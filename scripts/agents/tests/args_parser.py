import argparse

_parser = argparse.ArgumentParser()
_cached_args = None

def get_parser():
    """Used by modules to register their arguments."""
    return _parser

def get_args():
    """Parses once and returns the same object"""
    global _cached_args
    if _cached_args is None:
        _cached_args = _parser.parse_args()
    return _cached_args