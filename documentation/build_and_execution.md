## 🔧 Build & Execution

This project uses **Maven** as the primary build tool and relies on a **multi-module setup**.

---

## ▶️ Running the Application

### 🗃️ Jar bundled with FX libs

To build a jar, run from the project's root: 

```bash
mvn clean verify -Pheadless
```

After a successful build the application JAR can be launched manually:
```bash
java -jar modules/javafx/target/javafx-1.0-SNAPSHOT.jar
```

#### 📋 Runtime Requirements

Generated jar doesn't include a bundled JVM, but, *because JavaFX libraries 
aren't part of the JRE anymore, they are added to executable jar*, which blow 
it size, through still keep it fit and provide the ability to launch application 
on systems, having java9+ runtime. 

> ⚠️ Included JavaFX components are platform specific, so generated jar is 
> not a multi-platform. Generated under Linux, it would not run under Windows 
> and *vice versa*

*JavaFX's libraries for a jar are taken from the
Maven dependencies*, not from the system path, to ensure, that every developer
have the same runtime environment

To run the application, the environment must provide only
compatible **java21+ version**

### 🗃️ Thin Jar

To build a jar without additional dependencies, run from the root:

```bash
mvn clean verify -PexcludeFxDeps
```

After a successful build the application JAR can be launched manually:
```bash
java -jar modules/javafx/target/javafx-1.0-SNAPSHOT.jar
```

#### 📋 Runtime Requirements

The produced artifact is a **thin JAR**. This means that it doesn't
include a bundled JVM or JavaFX dependencies and *can be shared*
across different operating systems. Generated on Linux, it can run under any
system, as long as it have **preinstalled java21+ and openjfx21+**

### 🗃️ Jpackager

Project can be bundled via **Jpackager**, called on *verify* step if one 
of the dedicated maven's profiles activated:
- package-installer - created an installer, but do **not** automatically install the app into system
  ```bash
  mvn clean verify -Ppackage-installer
  ```
  
- package-app-image - create a runtime image
  ```bash
  mvn clean verify -Ppackage-app-image
  ```

Generated output *can be founded at /dist package*. 

Two platform types are supported - *Windows* and *Linux*(deb). Jpackage generate artifacts
only related to a specific platform, so it is not possible to create an executable Windows file
under Linux and *vice versa*

#### 🚧 Build requirement

Window's installer required pre-installed [WIX toolset](https://github.com/wixtoolset/wix3/releases)

#### 📋 Runtime Requirements

Both do not require java or any other additional system dependencies 

--- 


## 🔬 Tests

The project defines multiple Maven test's profiles to control how they are executed.

### ⏭️ Skip tests

Skip all tests, unit, integrational and functional

```bash
mvn clean verify -PskipTests
```

### 🖼️ GUI Mode (default)

* Uses a real JavaFX window
* Intended for local development and debugging
* Allows visual inspection of UI behavior and interactions

This profile is active by default and requires a graphical environment.

```bash
mvn clean verify
```

---

### 🔲 Headless Mode

* Runs JavaFX without a display using **Monocle**
* Intended for CI environments and automated execution
* Enables functional GUI tests without rendering a visible window

To build and execute the project in headless mode:

```bash
mvn clean verify -Pheadless
```
---

## ✓ Test Execution Strategy

Different test types are executed at different build phases:
- Unit tests — executed via *Surefire*
- Integration and functional tests — executed via *Failsafe*

This separation allows:
- Controlled JVM restarts for GUI tests
- Isolation of application lifecycle per test
- Reliable execution of JavaFX-based scenarios

#### 🔒 JVM Isolation

All tests are executed in **separate JVM instances**, enforced by Maven configuration
(forkCount / reuseForks=false).

This is especially important for:
- JavaFX application lifecycle correctness
- GUI and functional tests that require clean startup and shutdown
- Preventing shared static or platform state from leaking between tests

---

## 👤 Profiles

Below is a description of *all available profiles*, 
while many of them were used in previous sections.

### Operating system profiles

Linked to an OS type and contains system-specific properties:
- *windows* 
- *linux* - belongs to .deb systems

Activated automatically according to a host's system. 
Not for using with maven's commands

### Build profiles

- *excludeFxDeps*
- *package-installer*
- *package-app-image*

### Test Profiles

- *skipTests*
- *guiTests*
- *headlessTests*
 
