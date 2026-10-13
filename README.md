# 🎓 Student Management System (OOP in Java)

A clean, robust, and object-oriented **Student Management System** built using Java. This application demonstrates key **Object-Oriented Programming (OOP)** principles, strict data validation, and a multi-user CLI interface (Owner & User roles).

---

## 🌟 Key Features

### 🔐 Multi-User Authentication
* **Owner Login:** Protected with password authentication (`admin123`).
  * Add new students
  * Delete students by ID
  * Search students
  * Update student details
  * Display all registered students
* **User Login:** Read-only access for guests/users.
  * Search students by ID or Name
  * Logout

### ✅ Input Validation & Security
* **Name, Course, & Department Validation:** Restricts inputs to letters and spaces only (No numbers allowed).
* **Unique ID Check:** Ensures duplicate Student IDs cannot be added.
* **Sex Validation:** Restricted strictly to `M`, `F`, or `O`.
* **Age Validation:** Numeric integer validation within realistic limits (1–120).

---

## 🛠️ Tech Stack & Concepts

* **Language:** Java (JDK 8+)
* **Architecture:** Object-Oriented Programming (OOP)
* **Concepts Used:**
  * Encapsulation (Private fields with getters/setters)
  * Separation of Concerns (`Student`, `StudentManager`, `Main`)
  * Data Structures (`ArrayList`, Dynamic Lists)
  * Regular Expressions (`Regex` for string validation)

---

## 📁 Project Structure

```text
Student-Management-System/
│
├── Student.java          # Model class representing student entity
├── StudentManager.java   # Business logic & array management
└── Main.java             # Entry point with CLI interactive menus
