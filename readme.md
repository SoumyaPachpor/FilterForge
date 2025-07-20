# 🧪 FilterForge

**FilterForge** is a clean and modular Java-based image filtering desktop application. Built with Java Swing, it allows users to load images, apply stunning filters, and save results with ease. Think of it as your mini Photoshop filter lab — with full control and extendability.

![App Screenshot](https://your-screenshot-url.com) <!-- Replace with actual image URL if you have one -->

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

## 🏗️ Project Structure

FilterForge/
├── filters/ # Filter logic and transformations
├── gui/ # GUI design using Swing
├── io/ # Image loading/saving utilities
├── util/ # Common utilities
├── output/ # Folder for filtered output images
├── assets/ # Icons or images used in GUI
├── FilterForge.java # Main class
└── README.md # This file

---

## 📦 How to Run

1. Make sure you have **Java 17** or above installed.
2. Clone the repo:
   ```bash
   git clone https://github.com/SoumyaPachpor/FilterForge
   cd FilterForge
   javac -d out filters/*.java gui/*.java io/*.java util/*.java
   java -cp out gui.Main
   ```
