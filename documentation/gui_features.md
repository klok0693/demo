## 🎨 GUI Features & Interaction Model

This document describes the **GUI structure, behavior, and interaction model** of the application.  
It intentionally avoids focusing on JavaFX or Swing as a technology and instead explains **how the GUI is designed 
and behaves conceptually**.

The GUI package is designed to be **platform-agnostic**. There are two realizations: 
*JavaFX* and *Swing*. The same concepts are intended to be reusable for other UI frameworks (e.g. **Qt**).

---

## 🧩 Canvas Architecture

The canvas is the central interaction surface of the application.  
Its logic is organized into **explicit layers**, each with a well-defined responsibility.

### 🗂️ Canvas Layers

1. **Background layer**
    - Responsible for static background rendering
    - Grid, background color, and non-interactive visuals

2. **Shapes layer**
    - Renders model-backed shapes
    - Reflects current model state without owning it

3. **Tools layer**
    - Handles interaction visuals
    - Selection frames, drag previews, insertion hints, etc.
    - May render temporary or auxiliary elements

Each canvas layer is itself **hierarchical**. Layers can contain **sub-layers**, forming a recursive structure that
explicitly controls render order and visual stacking of elements. This makes ordering rules deterministic and 
manageable even as the number of visual elements grows. Direct manipulation of layer position is intentionally limited: 
only elements belonging to the shapes layer can change their layer placement explicitly.

## 🧪 GUI Feature: Multi-Platform UI Experiment

I have long been interested in how difficult it would be to migrate an application 
from one UI platform to another—especially given how many Swing applications still 
wait for a modern UI.

Surprisingly, the migration itself was not as hard as expected. Separating components 
and avoiding logic duplication turned out to be very manageable. Almost all 
logic—including most UI behavior—is shared and reused across platforms. This approach 
proved highly effective: the overall structure remained nearly unchanged, and very 
few bugs appeared during the migration.

Rendering did not cause a lot of problem too. To avoid duplicating drawing logic, 
a dedicated rendering API was introduced and moved into a separate api module, while 
the core logic remained platform-agnostic. This allowed both JavaFX and Swing 
implementations to reuse the same rendering intent with different backends.

The challenges came from differences in platform lifecycles, event handling, and 
underlying behavior. JavaFX follows a frame-driven rendering model, while Swing 
relies on passive repainting and the EDT. Some UI and UX aspects were sensitive 
to these differences, but they were resolved.

The most unresolved area is markup and styling. Swing lacks native CSS support, 
and defining layouts and styles purely in code is verbose and difficult to maintain. 
Ideally, UI appearance should come from a single declarative source. This remains 
an open problem, and as a result, the appearance sometimes too primitive in 
realization

### 🖱️ Input Handling

All input events (mouse, keyboard) are intercepted by a **canvas controller**.  
The controller does not interpret events itself; instead, it **delegates them to the active tool**.

### 🛠️ Tools

The following tools are currently implemented:
- **SelectionTool** — selecting shapes and handling selection logic
- **DraggableTool** — dragging and repositioning shapes
- **InsertTool** — inserting new shapes into the canvas

---

## 🔁 UI Modes & State Machine

The UI is structured around **explicit interaction modes**.

### 🎛️ Mode-Based UI Behavior

All UI changes such as:
- enabling / disabling components
- visibility changes
- focus handling

are performed **only in relation to a specific UI mode**.

This transforms UI behavior into a **state machine** with:
- clear transitions
- explicit rules
- predictable side effects

### 📌 Existing Modes

Currently implemented modes include:
- **SingleSelectionMode**
- **MultipleSelectionMode**
- **InsertMode**

Not every UI change requires a mode switch.  
Each mode allows a controlled amount of variability to avoid an explosion of states.

Conceptually, mods can be mixed together, while it isn't implemented for now

### 🧭 Mode Switching via Visitor Pattern

To manage increasing complexity, mode transitions are implemented using a **Visitor pattern**:
- each UI node defines its own rules for reacting to mode changes
- mode transitions are applied structurally and consistently
- prevents scattered conditional logic

This design keeps mode-related behavior localized and scalable.

---

## 🧠 State Separation: Model vs UI

The GUI package uses **strict state separation**.

### 📦 Model State

- Provided to the UI as **immutable views**
- UI components cannot modify model data directly
- All changes must be requested via logic components

This enforces a unidirectional flow:  
**User input → UI signal → Logic → Model update → UI refresh**

### 🧩 UI State

The UI maintains its own internal state, such as:
- active mode
- shape type to insert
- temporary interaction flags

This state:
- is fully mutable inside the UI package
- is **not visible outside the UI package**
- does not leak into core logic

Together, this forms a layered state model:
- **Model state** — authoritative, domain-level
- **UI state** — interaction and presentation-level

---

## ⚙️ Multithreading & UI Safety

The GUI package strictly respects the rendering thread of the platform.

### 🧵 Threading Model

- All UI updates occur on the render thread
- Non-UI operations are executed in background threads
- Communication with UI components is always non-blocking

JavaFX-specific mechanisms (e.g. `Task`) are used only as an implementation detail.

The GUI logic itself is written in a way that:
- assumes asynchronous execution
- avoids direct thread management
- remains portable to other UI frameworks

---

## 🔌 Ports & Adapters in the UI Layer

The UI package itself is internally structured using **ports and adapters**.

### 🔄 Ports

- Transfer signals and data from/into the outside world
- Do not contain decision-making logic
- Represent *what* happened, not *how to react*

### 🧠 Adapters

- Interpret incoming signals
- Decide how to proceed
- May:
    - handle UI-local actions (e.g. mode switching)
    - forward requests to application logic

A useful analogy:
> Ports are translators in an international company; adapters are the people who decide what to do with the translated information.

This separation keeps UI logic explicit and prevents accidental coupling.

---

## 🧭 Guided UI Updates

UI updates are performed in a **strictly guided manner**.

### 🔁 Update Flow

- UI components are updated **only via an explicit `update()` call**
- Updates are triggered **from parent components only**
- Child components never update themselves independently

This prevents:
- uncontrolled cascading updates
- event avalanches
- hidden side effects from property listeners

### 🧼 Stateless Rendering Principle

If a parameter affects rendering behavior:
- it is stored in **UI state** or came from **Model state**
- not inside the rendering component itself

Updatable components are therefore *effectively stateless* with respect to appearance.  
They may have mutable fields, but:
- they are reinitialized during `update()`, or
- they do not affect rendering

This makes UI behavior predictable and testable.

---

## 🧱 Composite UI Components

Some UI elements have a **non-trivial internal structure**, composed of multiple subcomponents.
Like **SelectionFrame** tool

---

## 🧩 JavaFX-Specific Realization Notes

While the GUI design is framework-agnostic, the JavaFX realization includes some platform-specific techniques.

### 🧬 Dependency Injection in JavaFX

The DI container is integrated into:
- `ControllerFactory`
- `NodeBuilderFactory`

This allows:
- dependency injection in JavaFX controllers
- consistent construction of UI components
- alignment between UI creation and application configuration

The use of DI in JavaFX is treated as an implementation detail, not a design dependency.

## ⌨️ Keyboard

Keyboard input is handled as an **independent input channel**, loosely coupled to UI components, while still sharing the 
same GUI platform as mouse and visual controls—allowing multiple packages to use the same tech stack without mixing 
responsibilities. This means that **application concerns are not confined to a single package**; instead, multiple 
packages can be composed around the same purpose