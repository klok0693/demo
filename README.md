# Purpose & Scope

This repository is **a technology- and architecture-driven project, used as a personal playground**  
to explore, test, and combine ideas, libraries, architectural approaches, and testing strategies  
that I find interesting or valuable from an engineering perspective.

While the project is primarily technical in nature, it can also be used as a demonstration project.  
It was not created to showcase a specific library or a predefined technology stack. Instead, it brings  
together a broad set of concepts and tools within a single, coherent codebase. The choice to work on  
one relatively complex project, rather than multiple small and isolated examples, was intentional: it  
allows different ideas to interact under more realistic constraints.

To make navigation easier, this README provides a **high-level overview** of the project, while more detailed  
explanations of specific aspects are available in dedicated documents listed below. The GitHub version of  
the project is kept in a **clean, buildable, and stable state**, even though local development may involve  
experiments and refactoring.

> ⚠️ Some parts of the codebase may appear **overengineered** for the problems they solve.  
> This is intentional: the focus is on learning, comparison, and validation of approaches, rather than  
> minimizing code or optimizing for a specific business use case.
>
> As a result, the project is primarily useful for **technical readers** rather than as a showcase of  
> end-user functionality.

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

## What This Project Demonstrates

This project demonstrates a range of architectural, design, and testing concepts, explored together  
within a single codebase:

### 🧱 Architecture & Design
- **Hexagonal (Ports & Adapters) architecture**
- **Strong modular separation, using Maven multi-module setup and JPMS**, with a clear distinction between:
  - platform-agnostic core logic
  - platform-specific implementations (JavaFX)
- A developed **domain model**, kept independent from UI and infrastructure concerns
- **Events orchestration** separated from business logic, avoiding UI-driven control flow
- Clear boundaries between **synchronous and asynchronous execution**, explicitly modeled and kept out of core logic

### 🔌 Dependency Management
- **Dependency Injection (Guice)**, including:
  - runtime bindings
  - test-specific configuration
  - pre-initialization dependency setup

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
