# Purpose

This repository is a technology- and architecture-focused project used as a **personal playground 
and investigation space** to test and combine engineering ideas, libraries, architectural patterns, 
and testing approaches. I decided to open it to give reviewers a broader and more honest view of 
my technical level across different areas

The project is primarily intended for technical readers rather than as a showcase of end-user 
functionality. It is not intended to showcase a specific library or predefined technology stack. 
Instead, it brings together multiple concepts within a *single, cohesive codebase*. Working on 
one relatively complex project rather than many isolated examples was a deliberate choice, 
allowing ideas to interact under more realistic constraints.

This README provides a *high-level overview*, while deeper explanations are available in dedicated 
documents *listed below*. The GitHub version is kept *clean, buildable, and stable*, even though 
local development may include experiments and refactoring

> ⚠️ Some parts may appear overengineered by design - the focus is learning and validation rather 
> than end-user functionality.

#### 📄 Content
- Application Overview
- Tech Stack
- What This Project Demonstrates
- Documentation & Navigation

---

# Application Overview

From a user perspective, the application is a **simple JavaFX-based graphical editor** that allows working  
with basic shapes on a canvas. Users can:
- draw rectangles and circles
- assign shapes to layers
- edit visual properties (position, size, color)
- interact via mouse or keyboard
- perform copy, paste and cut operations

Shapes are selectable, resizable, and draggable. The interface includes 
a property panel, layer's tree with a shape lists, and toolbar.

![Selection](documentation/screenshot_0.png)

---

## Tech Stack

#### 💼 Project: Java, JavaFX, Google Guice, Logback, Maven, Lombock 
#### 📋 Tests: JUnit, Mockito, Cucumber, TestFX, Monocle

---

## What This Project Demonstrates

### 🧱 Architecture & Design
- **Hexagonal (Ports & Adapters) architecture**
- A developed **domain model**, kept independent from UI and infrastructure concerns
- **Events orchestration** separated from business logic, avoiding UI-driven control flow
- Clear boundaries between **synchronous and asynchronous execution**, explicitly modeled and kept out of core logic

### 🔌 Dependency Management
- **Dependency Injection (Guice)**, including runtime bindings, test-specific configuration, pre-initialization 
    dependency setup etc
- **Strong modular separation, using Maven multi-module setup and JPMS**, with a clear distinction between:
  - platform-agnostic core logic
  - platform-specific implementations (JavaFX)

### 🎨 Canvas & Interaction Logic
- **Layered canvas architecture**, covering rendering, input handling, and interaction logic  
  (mouse events, drag & drop, tool-specific behavior), while keeping model state separate from visual concerns

### ⚙️ Application Behavior & Control
- A **Command Pattern** implementation with undo support
- A **state machine–based approach** to control application modes and UI behavior
- **Fail-safe validation and defaulting**, keeping the system operational under partial or invalid input
- **Structured logging with markers**, aimed at tracing behavior across layers and threads
- **JavaFX controller and node builder factories**, used to decouple UI creation from application logic

### 🧪 Testing Strategy
The testing strategy intentionally mixes multiple test types, each targeting a different level of the system:
- **Unit tests** — model logic, geometry, and pure computation
- **Integration tests** — interaction between components
- **Functional tests** — GUI behavior and user interactions, using TestFX  
  (with Monocle for headless execution), running the entire instance of an application

---

## Documentation & Navigation

To make this repository easier to explore, additional documentation is provided in the `/docs` directory.  
Each document focuses on a specific aspect of the project, such as:
- [architectural overview](documentation/architecture_overview.md)
- [module structure](documentation/module_structure.md)
- [GUI features](documentation/gui_features.md)
- [testing strategy](documentation/testing_strategy.md)
- [build and execution](documentation/build_and_execution.md)  
