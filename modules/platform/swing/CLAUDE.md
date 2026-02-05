# CLAUDE.md

This module implements the **Swing** UI adapter for application. It wires the core business logic to Java Swing components and provides a desktop‑only entry point.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures 
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation 
 ├─ platform/swing   ← Swing UI implementation ← current module
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

## Common Commands

| Task | Command |
|------|---------|
| Build this module only (with all its transitive dependencies) | `mvn -pl modules/platform/swing clean verify` |
| Run the Swing UI (requires an X server) | `java -cp $(mvn -q -Dexec.executable=echo -Dexec.args='${project.build.testOutputDirectory}' --non-recursive exec:exec) modules/platform/swing/target/classes/com/example/demo/swing/Main.class` |
| Run a single unit test in this module (e.g., `SwingComponentTest`) | `mvn -pl modules/platform/swing -Dtest=org.example.demo.swing.unit.SwingComponentTest test` |
| Skip all tests for the entire build | `mvn clean verify -PskipTests` |

> **Tip**: The root‑level Maven commands (`mvn clean verify`) will also build this module automatically. Use the `-pl` flag only when you want to isolate work.

## Key Files & Packages

- **`Main.java`** – Entry point that launches the Swing UI. Located at `src/main/java/com/example/demo/swing/Main.java`.
- **Adapters** – Classes in `com.example.demo.swing.adapter` translate core domain events into Swing actions.
- **Tests** – Look for tests under `src/test/java/org/example/demo/swing/unit/`.

## Navigation Tips

1. **Jump to the UI entry point**: `Main.java`.
2. **Find adapters**: Search for classes ending in `Adapter` or containing `Swing`. Use `grep -R "class .*Adapter" src/main/java`.
3. **Explore event handling**: Look at `EventDispatcher` usage inside adapters.
4. **Run tests quickly**: `mvn -pl modules/platform/swing test` will run all Swing‑specific tests.


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
