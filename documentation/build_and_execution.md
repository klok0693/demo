## 🔧 Build & Execution

This project uses **Maven** as the primary build tool and relies on a **multi-module setup**.

---

## ☕ Java

### ▶️ Running the Application

### 🗃️ Jar

To build a jar without additional dependencies, run from the root:

```bash
mvn clean verify -Pheadless
```

After a successful build the application JAR can be launched manually:

*JavaFX*
```bash
java -jar modules/platform/javafx/target/javafx-1.2-thin.jar
```

*Java Swing*
```bash
java -jar modules/platform/swing/target/swing-1.2-all.jar
```

Generated jars are used mostly for *JPackager*

#### 📋 Runtime Requirements

The produced artifact is a *thin JAR*. This means that it doesn't
include a bundled JavaFX or other dependencies and *can be shared*
across different operating systems. Generated on Linux, it can run under any
system, as long as it have **preinstalled java21+**, *JavaFX version*
additionally requires **openjfx21+**

### 🗃️ Fat Jar 

> ⚠️ Works for JavaFX version only. Swing's jar has no 'thin' or 'fat' differences  

To build a jar, run from the project's root:

```bash
mvn clean verify -Pheadless
```

After a successful build the JARs can be launched manually:

*JavaFX*
```bash
java -jar modules/platform/javafx/target/javafx-1.2-fat.jar
```

> ⚠️ Included UI render components are platform specific, so generated jar is
> not a multi-platform. Generated under Linux, it would not run under Windows
> and *vice versa*

#### 📋 Runtime Requirements

Preinstalled **java21+**, but **no** need of *openjfx* dependency

Generated jar contains all dependencies, *including JavaFX*, which blow 
it size, through provide the ability to launch application 
on systems, having *java9+* as system java.

*JavaFX's libraries for a jar are taken from the
Maven dependencies*, not from the system path, to ensure, that every developer
have the same runtime environment


### 🗃️ Jpackager

Project can be bundled via **Jpackager**, called on *verify* step if one 
of the dedicated maven's profiles activated:

**JavaFX**
- *package-fx-installer* - created an installer, but do **not** automatically install the app into system
  ```bash
  mvn clean verify -Ppackage-fx-installer -Pheadless
  ```
  
- *package-fx-app-image* - create a runtime image
  ```bash
  mvn clean verify -Ppackage-fx-app-image -Pheadless
  ```

**Java Swing**

- *package-swing-installer* - created an installer, but do **not** automatically install the app into system
  ```bash
  mvn clean verify -Ppackage-swing-installer -Pheadless
  ```

- *package-swing-app-image* - create a runtime image
  ```bash
  mvn clean verify -Ppackage-swing-app-image -Pheadless
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

* Uses a real window
* Intended for local development and debugging
* Allows visual inspection of UI behavior and interactions

This profile is active by default and requires a graphical environment.

```bash
mvn clean verify
```

---

### 🔲 Headless Mode

* Runs application in headless mode
* Intended for CI environments and automated execution
* Enables functional GUI tests without rendering a visible window
* Do not require *xvfb* or any other virtual sreen

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
- Reliable execution of based scenarios

#### 🔒 JVM Isolation

All GUI tests are executed in **separate JVM instances**, enforced by Maven configuration
(forkCount / reuseForks=false).

This is especially important for:
- Application lifecycle correctness
- GUI and functional tests that require clean startup and shutdown
- Preventing shared static or platform state from leaking between tests

---

## ➕ C++

This project contains a *native C++* module built using **CMake**

#### 🚧 Build Requirements

**All platforms:**
 - *CMake* (must be installed and *available in PATH*)
 - *Qt Quick*, version: approximately 6.10+. The path must be set up manually, using
    *qt.toolchain* variable. Example:
    ```
   -Dqt.toolchain=C:/Qt/6.10.1/msvc2022_64
    ```
 - [Jextract](https://jdk.java.net/jextract/), version: 21+. The path must be set up 
   manually, using *jextract.home* variable. Example:
   ```
   -Djextract.home=C:\Users\user0\.jextract\jextract-21
    ```
   
**Linux**
 - [Ninja](https://ninja-build.org/) (must be installed and *available in PATH*)
 - *gcc/g++* - system default usually

**Windows**
 - *Visual Studio 17 2022 (v143)* build tools with *MSVC* compiler
 - *%JAVA_HOME%\bin\server* - must be added to *PATH*, additionally to '%JAVA_HOME%\bin\'

#### ⚙️ Build 

```bash
mvn clean verify -Pheadless -PQt -Djextract.home={path} -Dqt.toolchain={path}
```

#### ▶️ Execution

**Linux**

Navigate to *target\native\build\Debug* and launch 'QtDemo.exe' or run from console

```bash
./QtDemo
```

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

- *package-fx-installer*
- *package-fx-app-image*
- *package-swing-installer*
- *package-swing-app-image*

### Test Profiles

- *skipTests*
- *guiTests*
- *headlessTests*
 
---

## ❯ Command line arguments

There is only one argument, available for now: *-no-custom-ui*, used mostly for tests 
