## 🧩 Module Structure

This project is organized as a **multi-module Maven build**, with each module having a clearly defined responsibility and a controlled dependency direction.
The goal of this structure is to keep the core logic independent from UI frameworks and infrastructure concerns, while still allowing realistic integration and testing.

## 📦 Modules Overview

At a high level, the project consists of the following modules:
- [core](../modules/core/src/main/java/org/example/demo/core) — Application logic and business rules
- [model](../modules/model/src/main/java/org/example/demo/model/) — Domain data structures and pure model logic
- [realization](../modules/realization/src/main/java/org/example/demo/realization) — orchestration, asynchronization, DI, etc
- [util](../modules/util/src/main/java/org/example/demo/util) — Shared utilities and technical helpers
- [api](../modules/api/src/main/java/org/example/demo/api) - Contracts to unbind application components from 
  specific realizations. Currently only used for graphics platforms.
- [platform](../modules/platform) - aggregator module for GUI platform modules 
  - [javafx](../modules/platform/javafx/src/main/java/org/example/demo/fx) 
  - [swing](../modules/platform/swing/src/main/java/org/example/demo/swing)

Dependencies generally flow from higher-level policy to lower-level implementation.
![Selection](Screenshot(22).png)

## 🧠 Model Module

The [model](../modules/model/src/main/java/org/example/demo/model) 
module contains the domain model of the application.
It contains pure data structures and domain-level logic.

📦 Typical contents:
- [Shape definitions](../modules/model/src/main/java/org/example/demo/model/entity)
  (geometry, properties)
- [DTO and metadata](../modules/model/src/main/java/org/example/demo/model/metadata) objects

Model-level operations are independent of UI or execution context.

This module represents the stable core of the system’s data.
It is intentionally kept simple, deterministic, and easy to test.

🚫 Does not depend on any other module

## ⚙️ Core Module

The [core](../modules/core/src/main/java/org/example/demo/core) 
module contains the application logic. This is where:
- Domain objects [are manipulated](../modules/core/src/main/java/org/example/demo/core/logic/LogicClipboardProcessor.java) 
  according to application rules
- Commands are [defined and executed](../modules/core/src/main/java/org/example/demo/core/logic/command)
- State machines control application [modes](../modules/core/src/main/java/org/example/demo/core/adapter/ui/state/mode/UIMode.java)
- [Unit, intergration and functional tests](../modules/core/src/test/java/org/example/demo) are placed

🎯 Key responsibilities:
- Coordinating model changes
- Managing application state and modes
- Defining ports (interfaces) for interaction with the outside world
- Tests (unit, integration and functional)

The module remains independent of platform-specific parts.

🔗 Dependencies: 
- model
- util

From an architectural perspective, this module represents the policy and behavior of the application.

## 🧩 API Module

The [api](../modules/api/src/main/java/org/example/demo/api) 
module defines platform-independent UI **contracts** and abstractions used by all graphical implementations.

📦 Typical contents:
- [Rendering and drawing interfaces](../modules/api/src/main/java/org/example/demo/api/graphics)
- Input and interaction contracts

🎯 Key responsibilities:
- Enabling multiple UI implementations without code duplication

🚫 Does not depend on any other module

## 🧱 Platform Module

The [platform](../modules/platform) module is an **aggregator** for platform-specific UI implementations.

📦 Typical contents:
- [Maven aggregation and shared build configuration](../modules/platform/pom.xml)
- Common build and packaging logic

🔗 Dependencies:

(none — aggregator module)

This module contains no production code and exists purely to structure, 
 and configure platform-specific implementations.

## 🎨 JavaFX Module

The [javafx](../modules/platform/javafx/src/main/java/org/example/demo/fx) module contains all JavaFX-specific code and acts as the primary UI layer of the application.

📦 Typical contents:
- [JavaFX controllers](../modules/platform/javafx/src/main/java/org/example/demo/fx/port/ui)
- [FXML files](../modules/platform/javafx/src/main/resources/org/example/demo/fxml)
- [UI builders and factories](../modules/platform/javafx/src/main/java/org/example/demo/fx/initialization/ui)
- [JavaFX-specific implementation of core ports](../modules/platform/javafx/src/main/java/org/example/demo/fx/port/ui/element/FxLayersTree.java)
- [GUI-related implementations of functional tests](../modules/platform/javafx/src/test/java/org/example/demo/func)
- [Custom renderers and painters](../modules/platform/javafx/src/main/java/org/example/demo/fx/port/ui/graphics/FxPainter.java)
- [GUI launcher](../modules/platform/javafx/src/main/java/org/example/demo/FxHelloApplication.java) 
  (launchers are platform-specific)

🎯 Key responsibilities:
- Rendering the application state
- Handling user input (mouse, keyboard)
- Translating UI events into domain actions
- Displaying feedback to the user

🔗 Dependencies:
- core
- model
- realization
- util

This module is intentionally isolated so that UI concerns do not leak into the core logic.

## 🪟 Swing Module

The [swing](../modules/platform/swing/src/main/java/org/example/demo/swing) 
module contains all Swing/AWT-specific code 
and provides a complete alternative UI implementation.

📦 Typical contents:
- [Swing components and panels](../modules/platform/swing/src/main/java/org/example/demo/swing/port/ui)
- [Custom renderers and painters](../modules/platform/swing/src/main/java/org/example/demo/swing/port/ui/graphics/SwingPainter.java)
- [Event handling and input translation](../modules/platform/swing/src/main/java/org/example/demo/swing/port/ui/canvas/SwingShapeCanvasView.java)
- [Swing-based application launcher](../modules/platform/swing/src/main/java/org/example/demo/SwingHelloApplication.java)

🎯 Key responsibilities:
- Rendering the application using Swing/AWT
- Handling user input via Swing event mechanisms
- Mapping Swing events to domain actions

🔗 Dependencies:

api

core

model

realization

util

This module demonstrates that the application can be ported 
to a different UI toolkit without duplicating logic or relying 
on hybrid solutions like JFXPanel.

## 🧬 Realization Module

The [realization](../modules/realization/src/main/java/org/example/demo/realization) 
module provides systems that serve the application logic without modifying it.
This is where the following concerns are handled:
- [Dependency Injection](../modules/realization/src/main/java/org/example/demo/realization/initialization)
- [Application configuration](../modules/realization/src/main/java/org/example/demo/realization/configuration)
- [Thread management](../modules/realization/src/main/java/org/example/demo/realization/level/async)
- [Event orchestration](../modules/realization/src/main/java/org/example/demo/realization/level/transport)

🔗 Dependencies:
- model
- core
- util

This module helps keep the core module strict and focused on business rules, 
avoiding overloading that layer with non-business responsibilities.

## 🛠 Util Module

The [util](../modules/util/src/main/java/org/example/demo/util) module contains shared technical utilities used across multiple modules.

📦 Typical contents:
- General-purpose helpers
- Shared constants

These are reusable utilities not tied to a specific domain or UI concern.

## 🧪 Testing and Modules

Testing is aligned with module boundaries, but with one important detail:

Because realization of functional tests [is separated](../modules/platform/javafx/src/test/java/org/example/demo/) 
from test definitions themselves,
test classes from the javafx module depend on test classes from the core module.

🔗 More details are described in the dedicated [testing documentation](testing_strategy.md).