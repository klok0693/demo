## 🧭 Architectural Overview

This document describes the **architectural principles and structural decisions** behind the application.
The focus is on *how responsibilities are separated*, *how information flows*, and *why certain abstractions exist*.

The application is intentionally designed to explore architectural ideas under realistic constraints rather than to optimize for minimalism.

---

## 🧱 Architectural Style: Hexagonal Architecture

The application is built around **Hexagonal Architecture (Ports & Adapters)**.

At a high level:
- **Core logic** defines *what* the application does
- **Ports** define *how the outside world can interact with it*
- **Adapters** decide *how those interactions are actually realized*

### Scheme

![Selection](not_found.png)

---

## 📦 Domain Model & Data Sources

### Model vs Data Source

Although the `model` module contains domain classes, **the model state is not treated as a special or privileged data source**.

From the application’s perspective:
- in-memory model state
- files
- databases
- external services

are conceptually equivalent **data providers**.

The application logic does not depend on *where* data comes from, only on *what it represents*.

---

## 🧠 Controllers, Ports & Adapters

Between **logic** and **adapters**, there is an explicit **controller layer**.

### Controllers

Controllers:
- manage information flow
- validate incoming data
- act as a stable boundary between intent and execution

They do not depend on concrete adapters.

---

### Adapters by Purpose, Not by Source

Adapters are designed **by purpose**, not by data source.

There is **no one-to-one relationship** between:
- controllers
- adapters
- ports

Although in practice many are connected only once, the architecture does not assume that.

#### Example: Clipboard

A single clipboard adapter may work with:
- **system clipboard** (text-based, OS-level)
- **internal clipboard** (model entities, application-level)

The controller interacts with a *clipboard abstraction*.
The adapter decides:
- where data should go
- how to translate it
- which storage to use

This keeps logic agnostic of implementation details.

---

## 🧩 Operational Context (Non-Model State)

In addition to model data, the application maintains an **operational context**.

This context stores data required during the application’s working cycle, but not belonging to the domain model.

Current examples:
- internal clipboard

Characteristics:
- treated as a data storage, just like model state
- has its own ports and adapters
- participates in the same architectural flow

Operational data is **not less important** than model data — it is simply different in nature.

---

## 🗃️ State Holders & State Switching

### Model State Holder

Model state is stored in a **ModelStateHolder**. Holder can store multiple states at once

Key detail:
- `ModelState` and `ModelStateHolder` implement the **same interface**

This allows:
- injecting the holder wherever a state is expected
- swapping entire model states transparently
- supporting multiple active states without changing consumers

This design enables:
- fast state switching (e.g. tabs)
- future branching or versioning
- isolation of model instances

> From the perspective of the application, *nothing changes* when the active state changes.

---

### Operational State Holder

Operational state has its own holder with the same principle.

This separation enables scenarios like:
- copying data in one tab
- switching model state
- pasting data into another tab

For now, operational context switching is not implemented, but the structure allows it.

A future extension could include:
- per-tab undo/redo stacks
- isolated operational memory per workspace

---

## 🧾 Command Pattern & Undo Support

All changes to model state are performed using the **Command Pattern**.

Characteristics:
- every sensitive operation is encapsulated as a command
- commands are queued
- undo is supported by reversing executed commands

Important design choice:
- commands **do not hold references to model objects**
- all required data is stored explicitly

This prevents:
- memory leaks
- accidental retention of outdated objects
- cross-state corruption

Redo is intentionally not implemented yet and may remain unsupported.

---

## 🔗 Logic Interaction & Cycles

Logic components are allowed to call each other.

Example:
- `ClipboardProcessor` may call `ShapeProcessor`

This enables:
- rich logic graphs
- potentially dynamic composition (if sometimes I find the time to implement it)

However, it introduces a risk of **logic cycles**.

The long-term solution would be introducing **internal ports for logic interactions**, but this is considered future work.

---

## ✅ Validation & Safety

Input validation is applied consistently to ensure:
- internal state remains valid
- partial or invalid input does not corrupt the system

The system favors:
- fail-safe behavior
- controlled defaults
- predictable recovery paths

---

## 🪵 Logging & Observability

Logging is structured and tagged.

Features:
- logs are marked with semantic tags
- easier tracing across layers and threads
- error logs are written to permanent storage

This ensures:
- errors are not lost
- post-mortem analysis is possible
- behavior can be reconstructed from logs

---

## 🏗️ Infrastructure Levels (Realization Module)

Some logic is required for the application to work but does **not belong to business logic**.

This includes *how* things are done, not *what* is done.

Such logic is separated into **infrastructure levels**, implemented in the `realization` module.

---

### 🧵 Thread Level

The thread level:
- defines the boundary between synchronous and asynchronous execution
- owns the concept of threads
- wraps calls transparently

Example:
- all calls from UI are wrapped into appropriate threads
- business logic remains unaware of threading

From the core’s perspective, execution appears synchronous and direct.

---

### 📡 Event Level

Not all events are part of the domain model. Such non-model events live in
dedicated **transport level**. This level:
- wraps direct calls into explicit events
- routes events to receivers
- unwraps and executes logic

From business logic perspective:
- it looks like a direct method call

In reality:
- execution may be delayed
- grouped
- replaced
- or skipped entirely

This level enables:
- delayed execution
- event coalescing
- cancellation
- it is the best place to implement reactive programming! Through, it is not added yet(

Currently, a mock event channel is used.
A full event system may be integrated later.

---

## 🧬 Dependency Injection & Proxies

All infrastructure levels are implemented using **Dependency Injection**.

Key rules:
- all components depend on interfaces
- original implementations implement those interfaces
- level components also implement the same interface
- original component is wrapped inside the level component

This is a classic **Proxy pattern**.

Important detail:
- proxy constructors depend on interfaces, not concrete classes

This enables:
- chaining multiple levels
- composing behavior like a *matryoshka doll*
- adding new levels without modifying existing logic

Planned future levels include:
- health checks
- transaction handling
- error recovery layers
