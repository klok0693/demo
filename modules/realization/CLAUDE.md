# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts 
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures 
 ├─ realization   ← Guice initialization and DI wiring ← current module
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

## Configuration & Command‑Line Arguments
The module parses command‑line arguments via `AppArgumentsParser`.  Common flags:
```
--config <file>          Path to a JSON/YAML config file.
--profile <name>         Activate a Maven profile (e.g., headless, guiTests).
--help                   Show usage information.
```
See `src/main/java/org/example/demo/realization/configuration/parser/AppArgumentsParser.java` for the full list and defaults.

## Dependency Injection & Wiring
The Guice modules that bind interfaces to concrete implementations live in:
```
src/main/java/org/example/demo/realization/initialization/di/module/
```
Key modules:
- `CoreModule` – binds core services.
- `ViewModule` – wires UI adapters (JavaFX, Swing, Qt).
- `AsyncModule` – provides async executors and wrappers.
- `LogicModule`, `CommandModule`, `ContextModule`, `ProviderModule`, `UIAdapterModule`, `ControllerModule`, `ConfigurationModule` – each handles a specific aspect of the application lifecycle.

## Event Transport Layer
The transport package implements a lightweight channel abstraction used by adapters:
```
src/main/java/org/example/demo/realization/level/transport/
```
Key classes:
- `Channel`, `SenderWrapper`, `ReceiverWrapper` – generic send/receive.
- `LogicEventSenderWrapper`, `LogicEventReceiverWrapper` – bridge between core logic and adapters.
- `ClipboardEventSenderWrapper`, `ClipboardEventReceiverWrapper` – clipboard events.

## Async Wrappers & Executors
Asynchronous execution is handled by:
```
src/main/java/org/example/demo/realization/level/async/
```
Key classes:
- `BackgroundExecutor`, `BlockingForegroundExecutor`, `NonBlockingForegroundExecutor` – executor implementations.
- `AsynchWrapper`, `RunnableWrapper`, `EventProcessorAsyncWrapper`, `ClipboardProcessorAsyncWrapper` – wrappers that adapt core logic to async execution.

## Navigation Tips
| Topic | How to Find It |
|------|----------------|
| Core wiring points | Search for `AppInitializer`, `Application.java`, or the Guice modules in `di/module`. |
| Event handling | Look at classes ending with `Wrapper` in `transport` and `async` packages. |
| Configuration parsing | Open `AppArgumentsParser.java` and related parser classes (`ConfigurationParser`, `SystemPropertiesConfigurationParser`). |
| Async execution | Search for `BackgroundExecutor` or `AsynchWrapper`. |
| Platform adapters | Check `UIAdapterModule` and the corresponding adapter classes in `platform/javafx`, `platform/swing`, `platform/qt`. |

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
