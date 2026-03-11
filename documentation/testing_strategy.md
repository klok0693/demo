## 🧪 Testing Strategy

---

## 🗂️ Test Levels

The project uses *three levels of testing*, each targeting a different layer of the system:
 - [Unit Tests](../modules/core/src/test/java/org/example/demo/unit)
 - [Integration Tests](../modules/core/src/test/java/org/example/demo/integration)
 - [Functional Tests](../modules/core/src/test/java/org/example/demo/functional)

Test system is moving to full [AI test generation](AI_overview.md), while currently only unit tests 
generating are fully automated

## 🖥️ Functional Tests

Functional tests exercise the application **as a whole**, including user interaction and UI behavior.
Key characteristics:
 - Drive the application through real UI actions
 - Validate observable behavior and selected internal state
 - Run against a fully initialized application instance

These tests are implemented using: 

*JavaFX*: **TestFX**

*Swing*: **AssertJ**

simulating real user behavior rather than mocking UI interactions

#### 🪟 GUI vs Headless Execution

Functional tests can be executed in two modes:

🖼️ *GUI mode*:
Uses a real window - useful during development and debugging

🔲 *Headless mode*:
Uses **Monocle** for JavaFX or [**Cacio**](https://github.com/CaciocavalloSilano/caciocavallo) for Swing, allowing execution without a display (CI-friendly)

The execution mode is controlled via **Maven profiles** and **JVM arguments**.

---

## 🧩 Splitting Functional Tests Across Modules

One notable design decision is the separation of functional test intent from platform-specific realization.

📦 **Core** module contains:
 - [Gherkin scenarios](../modules/core/src/test/resources/org/example/demo/functional/scenario) 
 - [Step definitions](../modules/core/src/test/java/org/example/demo/functional) 
 - Shared test helpers and abstractions

🎨 **JavaFX** module contains
 - [JavaFX-specific test implementations](../modules/platform/javafx/src/test/java/org/example/demo/func/launchers)
 - [TestFX-based realizations of interactions](../modules/platform/javafx/src/test/java/org/example/demo/func/FxApplicationRobot.java)
 - [Application startup and lifecycle handling](../modules/platform/javafx/src/test/java/org/example/demo/func/hooks/FxHook.java)

This structure keeps test intent and behavior description independent of the UI framework, while still 
enabling realistic end-to-end testing, which made possible to reuse tests scenarios across various 
GUI platforms

#### ⚙️ Technical Consequences

This approach introduces additional complexity:

🔍 Test classes from the core module must be visible to tests 
in the platform's modules

📦 Maven does not expose test classes by default

To address this:
 - Tests from the core module are packaged into a **test-only JAR** 
 - This JAR is added as a test dependency to the javafx and swing modules

This is achieved using **Maven’s test-JAR packaging** and dependency configuration.

---

## 🧠 Behavior Model (Work in Progress)

A formal **application behavior scheme** — describing interaction flow, modes, 
and transitions — is currently under development and will be added in a future iteration 
of the documentation.
![Selection](not_found.png)
