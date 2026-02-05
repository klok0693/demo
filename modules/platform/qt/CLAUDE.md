# CLAUDE.md for Qt Platform Module

This file provides quick‑reference commands and notes for working with the **Qt** platform module. It is intended to be used by developers who are navigating this complex, multi‑module project.

## Build & Run

| Task | Maven command |
|------|---------------|
| Compile all modules (GUI + native) | `mvn clean verify` |
| Build only the headless profile (CI‑friendly) | `mvn clean verify -Pheadless` |
| Build a thin JavaFX JAR (used by other modules) | `mvn clean verify -Pheadless && java -jar modules/platform/javafx/target/javafx-1.2-thin.jar` |
| Run the Qt application locally | `mvn exec:java -Dexec.mainClass=org.example.demo.QtMain` |
| Build and run the native runtime (fat JAR) | `mvn clean verify && java -jar target/qt-java-runtime.jar` |

> **Tip** – The `-Pheadless` profile skips GUI tests, which is useful for CI pipelines that lack a display.

## Tests

| Test type | Command |
|-----------|---------|
| Unit tests (core & realization) | `mvn -Dtest=org.example.demo.unit.* test` |
| Integration/functional GUI tests | `mvn verify -PheadlessTests` |
| Skip all tests | `mvn clean verify -PskipTests` |

The Qt module contains its own Cucumber integration tests under `src/test/resources/cucumber.properties`. They are executed as part of the Failsafe plugin during the `verify` phase.

## Native Build Notes

* The native C++ code is compiled via **CMake** and integrated with Java using **JExtract**.
* Key properties:
  * `jextract.home` – path to JExtract binary (set in parent pom).
  * `qt.toolchain` – location of the Qt SDK for the target platform.

Build steps performed by Maven plugins:
1. **Generate Java bindings** – `maven-antrun-plugin` runs JExtract during `generate-sources`.
2. **Configure CMake** – executed in the `verify` phase (`configure-qt`).
3. **Compile native code** – `build-qt` goal of the same plugin.
4. **Copy runtime jar** – `copy-qt-jar` copies the fat JAR into the native build output.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures 
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation 
 ├─ platform/swing   ← Swing UI implementation 
 ├─ platform/qt      ← Qt Quick UI implementation ← current module
 └─ util
```

## Key Directories & Files

```
src/main/c++/          # C++ source and CMakeLists.txt
src/main/java/         # Java entry points (QtMain, QtAppInitializer)
src/generated/java/    # Auto‑generated JExtract bindings
src/main/resources/   # QML resources
src/test/             # Cucumber feature files & test utilities
pom.xml               # Module POM with profiles and plugins
```

## Common Issues

* **Missing Qt SDK** – Ensure `qt.toolchain` is set in the environment or via a profile (`qt-windows`, `qt-linux`).
* **CMake errors** – Verify that the C++ compiler matches the toolchain preset (e.g., `linux-gcc`).
* **Test failures due to display** – Run with `-PheadlessTests` if you are on a headless CI runner.

## Useful Commands for Developers

```bash
# Clean and build only this module
mvn -pl modules/platform/qt clean verify

# Run the Qt application in debug mode (Java)
mvn exec:java -Dexec.mainClass=org.example.demo.QtMain -Dexec.args="--debug"
```

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
