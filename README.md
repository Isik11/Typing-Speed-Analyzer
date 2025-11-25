# Typing Speed Analyzer (JavaFX)

A JavaFX desktop app that measures typing speed (WPM) and accuracy with a clean GUI.

---

## Features
- Real-time typing speed (WPM) and accuracy tracking
- Random word generation from a custom word list
- User-friendly JavaFX interface built with FXML and CSS
- Displays correct/incorrect word feedback visually

---

## Tech Stack
- **Java 21**, **JavaFX 21**
- **Maven** (for dependency management)
- **IntelliJ IDEA**
- **ControlsFX**, **Apache Commons Lang**

---

## 📁 Project Structure

Typing-Speed-Analyzer/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── data/
│       │   │   └── *.txt                # Saved typing session results
│       │   └── sample/typer4/
│       │       ├── Controller.java
│       │       ├── FileHandling.java
│       │       ├── GameController.java
│       │       ├── Main.java            # Application entry point
│       │       └── PopUpController.java
│       │
│       └── resources/
│           └── sample/typer4/
│               ├── images/
│               │   ├── EZZ.png
│               │   ├── Wrong.png
│               │   └── green_double_circle_check_mark.jpg
│               ├── game.fxml
│               ├── popup.fxml
│               ├── sample.fxml
│               └── style.css
│
├── pom.xml             # Maven configuration
├── README.md           # Project documentation
├── wordsList           # Word dataset used in typing test
└── username.txt        # Stores user’s saved name

---
## 🖼️ Screenshots
### 🖥️ Main Window — Real-Time Typing Metrics Dashboard
 <img width="300" height="400" alt="image" src="https://github.com/user-attachments/assets/6bf8df15-bb21-4649-a50f-bd0b8e28103c" />
 <br>
### 🧪 Typing Session Interface — Real-Time Accuracy, WPM & Timer Display

 <img width="1134" height="874" alt="Screenshot 2025-11-25 at 2 08 24 PM" src="https://github.com/user-attachments/assets/1bf6db0c-21e0-45f3-89ce-c1cbb21a4ae4" />



