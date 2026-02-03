from langchain_openai import ChatOpenAI

from dotenv import load_dotenv
import os


# --- SETUP ---

load_dotenv()

OPENAI_PORT = os.getenv("OPENAI_PORT")
SMALL_MODEL = os.getenv("SMALL_MODEL") or ""
MEDIUM_MODEL = os.getenv("MEDIUM_MODEL") or ""
temperature = 0.1

# --- MODELS ---

small_llm = ChatOpenAI(
    base_url= OPENAI_PORT,
    model = SMALL_MODEL,
    temperature = temperature
)

medium_llm = ChatOpenAI(
    base_url = OPENAI_PORT,
    model = MEDIUM_MODEL,
    temperature = temperature
)

