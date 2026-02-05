# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts
 ├─ core          ← business logic, ports & adapters ← current module
 ├─ model          ← pure domain data structures
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

### Core Module
* **Ports** – interfaces in `src/main/java/org/example/demo/core/port/...`.  
* **Adapters** – concrete implementations that translate between ports and the UI or persistence layers.  
* **Logic** – pure domain logic (`ShapeProcessor`, command pattern, state machine).  
* **State** – immutable model objects (`ModelState`, `Selection`).
The core module is platform‑agnostic; all UI code depends on it via Guice injection.

### Command Pattern
Commands are defined in `src/main/java/org/example/demo/core/logic/command`.  `CommandProcessor` executes them and supports undo/redo.  See `CommandFactoryImpl.java`.

### State Machine for UI Modes
UI modes (insert, select, edit) live in `src/main/java/org/example/demo/core/adapter/ui/state/mode`.  `ModeSwitcher` routes events to the current mode implementation.

## Important Files
| File | Purpose |
|------|---------|
| `../../CLAUDE.md` | Root project's CLAUDE.md. |
| `../../pom.xml` | Root project's pom, aggregates modules and defines common properties. |
| `pom.xml` | Core module dependencies and test‑jar plugin. |
| `documentation/build_and_execution.md` | Detailed run instructions for each platform. |

## Useful Links
* Project overview: `documentation/module_structure.md`
* Architecture diagram: `documentation/architecture_overview.md`
