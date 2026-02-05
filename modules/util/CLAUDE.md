# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures 
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util           ← current module
```

## Util Module
The *util* module provides shared utilities and logging facilities used by other modules:

```
modules/util/
 ├─ src/main/java/org/example/demo/util/          ← core utility classes (e.g., ValueUtil, MarkerStorage)
 └─ src/main/resources/logback.xml               ← default logging configuration
```

Key responsibilities:
- **Logging** – `MarkerStorage` implements a simple marker‑based logger that can be used by any module.
- **Value utilities** – `ValueUtil` offers helper methods for common value transformations.
- **Configuration** – The module exposes no runtime configuration; it relies on the consuming modules to provide context.

## Important Files
| File | Purpose |
|------|---------|
| `../../CLAUDE.md` | Root project's CLAUDE.md. |
| `../../pom.xml` | Root project's pom, aggregates modules and defines common properties. |
| `pom.xml` | module dependencies and test‑jar plugin. |
| `documentation/build_and_execution.md` | Detailed run instructions for each platform. |

## Useful Links
* Project overview: `documentation/module_structure.md`
* Architecture diagram: `documentation/architecture_overview.md`
