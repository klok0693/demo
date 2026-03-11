## 📋 AI Test Generation Overview

AI-assisted system for generating software tests using **LangGraph-based agents**
is designed primarily as an educational exploration of AI agent architectures, particularly 
focusing on how smaller local language models can be orchestrated to perform complex reasoning tasks 
instead of relying on large online AI services.

The system analyzes project source code and generates tests automatically.
Currently, the focus is on unit test generation, but the architecture is designed to support additional Integration and
functional tests

## 🥞 Technology Stack

The project relies on **Python** as main implementation language,
**LangGraph** as framework for building AI agent graphs and pipelines,
**LangSmith** as debugging and observing agent execution and
**Local LLMs** for reasoning and generation tasks

**[Repomix](https://github.com/yamadashy/repomix)**	are used to extract project source code for AI agents

All model's configs are moved into **.env** file, so the system can be configured for
an offline local models usage or for online AI services without modifications in source code 

##  🗺️ High-Level Architecture

The system scans the project source directory,
whose is analyzed to determine if tests are required.
and, if true, the generator agent produces test code.
Generated tests are written to the output directory.

Agents may request additional information about the project using tools, 
which provides access to source code beyond the currently analyzed file.
This *tool-augmented reasoning* approach allows smaller models to work with large codebases 
without requiring the entire project to be placed in the model context.

## 📂 Project Structure
<pre>
   root/
   │
   ├── pyproject.toml
   │
   ├── tools/
   │   ├── repo_xml.py
   │   └── repo_tools.json
   │
   ├── tests/
   │   ├── analizer_agent.py
   │   ├── generator_agent.py
   │   ├── args_parser.py
   │   ├── model.py
   │   ├── prompt_manager.py
   │   └── test_agent.py
   │
   └── resources/
       ├── style.txt
       └── unit_tests_prompts.ini
</pre>

## 🛠️ Tools

**[Repomix tool](../scripts/agents/tools/repo_xml.py)** parses the project and creates internal maps: *files_by_path, files_by_name* 
and *packages*. These structures allow agents to search for source files when 
encountering unknown classes or imports. The behavior is conceptually 
*similar to a linker in compiled languages*, where missing symbols are resolved by 
loading additional modules.

*[repo_tools.json](../scripts/agents/tools/repo_tools.json)* defines the schema used to parse the generated Repomix XML output.
This schema allows agents to reliably interpret tool results.

## 🤖 AI Agents

All AI agents in the system are implemented as LangGraph graphs. Each agent is 
responsible for a specific step in the test generation pipeline. The architecture follows 
a modular agent design, where each agent performs a well-defined task

---

### Analyzer Agent

The [Analyzer Agent](../scripts/agents/tests/analizer_agent.py) determines whether a given source file requires test generation.
It consists of two cooperating sub-agents:
- **Source Analyzer** - Analyze the provided source code and determine whether test generation is required
- **Critic Agent** - The critic agent verifies the decision made by the Source Analyzer
to detect incorrect or incomplete conclusions. If the critic disagrees 
with the analysis a critique message is produced and the request is 
returned to the Source Analyzer.

This design follows the *ReAct pattern* to reduce incorrect conclusions and model hallucinations.

### Generator Agent

The [Generator Agent](../scripts/agents/tests/generator_agent.py) is responsible for creating test code for the analyzed 
source file following the project's test style rules

---

More complex reasoning tasks such as code generation are assigned to medium-sized model, 
while smaller model are used for faster, simpler tasks. This approach improves 
performance while preserving generation quality.


## 🗄️ Resource System

The system stores prompts and configuration files in *human-readable resources*.
This design allows non-technical specialists to modify the behavior of the agents 
without changing source code.

**[style.txt](../scripts/agents/tests/resources/style.txt)** - defines the coding style expected for generated tests.
**[unit_tests_prompts.ini](../scripts/agents/tests/resources/unit_test_prompts.ini)** - contains prompt templates used by the agents:
- Analyzer prompt
- Critic prompt
- Generator prompt

This approach keeps the AI behavior transparent and easy to adjust.

## 📝 Prompt Manager

The [Prompt Manager](../scripts/agents/tests/prompt_manager.py) is responsible for loading the appropriate prompts 
based on the selected test type by receiving the selected test type 
from the argument parser and loads the corresponding resources. 
This allows to configure agent behavior from command line

## 🔑 Models Configuration

The file **[model.py](../scripts/agents/tests/model.py)** defines the configuration of all AI models used in the system.

The system uses two models to reduce execution time:
- *Small Model* for simple tasks
- *Medium Model* if task requires a complex reasoning

This strategy reflects a resource-aware AI workflow, 
where simpler tasks are handled by smaller models while 
more complex reasoning is delegated to larger ones.


## 🛡️ Execution Limits and Safety

AI systems can easily enter uncontrolled loops or excessive tool usage.
To prevent this behavior, the system defines explicit execution limits using:
- *recursion_limit*
- *ToolCallLimitMiddleware*

If an agent exceeds the limits, an exception is raised. The main execution 
script must catch and handle these errors appropriately. This ensures that 
agent execution remains predictable and controllable.


## ⌨️ Argument Parser

The **[args_parser.py](../scripts/agents/tests/args_parser.py)** is responsible for parsing command-line arguments 
used to configure execution. Parameters include:
- input source folder
- output folder for generated tests
- test type


## 📔 Logging

The system supports configurable logging levels: *DEBUG, INFO, WARNING and ERROR*.
*Default level is WARNING*


## 🚀 Main Execution Script

[Test agent script](../scripts/agents/tests/test_agent.py) is the main entry point of the system, which:
- Receive the input folder from arguments.
- Recursively scan all source files.
- Pass files through the Analyzer Agent.
- Generate tests when required.
- Save generated tests to the specified output folder.

The script coordinates all agents and tools, *forming the full execution pipeline*.
In this architecture, AI agents act as components inside a deterministic system, 
rather than fully autonomous decision-makers.