# RestaurantProject

## Overview

**RestaurantProject** is a simple **Java CLI simulation app** that simulates both **user and admin interactions** in a restaurant system.

### Features

**User side:**

* Login and logout
* Place orders by selecting foods
* Increase account balance
* View order history

**Admin side:**

* Add and manage foods and prices
* View orders from all users
* Access admin panel with a default password `"1234"` (changeable in source files)

The project is designed as a **single executable CLI application** built with **Gradle**.

---

## Prerequisites

Before building and running the project, ensure you have:

1. **Java Development Kit (JDK) 22** installed

   * Verify installation:

     ```bash
     java -version
     ```

     Expected output should indicate `Java 22`.

2. **Gradle** (optional, project includes Gradle wrapper)

   * You can use the included Gradle wrapper without installing Gradle system-wide.
   * Verify Gradle wrapper:

     ```bash
     ./gradlew --version
     ```
---

## Building the Project

1. Open a terminal in the **project root** directory.

2. To **build the executable JAR file**:

   ```bash
   ./gradlew build
   ```

4. The resulting **JAR file** will be located at:

   ```
   app/build/libs/app.jar
   ```

> **Note:** The Gradle wrapper automatically downloads and configures Gradle for your project, so you don’t need a separate Gradle installation.

---

## Running the Executable

To run the built CLI app, use:

```bash
java -jar app/build/libs/app.jar
```

---

## Modifying and Extending

* **Admin password:** Default `"1234"` stored in `Main.java` (can be changed in source).

---

## Dependencies

All dependencies are handled via **Gradle**. By default, this project does not include external libraries. Any future dependencies can be added in `app/build.gradle`:

```gradle
dependencies {
    // Example:
    // implementation 'com.google.guava:guava:32.0.1-jre'
}
```

Then build again with `./gradlew build`.

