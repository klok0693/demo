# CLAUDE.md

This module implements the **JavaFX** UI adapter for application. It wires the core business logic to JavaFX components and provides a desktop‑only entry point.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures 
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation ← current module
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```


## Common Commands

| Task | Command |
|------|---------|
| Build all modules (GUI + native) | `mvn clean verify` |
| Build only the headless profile (CI‑friendly) | `mvn clean verify -Pheadless` |
| Build a thin JavaFX JAR | `mvn clean verify -Pheadless && java -jar modules/platform/javafx/target/javafx-1.2-thin.jar` |
| Run a single unit test in this module (e.g., `JavaFxShapeTest`) | `mvn -Dtest=org.example.demo.platform.javafx.unit.JavaFxShapeTest test` |
| Skip all tests for the entire build | `mvn clean verify -PskipTests` |

> **Tip** – If you only want to rebuild this module, use Maven’s *project list* feature:
> ```bash
> mvn -pl modules/platform/javafx clean verify
> ```
>
---
## Build & Execution Notes

- The JavaFX module is a thin wrapper that wires the core logic into a JavaFX UI.  It has no native dependencies, so it can be built on any platform.
- The resulting JAR (`javafx-1.2-thin.jar`) contains only the JavaFX entry point and minimal runtime classes; all business logic lives in `core` and is pulled in as a dependency.
- To run the application locally:
> ```bash
> java -jar modules/platform/javafx/target/javafx-1.2-thin.jar
> ```

## Navigation Tips

- **UI Components** – Look under `src/main/java/org/example/demo/platform/javafx` for FXML files and controller classes.
- **Tests** – Unit tests are in `src/test/java/.../unit`.  Run them with the command above or via your IDE’s test runner.
- **Resources** – Any CSS, images, or other assets live under `src/main/resources`.


## Important Files
| File | Purpose |
|------|---------|
| `../../../CLAUDE.md` | Root project's CLAUDE.md. |
| `../../../pom.xml` | Root project's pom, aggregates modules and defines common properties. |
| `pom.xml` | module dependencies and test‑jar plugin. |
| `documentation/build_and_execution.md` | Detailed run instructions for each platform. |

## Useful Links
* Project overview: `documentation/module_structure.md`
* Architecture diagram: `documentation/architecture_overview.md`
