import logging
from pathlib import Path
from typing import Optional, TypedDict

from langgraph.graph import END, StateGraph

from tests.args_parser import get_parser, get_args
from tests.analizer_agent import (AnalizerState, analyzer_subgraph)
from tests.generator_agent import (GenerationState ,generator_subgraph)

logger = logging.getLogger(__name__)

# --- VARIABLES ---

ANALYZER_SUBGRAPH = "analyzer_subgraph"
GENERATOR_SUBGRAPH = "generator_subgraph"


# --- STATE ---

class AgentState(TypedDict):
    class_name: str
    code: str
    require_tests: bool
    message: str
    generated_tests: Optional[str]


# --- NODE

def call_analyzer_subgraph(state: AgentState):
    """Adapter for analyzer subgraph"""

    subgraph_input: AnalizerState = {
        "class_name": state['class_name'],
        "code": state["code"],
        "require_tests": False,
        "summary": "",
        "critique": "",
        "is_valid": False,
        "iteration": 0
    }    

    require_tests: bool
    summary: str

    try:
        subgraph_results = analyzer_subgraph.invoke(subgraph_input)
        require_tests = subgraph_results["require_tests"]
        summary = subgraph_results["summary"]

    except Exception as e:
        require_tests = False;
        summary = f"Can't analize class {state['class_name']}: {e}"
    
    return {"require_tests": require_tests, "message": summary}


def call_generator_subgraph(state: AgentState):
    """Adapter for generator subgraph"""

    subgraph_input: GenerationState = {
        "code": state["code"],
        "messages": []
    }    

    generated_tests: Optional[str]
    try:
        subgraph_results = generator_subgraph.invoke(subgraph_input)
        final_message = subgraph_results["messages"][-1]
        generated_tests = final_message.content
    except Exception as e:
        logger.error(f"Can't generate tests for {state['class_name']}: {e}")
        generated_tests = None

    return {"generated_tests": generated_tests}


# --- ROUTER ---

def should_generate_tests(state: AgentState):
    return state["require_tests"]


# --- GRAPH ---

graph = StateGraph(AgentState)

graph.add_node(ANALYZER_SUBGRAPH, call_analyzer_subgraph)
graph.add_node(GENERATOR_SUBGRAPH, call_generator_subgraph)

graph.set_entry_point(ANALYZER_SUBGRAPH)
graph.add_conditional_edges(
    ANALYZER_SUBGRAPH,
    should_generate_tests,
    {
        True: GENERATOR_SUBGRAPH,
        False: END
    }
)
graph.add_edge(GENERATOR_SUBGRAPH, END)

app = graph.compile()


# --- RUNNER ---

def setup_logging(level: str):
    numeric_level = getattr(logging, level.upper(), logging.INFO)

    logging.basicConfig(
        level=numeric_level,
        format="%(asctime)s | %(message)s",
    )

    logging.getLogger("langchain").setLevel(logging.WARNING)
    logging.getLogger("openai").setLevel(logging.WARNING)
    logging.getLogger("httpx").setLevel(logging.WARNING)


def parse_args():
    parser = get_parser()
    parser.add_argument("--input_folder", required=True, help="Path to source code file")
    parser.add_argument("--output_folder", required=True, help="Path to output test file")
    parser.add_argument("--log-level", default="INFO", choices=["DEBUG", "INFO", "WARNING", "ERROR"])
    return get_args()


def main():
    args = parse_args()
    setup_logging(args.log_level)

    input_root = Path(args.input_folder).resolve()
    output_root = Path(args.output_folder).resolve()

    for input_file in input_root.rglob("*.java"):
        relative = input_file.relative_to(input_root)
        output_file = output_root / relative.parent / f"{relative.stem}Test{relative.suffix}"
        code = Path(input_file).read_text(encoding="utf-8")

        logger.debug(f"Code: {code}")

        initial_state: AgentState = {
            "class_name": Path(input_file).name,
            "code": code,
            "require_tests": False,
            "message": "",
            "generated_tests": None
        }

        executed_state = app.invoke(initial_state)
        
        logger.info(executed_state.get('message'))
        if executed_state["require_tests"]:
            value = executed_state.get("generated_tests")

            if value is None:
                logger.error(f"Error: missing tests for {input_file}")
            else:    
                output_path = Path(output_file)
                output_path.parent.mkdir(parents=True, exist_ok=True)
                output_path.write_text(value, encoding="utf-8")


if __name__ == "__main__":
    main()
