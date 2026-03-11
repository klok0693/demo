import logging
from typing import List, TypedDict, Annotated
from operator import add

from langchain.agents import create_agent
from langchain.agents.middleware import ToolCallLimitMiddleware

from langchain.agents import AgentState
from langgraph.graph import END, StateGraph
from langchain_core.messages import (BaseMessage, HumanMessage, SystemMessage)

from tests.model import medium_llm
from tools.repo_xml import RepoIndex, RepoQueryTools
from tests.prompt_manager import get_generator_prompt

logger = logging.getLogger(__name__)


# --- VARIABLES ---

INIT_TEST_MESSAGES = "init_test_messages"
LLM = "llm"


# -- TOOLS ---

index = RepoIndex("tools/repomix-output.json")
repo_tools = RepoQueryTools(index)
tools = repo_tools.get_tools()


# --- Agent ---
#class GenerationState(TypedDict):
class GenerationState(AgentState):
    code: str 


# --- MODEL ---

generator_agent = create_agent(
    model=medium_llm,
    tools=tools,
    state_schema=GenerationState,
    middleware=[
        ToolCallLimitMiddleware(thread_limit=30, run_limit=30, exit_behavior="error")
    ]
)


# --- NODE ---

def init_test_messages_node(state: GenerationState):
    """Prepares the message history for the test generator."""

    logging.info("Send to LLM " + state["code"])
    messages = [
        SystemMessage(content=get_generator_prompt()),
        HumanMessage(content=state['code'])
    ]
    return {"messages": messages}


def call_model_node(state: GenerationState):    
    """The node that actually calls the LLM with tools."""

    messages = state["messages"]
    logging.debug(f"LLM execution: {messages}")

    response = generator_agent.invoke(state, config={"recursion_limit": 50})
    return response


# --- GRAPH ---

gen_builder = StateGraph(GenerationState)

gen_builder.add_node(INIT_TEST_MESSAGES, init_test_messages_node)
gen_builder.add_node(LLM, call_model_node)

gen_builder.set_entry_point(INIT_TEST_MESSAGES)
gen_builder.add_edge(INIT_TEST_MESSAGES, LLM)
gen_builder.add_edge(LLM, END)

generator_subgraph = gen_builder.compile()