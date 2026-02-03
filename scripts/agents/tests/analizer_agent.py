import logging
from pathlib import Path
from typing import TypedDict

from langgraph.graph import END, StateGraph
from langchain_core.messages import (AIMessage, HumanMessage, SystemMessage)
from pydantic import BaseModel, Field

from tests.model import small_llm, medium_llm
from tests.prompt_manager import get_analyzer_prompt, get_critic_prompt

logger = logging.getLogger(__name__)

# --- VARIABLES ---

MAX_REFLECTION_STEPS = 3

ANALYZE = "analyze"
CRITIQUE = "critique"
SUMMARY = "summary"


# --- STATE ---

class AnalizerState(TypedDict):
    class_name: str 
    code: str
    require_tests: bool
    summary: str
    critique: str
    is_valid: bool
    iteration: int


# --- MODELS ---

class AnalizerOutput(BaseModel):
    """Schema for the code analysis result."""
    reason: str = Field(..., description="Explanation of the conclusion")
    require_tests: bool = Field(..., description="Does class require tests?") 

class CriticOutput(BaseModel):
    """Schema for the code analysis result."""
    validation_explanation: str = Field(description="Brief explanation of why the reasoning is valid or invalid.")
    is_reasoning_valid: bool = Field(description="Is reasoning valid?")     

structured_small = small_llm.with_structured_output(
    AnalizerOutput,
    method="json_schema"
)

structured_medium = medium_llm.with_structured_output(
    CriticOutput,
    method="json_schema"
)


# --- NODES ---

def analyzer(llm, state: AnalizerState):
    """Uses the small model to decide if tests are needed."""
    messages = [
        SystemMessage(content=get_analyzer_prompt()),
        HumanMessage(content=state["code"]),
    ]

    critique = state.get('critique')
    if critique:
        messages.append(HumanMessage(content=f"Critique: {critique}"))

    response = llm.invoke(messages)

    logging.debug(f"Response: {response}")
    return {"require_tests": response.require_tests, "summary": response.reason}


def analyzer_node(state: AnalizerState):
    return analyzer(structured_small, state)


def critic_node(state: AnalizerState):
    """Uses the meduim model to validate conclusion of the small model."""
    messages = [
        SystemMessage(content=get_critic_prompt()),
        HumanMessage(content=f"Original_code: {state['code']}"),
        HumanMessage(content=f"Rules: {get_analyzer_prompt()}"),
        HumanMessage(content=f"Requires_unit_tests: {state['require_tests']}"),
        HumanMessage(content=f"Reasoning string from first agent: {state['summary']}"),
    ]

    response = structured_medium.invoke(messages)

    logging.debug(f"Response: {response}")
    return {
        "is_valid": response.is_reasoning_valid, 
        "critique": response.validation_explanation,
        "iteration": state.get('iteration', 0) + 1,
    }


def summary_node(state: AnalizerState):    
    msg = AIMessage(content=f"{Path(state['class_name']).name} : {state['require_tests']}. Reason: {state['summary']}")
    return {"summary": [msg.content]}


# --- ROUTER ---

def require_additional_analize(state: AnalizerState):
    return state["require_tests"]


def should_stop_analize(state: AnalizerState):
    if state['is_valid']:
        return True
    if state['iteration'] >= MAX_REFLECTION_STEPS:
        return True
    return False


# --- GRAPH ---

analizer_builder = StateGraph(AnalizerState)

analizer_builder.add_node(ANALYZE, analyzer_node)
analizer_builder.add_node(CRITIQUE, critic_node)
analizer_builder.add_node(SUMMARY, summary_node)

analizer_builder.set_entry_point(ANALYZE)
analizer_builder.add_edge(ANALYZE, CRITIQUE)
#analizer_builder.add_conditional_edges(
#    ANALYZE,
#    require_additional_analize,
#    {
#        True: CRITIQUE,
#        False: SUMMARY
#    },
#)

analizer_builder.add_conditional_edges(
    CRITIQUE,
    should_stop_analize,
    {
        True: SUMMARY,
        False: ANALYZE
    }
)


analizer_builder.add_edge(SUMMARY, END)

analyzer_subgraph = analizer_builder.compile()
#analyzer_subgraph.get_graph().print_ascii()