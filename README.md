# My first AI Project - Sammy's Sliding Puzzle 🐈

(July 2026)

A charming 3×3 sliding puzzle game, created in loving memory of my soul cat Sammy (born ~June 2008, first day with us 13 Nov 2008 – 10 Nov 2025) 🧡

Built with Kotlin Multiplatform and Jetpack Compose.


## 📸 Showcase
<!-- ![](Sammy's Schiebepuzzle.gif) -->
<img src="Sammys Schiebepuzzle.gif" width="250" height="500"/>

## Summary
**Sammy's Sliding Puzzle** is a classic brain teaser where pictures of Sammy the Cat are divided into a 3x3 grid. With one empty slot, the player must strategically slide the tiles to reconstruct the full image.

### Features:
- **Game Stats**: A move counter tracks your efficiency.
- **Exclusive Content**: Features 3 curated, high-quality images of Sammy.
- **Smart Cropping**: Images are automatically adjusted so Sammy's face remains the focal point.
- **Fully Offline**: All resources are bundled within the app.
- **Cross-Platform**: Runs natively on Windows Desktop and Android.

---

## 🛠 Tech Stack
- **IDE**: Android Studio 2026.1.2
- **AI Assistant**: Gemini (Model: Gemini 3 Flash Preview)
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose / Compose Multiplatform
- **Shared Code**: Kotlin Multiplatform (KMP)
- **Resources**: Compose Resources (bundled assets)

---

## 🚀 How to Run / Start
You can run the app directly from your terminal using Gradle:

```powershell
# Run the Windows Desktop version
./gradlew :app:run

# Install the Android version (requires connected device/emulator)
./gradlew :app:installDebug
```


## 💡 Learnings with AI
This project was developed in collaboration with an AI assistant. Here are the key takeaways from the development process:

- **Prompting is Key**: Writing clear, descriptive prompts and providing context is crucial for high-quality AI output.
- **Token Efficiency**: Throughout the development, I asked numerous prompts and used about **one-fifth of the available context window**. The fear of "running out of tokens" proved unnecessary for a project of this scale. However, I want to further optimize my workflow in future projects by writing more precise prompts, maintaining cleaner context, and reducing unnecessary token usage, as context efficiency will likely become increasingly important when working with AI systems. 
- **Rapid Prototyping**: A functional, cross-platform application was built from scratch within about **a single day** — a process that would typically take much longer manually.
- **Smart Documentation**: Using AI "Artifacts" for changelogs and documentation saved a significant amount of administrative work, allowing more focus on logic and design.
