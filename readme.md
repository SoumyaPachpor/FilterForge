# 🧪 FilterForge

**FilterForge** is a clean and modular Java-based image filtering desktop application. Built with Java Swing, it allows users to load images, apply stunning filters, and save results with ease. Think of it as your mini Photoshop filter lab — with full control and extendability.

![App Screenshot]([https://your-screenshot-url.com](https://i.pinimg.com/736x/dd/d1/46/ddd146b2d1d2023533e66fa0c77a3167.jpg))

---

## 🚀 Features

- 🖼️ Load images in popular formats
- 🎨 Apply real-time filters:
  - Grayscale
  - Sepia
  - Invert
  - Brightness/Contrast adjustments
  - [More filters coming soon!]
- 🔧 Plug-and-play filter architecture — easily add new filters
- 🖱️ Minimal, intuitive GUI built with Java Swing
- 💾 Save filtered images with a single click

---


---

## 📦 How to Run

1. Make sure you have **Java 17** or above installed
2. Clone the repo:
   ```bash
   git clone https://github.com/SoumyaPachpor/FilterForge
   cd FilterForge
   javac -d out src/filters/*.java src/gui/*.java src/io/*.java src/util/*.java
   java -cp out gui.MainGUI
