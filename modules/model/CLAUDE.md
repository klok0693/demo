# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures ← current module
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

## Model Module

The **model** module contains pure domain data structures and logic. It has no dependencies on other modules, making it the foundation of the application.

Key packages:
- `entity`: geometric shapes (`Shape`, `Rectangle`, `Ellipse`) and factory utilities.
- `metadata`: DTOs and parameter descriptors used by UI layers to expose shape properties.

## Navigation Tips

* **Shape definitions** – `modules/model/src/main/java/org/example/demo/model/entity/Shape.java` and related classes.
* **Factory & validation** – `ShapeFactory.java`, `ShapeValidator.java` (in core, but referenced here).
* **DTOs for UI binding** – `ShapeParams.java`, `ParamInfo.java`, `ShapeParam.java`.

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
