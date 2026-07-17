# Technical Documentation - Sammy's Schiebepuzzle

This document provides a high-level overview of the application's architecture and logic for future reference.

## 🏗 Project Architecture
The app follows a **Kotlin Multiplatform (KMP)** structure using the **MVVM** pattern.

- **`commonMain`**: The core of the application.
    - **`SlidingPuzzleViewModel.kt`**: Contains the game state (tile positions, move count) and the logic for movement, shuffling, and win detection.
    - **`ui/SlidingPuzzleScreen.kt`**: The main UI entry point, rendering the grid and controls using Jetpack Compose.
    - **`composeResources/`**: Local storage for images (`sammy_*.jpg`).
- **`androidMain`**: Standard Android entry point (`MainActivity`).
- **`desktopMain`**: Windows-specific entry point (`Main.kt`) which handles window creation and initial size configuration (600x850 dp).

## 🧩 Core Logic

### Shuffling Algorithm
To guarantee that the puzzle is always solvable, the app does not use a simple random shuffle. Instead, it starts from a **solved state** and performs **100 random valid moves**. This ensures the resulting state can be traced back to the solution.

### Image Slicing & Focus
The image is rendered on a `Canvas`. To handle different aspect ratios and focus on specific parts of the image:
1.  **Scaling**: The image is scaled to fill the entire 3x3 grid (Crop-to-fill).
2.  **Focus (Bias)**: Each image can have a `verticalBias` and `horizontalBias`.
    - Example: `sammy_1` uses a `verticalBias` of 0.35 to shift the view upwards, ensuring the eyes/face are visible.
3.  **Drawing**: The `Canvas` translates the origin for each tile to draw only the relevant 1/9th section of the scaled image.

### Move Logic
A tile can only move if it is **adjacent** (up, down, left, right) to the "empty slot" (internal index 8). When clicked, the values of the clicked index and the empty index are swapped in the state list.

## 📦 Resource Management
Images are bundled as `DrawableResource` using the Compose Resources plugin.
- Path: `src/commonMain/composeResources/drawable/`
- All images are lowercase and prefixed for organization (e.g., `own_images_sammy_1.jpg`).
