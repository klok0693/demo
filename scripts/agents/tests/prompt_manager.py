import logging
from configparser import ConfigParser

from tests.args_parser import get_parser, get_args

logger = logging.getLogger(__name__)

get_parser().add_argument("--type", choices=["unit", "integration"], required=True, help="Type of generated tests")

_cache = {}


def _get_prompt(section: str) -> str:
    if section not in _cache:
        args = get_args()
        config = ConfigParser(interpolation=None)
        config.read(f"tests/resources/{args.type}_test_prompts.ini")
        _cache[section] = config.get(section, "prompt")
        logging.debug(f"Added to section cache {section}: {_cache[section]}")        

    return _cache[section]


def get_analyzer_prompt() -> str:
    return _get_prompt("analyzer")


def get_critic_prompt() -> str:
    return _get_prompt("critic")


def get_generator_prompt() -> str:
    return _get_prompt("generator")

