import logging
import json
from typing import Optional

from typing import Dict, List, Optional

from langchain_core.tools import tool

logger = logging.getLogger(__name__)

class RepoIndex:
    """
    Lightweight in-memory index over a Repomix XML export.
    No Java semantics, no AST, no reasoning.
    """

    def __init__(self, xml_path: str):
        self.xml_path = xml_path

        # core storage
        self.files_by_path: Dict[str, str] = {}
        self.files_by_name: Dict[str, List[str]] = {}
        self.packages: Dict[str, List[str]] = {}

        self._load()

        logger.debug(f"Number of files loaded: {len(self.files_by_path)}")
        logger.debug(f"Number of filenames indexed: {len(self.files_by_name)}")
        logger.debug(f"Number of packages indexed: {len(self.packages)}")

    def _load(self):
        """
        Load a canonical index from Repomix JSON in simple {path: content} form.
        """
        with open(self.xml_path, "r", encoding="utf-8") as f:
            data = json.load(f)

        files = data.get("files", {})
        if not isinstance(files, dict):
            raise ValueError("Expected 'files' section to be a dict")

        for path, content in files.items():
            content = (content or "").strip()

            # 1. Path → source
            self.files_by_path[path] = content

            # 2. Filename → paths
            filename = path.split("/")[-1]
            self.files_by_name.setdefault(filename, []).append(path)

            # 3. Package → paths
            package = self._extract_package(content)
            if package:
                self.packages.setdefault(package, []).append(path)


    @staticmethod
    def _extract_package(source: str) -> Optional[str]:
        for line in source.splitlines():
            line = line.strip()
            if line.startswith("package "):
                return line.replace("package", "").replace(";", "").strip()
        return None


# -----------------------------
# Query functions (LLM tools)
# -----------------------------

class RepoQueryTools:
    """
    Deterministic query interface.
    These methods are meant to be exposed as LLM tools.
    """

    def __init__(self, index: RepoIndex):
        self.index = index

    
    def get_tools(self):

        def _get_file_by_path_func(path: str) -> str:
            logging.info("Getting file " + path)
            return self.index.files_by_path.get(path, f"ERROR: file not found: {path}")

        @tool
        def get_file_by_path(path: str) -> str:
            """
            Return full source code of a file by exact path.
            """
            return _get_file_by_path_func(path)

        @tool
        def get_class_source(class_name: str) -> str:
            """
            Return source code of a class by filename (e.g. Color or Color.java).
            If multiple matches exist, returns all with paths.
            """

            logging.info("Getting class source " + class_name)
            if not class_name.endswith(".java"):
                class_name = class_name + ".java"

            paths = self.index.files_by_name.get(class_name)
            if not paths:
                return f"ERROR: class not found: {class_name}"

            if len(paths) == 1:
                return _get_file_by_path_func(paths[0])

            # multiple matches
            result = []
            for p in paths:
                result.append(f"// FILE: {p}\n{_get_file_by_path_func(p)}")
            return "\n\n".join(result)

        @tool
        def list_files_in_package(package: str) -> List[str]:
            """
            List file paths belonging to a Java package.
            """

            logging.info("List files in " + package)
            return self.index.packages.get(package, [])

        @tool
        def search_symbol(symbol: str) -> List[str]:
            """
            Naive text search for a symbol across all files.
            Returns matching file paths only.
            """

            logging.info("Search symbol " + symbol)
            matches = []
            for path, content in self.index.files_by_path.items():
                if symbol in content:
                    matches.append(path)
            return matches

        return [get_file_by_path, get_class_source, list_files_in_package, search_symbol]    


# -----------------------------
# LangGraph-friendly factory
# -----------------------------

def create_repo_query_tools(xml_path: str) -> RepoQueryTools:
    """
    Factory helper so LangGraph can inject this node easily.
    """
    index = RepoIndex(xml_path)
    return RepoQueryTools(index)

