# SlidingPuzzleAi
My first AI Project - Sammy's Schiebepuzzle 🐈

(Juli 2026)

A charming 3x3 sliding puzzle game featuring **Sammy the Cat**. Built with Kotlin Multiplatform and Jetpack Compose.


## 📸 Showcase
<!-- ![](Sammy's Schiebepuzzle.gif) -->
<img src="Sammys Schiebepuzzle.gif" width="250" height="500"/>

## 🇩🇪 Zusammenfassung (Deutsch)
**Sammy's Schiebepuzzle** ist ein klassisches Denkspiel, bei dem ein Bild von Sammy der Katze in 9 Teile zerlegt wird. Ein Feld bleibt frei, und der Spieler muss die anderen Kacheln geschickt verschieben, um das Originalbild wiederherzustellen.

### Features:
- **Exklusive Bilder**: Enthält 3 handverlesene, hochauflösende Bilder von Sammy.
- **Plattformübergreifend**: Läuft nativ auf Windows (Desktop) und Android.
- **Intelligentes Cropping**: Die Bilder werden automatisch so zentriert, dass Sammys Gesicht immer im Fokus steht.
- **Spiel-Statistiken**: Ein Zugzähler zeigt dir, wie effizient du das Puzzle gelöst hast.
- **Offline-First**: Alle Bilder sind lokal in der App gespeichert.

---

## 🇺🇸 Summary (English)
**Sammy's Schiebepuzzle** is a classic brain teaser where a picture of Sammy the Cat is divided into a 3x3 grid. With one empty slot, the player must strategically slide the tiles to reconstruct the full image.

### Features:
- **Exclusive Content**: Features 3 curated, high-quality images of Sammy.
- **Cross-Platform**: Runs natively on Windows Desktop and Android.
- **Smart Cropping**: Images are automatically adjusted so Sammy's face remains the focal point.
- **Game Stats**: A move counter tracks your efficiency.
- **Fully Offline**: All resources are bundled within the app.

---

## 🛠 Tech Stack
- **IDE**: Android Studio 2026.1.2
- **AI Assistant**: Gemini (Model: Gemini 3 Flash Preview)
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose / Compose Multiplatform
- **Architecture**: MVVM (Model-View-ViewModel)
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
- **Token Efficiency**: Throughout the development, I asked numerous prompts and used approximately **200k tokens** (out of a 1M limit). The fear of "running out of tokens" proved unnecessary for a project of this scale.
- **Rapid Prototyping**: A functional, cross-platform application was built from scratch within about **a single day** — a process that would typically take much longer manually.
- **Smart Documentation**: Using AI "Artifacts" for changelogs and documentation saved a significant amount of administrative work, allowing more focus on logic and design.
