# Walkthrough - Sammy's Schiebepuzzle

The project has been successfully migrated to **Compose Multiplatform**! You can now run "Sammy's Schiebepuzzle" as a native Windows application, bypassing all emulator issues.

## Changes Made

### Project Structure (Multiplatform)
- **`commonMain`**: Contains the shared logic and UI.
    - **`composeResources/drawable/`**: Contains exclusively the personal Sammy images provided by the user.
- **`desktopMain`**: Contains the Windows-specific entry point (`Main.kt`).
- **`androidMain`**: Contains the Android-specific entry point (`MainActivity.kt`).

### Technology Upgrade
- **Compose Resources**: The app now uses the native resource system. Images are bundled with the application, ensuring they load instantly and work without an internet connection.
- **Kotlin Multiplatform (KMP)**: Full support for both Android and Windows Desktop.

### Features (All Platforms)
- **3x3 Sliding Puzzle** with 3 specific, user-provided Sammy images (stored locally).
- **No Web Assets**: All external cat images have been removed for legal safety.
- **Compact UI**: Optimized for Desktop with a 100dp reference image and 380dp puzzle grid.
- **Window Management**: Default size set to 600x850 dp to ensure visibility of all controls.
- **Move Counter** and victory message.
- **Shuffle, Reset, and New Game** functionality.
- **Custom Cropping**: Added a `verticalBias` parameter to ensure images (like `sammy_1`) are cropped perfectly, focusing on the cat's face/eyes instead of just the center.

## How to Run the App on Windows

You can start the game directly from the terminal or using the Gradle tab:

### Option 1: Terminal (Fastest)
Open the terminal at the bottom of Android Studio and type:
```powershell
./gradlew :app:run
```

### Option 2: Gradle Tab
1.  Open the **Gradle** tab on the right side of Android Studio.
2.  Navigate to: `SlidingPuzzleAi` -> `app` -> `Tasks` -> `compose desktop` -> `run`.
3.  Double-click on **run**.

The game will compile and open in a new Windows window. It works completely offline!

## Verification Results
- [x] Specific orange cat images downloaded and integrated as local resources.
- [x] Coil 3 (Network) replaced with native Compose Resources for local assets.
- [x] Project cleaned of all legacy template files, test folders (`test`, `androidTest`), and unused assets.
- [x] Project structure optimized: Only essential source sets (`commonMain`, `androidMain`, `desktopMain`) remain.
- [x] Build and Sync successful.
