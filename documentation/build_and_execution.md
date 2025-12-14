## 🔧 Build & Execution

This project uses **Maven** as the primary build tool and relies on a **multi-module setup**.
Build configuration is intentionally explicit, as build and execution behavior is part of the architectural design.

---

## 👤 Build Profiles

The project defines multiple Maven profiles to control how the application and tests are executed.

### 🖼️ GUI Mode (default)

* Uses a real JavaFX window
* Intended for local development and debugging
* Allows visual inspection of UI behavior and interactions

This profile is active by default and requires a graphical environment.

```bash
mvn clean verify
```

---

### 🔲 Headless Mode

* Runs JavaFX without a display using **Monocle**
* Intended for CI environments and automated execution
* Enables functional GUI tests without rendering a visible window

To build and execute the project in headless mode:

```bash
mvn clean verify -Pheadless
```
---

## ✓ Test Execution Strategy

Different test types are executed at different build phases:
- Unit tests — executed via *Surefire*
- Integration and functional tests — executed via *Failsafe*

This separation allows:
- Controlled JVM restarts for GUI tests
- Isolation of application lifecycle per test
- Reliable execution of JavaFX-based scenarios

#### 🔒 JVM Isolation

All tests are executed in **separate JVM instances**, enforced by Maven configuration
(forkCount / reuseForks=false).

This is especially important for:
- JavaFX application lifecycle correctness
- GUI and functional tests that require clean startup and shutdown
- Preventing shared static or platform state from leaking between tests

---

## ▶️ Running the Application

After a successful build, the application JAR can be launched manually from the root:
```bash
java -jar modules/javafx/target/javafx-1.0-SNAPSHOT.jar
```

The JavaFX module contains the application entry point, as application startup is platform-specific.

---

## 📋 Runtime Requirements

The produced artifact is a **thin JAR**. This means:
- The JAR does not include a bundled JVM
- JavaFX is not shaded or embedded

To run the application, the runtime environment must provide:
- A compatible Java runtime(java 21+)
- OpenJFX libraries available on the module/classpath