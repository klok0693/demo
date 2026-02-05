# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Common Commands

| Task | Command |
|------|---------|
| Build all modules (GUI + native) | `mvn clean verify` |
| Build only the headless (CI‑friendly) profile | `mvn clean verify -Pheadless` |
| Build a thin JavaFX JAR | `mvn clean verify -Pheadless` |
| Run a single unit test (e.g., `ShapeValidatorTest`) | `mvn -Dtest=org.example.demo.unit.ShapeValidatorTest test` |
| Skip all tests | `mvn clean verify -PskipTests` |

## Build & Execution Notes

* The project uses Maven multi‑module layout. All modules are built together unless a specific profile is activated.
* Native C++ code (Qt) is compiled via the `-Dqt.toolchain=…` and `-Djextract.home=…` properties; see `documentation/build_and_execution.md` for details.

## High‑Level Architecture

The repository follows a **hexagonal (ports & adapters)** architecture with modules:
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts
 ├─ core          ← business logic, ports & adapters
 ├─ model          ← pure domain data structures
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

All platform modules depend only on `core`, `model`, `api`, `util`, and `realization`; no circular dependencies exist.

## Testing Strategy

* Unit tests run via Surefire.
* Integration/functional GUI tests use Failsafe with separate JVMs (headless or GUI profiles).
* Profiles:
  * `guiTests` – default, requires a display.
  * `headlessTests` – runs without X server.
  * `skipTests` – skips all test phases.

See `documentation/testing_strategy.md` for more details.

## Documentation & Navigation

Key docs are located in the `documentation/` directory:
- `module_structure.md`
- `architecture_overview.md`
- `gui_features.md`
- `multi_platform_migration.md`
- `build_and_execution.md`
- `testing_strategy.md`

These files provide deeper insight into module responsibilities, event orchestration, and platform migration.
