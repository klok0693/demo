# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## High‑Level Architecture
```
m_modules/
 ├─ api          ← platform‑agnostic UI contracts ← current module
 ├─ core          ← business logic, ports & adapters 
 ├─ model          ← pure domain data structures
 ├─ realization   ← Guice initialization and DI wiring
 ├─ platform/javafx  ← JavaFX UI implementation
 ├─ platform/swing   ← Swing UI implementation
 ├─ platform/qt      ← Qt Quick UI implementation
 └─ util
```

## api module overview

The `api` module exposes a lightweight graphics API used by other parts of the application. Key components:

| Package                                         | Purpose                                                                                                                                                            |
|-------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `org.example.demo.api.graphics.color`           | Defines immutable color values (`Color`) and utility conversions (`Colors`). Colors are represented in 0‑1 range internally, with factory methods for RGB/RGBA.    |
| `org.example.demo.api.graphics`                 | Core drawing abstractions                                                                                                                                          |
| `org.example.demo.api.graphics.Drawable`        | interface for objects that can be rendered via a `GraphicsContext`                                                                                                 |                                                                                             |
| `org.example.demo.api.graphics.Graphics`        | high‑level façade providing drawing primitives (e.g., shapes, text). It delegates to a `GraphicsPainter` implementation.                                           |                                                                                                                                                                |
| `org.example.demo.api.graphics.GraphicsContext` | holds state such as current color, stroke width, and transformation matrix.                                                                                        |
| `org.example.demo.api.graphics.GraphicsPainter` | concrete renderer that performs the actual pixel operations. The API is intentionally thin so different back‑ends (Swing, JavaFX, custom canvas) can implement it. |

The design follows a **separation of concerns** pattern: `Drawable` objects describe *what* to draw, while `GraphicsContext` and `GraphicsPainter` handle *how* to render. This allows the rest of the application to remain agnostic of the rendering backend.

## Development Notes

- The module uses Lombok (`@Getter`) for boilerplate; ensure your IDE has Lombok support enabled.
- All color values are clamped between 0 and 1 internally; factory methods perform range checks and throw `IllegalArgumentException` on invalid input.
- Brightness manipulation (`darker`, `brighter`, `deriveColor`) uses HSB conversion via the helper class `Colors`. The implementation is straightforward but worth reviewing if you need custom color transformations.

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
