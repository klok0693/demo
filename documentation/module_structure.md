## 🧩 Module Structure

This project is organized as a **multi-module Maven build**, with each module having a clearly defined responsibility and a controlled dependency direction.
The goal of this structure is to keep the core logic independent from UI frameworks and infrastructure concerns, while still allowing realistic integration and testing.

## 📦 Modules Overview

At a high level, the project consists of the following modules:
- **core** — Application logic and business rules
- **model** — Domain data structures and pure model logic
- **realization** — orchestration, asynchronization, DI, etc
- **util** — Shared utilities and technical helpers
- **api** - Contracts to unbind application components from 
  specific realizations. Currently only used for graphics platforms.
- **platform** - aggregator module for GUI platform modules 
  - **javafx** 
  - **swing**

Dependencies generally flow from higher-level policy to lower-level implementation.
![Selection](Screenshot(22).png)

## 🧠 Model Module

The model module contains the domain model of the application.
It contains pure data structures and domain-level logic.

📦 Typical contents:
- Shape definitions (geometry, properties)
- DTO and metadata objects
- Domain invariants and validation

Model-level operations are independent of UI or execution context.

This module represents the stable core of the system’s data.
It is intentionally kept simple, deterministic, and easy to test.

🚫 Does not depend on any other module

## ⚙️ Core Module

The core module contains the application logic. This is where:
- Domain objects are manipulated according to application rules
- Commands are defined and executed
- State machines control application modes

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

The api module defines platform-independent UI **contracts** and abstractions used by all graphical implementations.

📦 Typical contents:
- Rendering and drawing interfaces
- Input and interaction contracts

🎯 Key responsibilities:
- Enabling multiple UI implementations without code duplication

🚫 Does not depend on any other module

## 🧱 Platform Module

The platform module is an **aggregator** for platform-specific UI implementations.

📦 Typical contents:
- Maven aggregation and shared build configuration
- Common build and packaging logic

🔗 Dependencies:

(none — aggregator module)

This module contains no production code and exists purely to structure, 
 and configure platform-specific implementations.

## 🎨 JavaFX Module

The javafx module contains all JavaFX-specific code and acts as the primary UI layer of the application.

📦 Typical contents:
- JavaFX controllers
- FXML files
- UI builders and factories
- JavaFX-specific implementation of core ports
- GUI-related implementations of functional tests
- Application Main class (launchers are platform-specific)

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

The swing module contains all Swing/AWT-specific code 
and provides a complete alternative UI implementation.

📦 Typical contents:
- Swing components and panels
- Custom renderers and painters
- Event handling and input translation
- Swing-based application launcher

🎯 Key responsibilities:
- Rendering the application using Swing/AWT
- Handling user input via Swing event mechanisms
- Mapping Swing events to domain actions
- Providing a functional UI without JavaFX dependencies

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

The realization module provides systems that serve the application logic without modifying it.
This is where the following concerns are handled:
- Dependency Injection (DI)
- Application configuration
- Thread management
- Event orchestration

🔗 Dependencies:
- model
- core
- util

This module helps keep the core module strict and focused on business rules, 
avoiding overloading that layer with non-business responsibilities.

## 🛠 Util Module

The util module contains shared technical utilities used across multiple modules.

📦 Typical contents:
- General-purpose helpers
- Shared constants

These are reusable utilities not tied to a specific domain or UI concern.

## 🧪 Testing and Modules

Testing is aligned with module boundaries, but with one important detail:

Because realization of functional tests is separated from test definitions themselves,
test classes from the javafx module depend on test classes from the core module.

🔗 More details are described in the dedicated [testing documentation](testing_strategy.md).