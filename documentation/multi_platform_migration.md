### ♾️ GUI Feature: Multi-Platform UI Experiment

All code was separated on two parts - platform-agnostic and platform dependent. 
From the start, the architecture assumed that **Swing**, **JavaFX**, and **Qt Quick** 
would all be replaceable realizations of the same logic

## ⬡ Platform-Agnostic Design

#### 📐 General principle

The major design idea was to make platform-specific part as small as possible.
Almost all behavior — including interaction rules, tool logic, selection modes, 
rendering intent — is shared. GUI components only performs atomic actions, triggered 
from controller component or transfer user input and almost every GUI element has a 
dedicated controller. It can be counted as **MVVM** pattern - but only for pair 
'View - GUI realization', general application design itself is more *MVC-like*.

I worried, that such tough binding and massive, manually controlled invasion of 
ui design concepts will bring a lot of problem because of platform differences,
but it works surprisingly well in many areas(in reality, the only real problem is UI/UX,
but it will be described later). Looks like I've understood, why tools like *ImGUI* 
so popular

Source code [here](../modules/core/src/main/java/org/example/demo/core/port/ui)

#### 🖼️ GUI

Platform components are **stateless**, they only transfer data/signals from/to controllers.
More about how application is dealing with it's state [here](gui_features.md#-ui-modes--state-machine) 
and [here](architecture_overview.md#-state-holders--state-switching)

Canvas rendering did not cause a lot of problem too. To avoid duplicating drawing logic,
a dedicated [rendering API](../modules/api/src/main/java/org/example/demo/api/graphics) was introduced and moved into a separate api module.
This allowed to reuse the same drawing logic across Swing, JavaFX and Qt implementations

#### ⌨️ Input handling

Chosen architecture design *avoid external input routing* - every event from UI has only **one
enter point**, later navigation/filtering performed manually. From the one side, it limits 
native features of some platform, like JavaFX/Qt, from the other - shift from tree-based MVC 
JavaFX to graph-based MVVM Qt Quick input handling can be a nightmare. Unbound prevents 
a huge amount of problems from platform differences, make migration from Swing to Qt 
much simpler

#### 🔀 Thread Managment

Threads required some attention. All platforms implement sets of executors,
while thread management is also stayed in shared modules. All calls to/from
core logic components are wrapped into background threads to prevent freezing, 
while simple adapter operations that trigger ui changed but not modifying the
model are executed from GUI loop to prevent unnecessary background-foreground
thread's rethrowing. More information about application thread management 
[here](architecture_overview.md#-infrastructure-levels-realization-module)

#### 🔬 Tests

> ⚠️ Qt automation tests are under construction, this section relates to 
> JavaFX and Swing tests

Make multi-platform automation functional tests was one of the most challenging task,
surprisingly. 

The core of the test ecosystem is **Cucumber** as scenario engine and 
abstract [configurators] (to set up app for test), [steps] (operations) 
and [checkers] (check the inner application state), that
used [Robot] and [TestComponentHolder] interfaces. 

All GUI components required unification of their names to being accessible,
so all node IDs used by test robots were moved into a shared class 
[ElementID](../modules/core/src/main/java/org/example/demo/core/port/ui/markup/ElementID.java)

Two main difficulties: 
1) Test framework must respect application's lifecycle, correctly launch/shutdown app
   Fixed by using:
   - [Hooks]
   - Very precise initialization flow. Structure 'AppMain-AppInitializer-GuiLauncher'
     appeared not as a fashion trick
   - Maven config
     - forkCount = 1
     - reuseForks = false
     - parallelMavenExecution = false
     - parallelOptimized=false
   
2) Custom test ecosystem must also be platform-agnostic, so it leads to nested
   complexity, when test must deal with abstract components, being abstract itself,
   while GUI module must implement core and test components. Like in matreska doll
   
   Solution was to pack all agnostic tests into test-jar and place them into
   platform realization, when they being visible to platform implementations. 
   JPMS was strongly agains such tricks, while I have no desire to move tests
   to standalone test module, so Maven ignore JPMS system, but only for tests code.
   In reality, because there is a intermodule 'realization', trick with tests 
   is being performed twice - from 'core' module to 'realization', when they 
   grab some friend and travel further to 'swing'/'javafx' modules. This tests
   travels more than I this year=(

Source code [here](../modules/core/src/test/java/org/example/demo/functional)

#### 🏗️ Build

Java Swing and JavaFX build are almost similar and do not contain heavy logic,
while Qt build is described [beyond]


### 🖥️ Platforms

Platform-specific code manages *GUI lifecycle, event translation, rendering* and other
relative operations

## 🏛️ Java Swing

- Library
- Passive immediate rendering
- Imperative state access during repaint
- Direct-dispatch event handling
- Does not build scene graph
- No CSS / markup
- Layout-object–driven management(bound to node)
- MVC-friendly
- JRE-bundled

#### Lessons from the Trenches
 - GUI elements are initialized from code, which required additional time to build
   the entire UI structure from scratch
 - Canvas mouse input handling also drew some time, but wasn't challenging
 - Headless mode for tests required some configuration
 - Build is simple

Source code [here](../modules/platform/swing/src/main/java/org/example/demo/swing)

## 🎬 JavaFX

- Framework
- Frame-based rendering
- State propagation on repaint(bindings)
- Scene tree for structure, graph for behavior
- FXML markup, CSS styling, JS support
- Event tree, bubbling/filtering
- Imperative layout management(Parent-Child)
- MVC-friendly
- Not part of the JRE(9+)
- Very good designed, half-assed implemented

#### The Scenic Route
 - Canvas mouse input handling drew some time
 - Combination TestFX-JUnit-Cucumber requires some fight for correct 
   application's lifecycle during the tests
 - Build is simple, but forget about JPMS

Source code [here](../modules/platform/javafx/src/main/java/org/example/demo/fx)

## 📡 Qt Quick

- Framework?
- Frame-based rendering
- State propagation on repaint(slots)
- Scene tree for structure, graph for behavior
- QML declarative markup/styling, JS support
- Event graph
- Layout-object–driven management
- MVVM-friendly
- Native C++

#### The Good, The Bad, and The Ugly
 - Layout and styling took some time because of different layout management design
 - MVVM style declarative gymnastic feels like driving a Tesla with a TV Remote when 
   dealing with a custom nodes
 - Java-C++ interaction not so challenging, but drain huge amount of time 
 - Need to remember about resource and thread ownership
 - Cross-platform (Win/Ubuntu) build issues required significantly more effort than 
   the sum of two standalone platform-specific builds

Few words about the migration — Java is bound to a .c file, not .cpp, due to **ABI 
compatibility**. This leads to a situation where a procedural *C interlayer exists between 
two OOP systems*.

**JNI** is used only to launch the JVM, while all communication uses **FFM**: hand-written 
method access and generated code for structs (via **jextract**). The main reason for such 
segregation is not logic-related — I wanted to try the new memory API and deal with 
both variants: separated methods and variable allocation.

Multithreading: the *main Qt thread is attached to the JVM* to avoid conflicts with the 
garbage collector and other JVM internals.

**Maven** is used as the primary build tool. First, it generates Java memory layouts from .c 
files by launching jextract. Next, it assembles a *qt-runtime-jar* with all required 
dependencies and transfers execution to **CMake**, which compiles and links native resources 
into the output folder. Finally, Maven manually fills this folder with Java resources.

The C++ project uses the standard Maven directory structure, so the output is placed 
under target/native.

Source code [here](../modules/platform/qt/src/main/java/org/example/demo/qt)

## 🏁 Summary

A few challenges came from differences in platform lifecycles and event handling.

The most unresolved area is *UI* — markup and styling — and *UX* aspects such as focus behavior. 
*Swing* is configured purely in code; *JavaFX* uses *FXML*, *CSS*, and *JS*; *Qt* relies on *QML* and *JS*. 
Finding a single, standard “source of truth” is not possible, not to mention that UX 
behavior can differ significantly between platforms.

For example, Qt text field focus is much more “greedy” than on Java-based platforms. 
There is no fast or simple solution here. It appears that the only viable approach is 
to manage UI and UX manually, which would require a dedicated engineering subsystem as a 
standalone part of the platform-agnostic module.

While using JavaScript for UX logic can be more or less reliable, styling remains a blank 
area. CSS would require custom parsers for both Swing and Qt. This remains an open problem, 
and as a result, the appearance is sometimes too primitive in the current realization.

## 📎 See Also

- [module structure](documentation/module_structure.md)
- [GUI features](documentation/gui_features.md)
- [architectural overview](documentation/architecture_overview.md)
- [testing strategy](documentation/testing_strategy.md)
- [build and execution](documentation/build_and_execution.md)  