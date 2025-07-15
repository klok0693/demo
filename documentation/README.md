# GUI Demo

This is a modular JavaFX desktop demo application focused on architecture, modular separation, and GUI framework design. While simple in surface appearance, the application showcases deep attention to internal structure and flexibility.

Users can draw rectangles and circles on a canvas, assign them to layers, edit visual properties (position, size, color), and interact via mouse or keyboard. Shapes are selectable, resizable, and stackable. Tools and states can be switched dynamically. The interface includes a property panel, shape list, and undo/delete operations.

![Selection](screenshot_0.png)

This project was built as a demonstration of clean architecture and maintainable, scalable design—not for production functionality.

---

## ✳ Architecture Highlights & Design

- **Hexagonal (ports & adapters) architecture**:  
  Separation between UI rendering, domain logic, and application orchestration. External interaction flows inward through interfaces, and adapters remain replaceable.

- **Java Platform Module System (JPMS)**:  
  Each module has an explicit `module-info.java` and maintains strict boundaries

- **Dependency Injection with Guice**:  
  Logic, adapters, and controllers are wired with Guice modules. The DI configuration itself is modular and externalized, supporting clean bootstrapping and testability.

---

## ⚙ Under-the-Hood Features

- **Platform-agnostic vs. platform-specific separation**:  
  UI logic (e.g. JavaFX rendering) is isolated from core logic. Domain model and command execution remain independent of the view.

- **Layered canvas rendering**:  
  Shapes exist on named layers. Rendering respects layer order and z-stacking. Tools interact selectively with layers.

- **Switchable tools and UI states**:  
  Interaction modes (e.g. selection, shape creation) are implemented as state machines. State transitions are explicit and deterministic.

- **Command Pattern implementation**:  
  User operations are abstracted into commands with undo/redo capability

- **Thread & event transport abstraction**:  
  All platform threading (e.g. JavaFX run loop) and global key/mouse event handling are wrapped in adapter interfaces.

- **Fail-safe validation & defaults**:  
  Object creation and update flows include precondition checks and fallback defaults to prevent invalid state.

- **Pre-initialization configuration injection**:  
  Application state is seeded from layered config sources (defaults, overrides, and runtime parameters) (GPT is lying, 
- there are only a few simple configs=).

- **Structured logging with markers**:  
  Logging uses SLF4J and custom markers to enable structured, filterable runtime diagnostics.

- **Mixed test types**:  
  The codebase includes one test class for:
  - Unit tests (model and geometry)
  - Integration tests (DI and adapter bindings)
  - Functional tests (GUI actions via TestFX)

- **JavaFX controller and builder factories**:  
  Controllers are injected via Guice; FXML loading is abstracted using factories for better modularity.
