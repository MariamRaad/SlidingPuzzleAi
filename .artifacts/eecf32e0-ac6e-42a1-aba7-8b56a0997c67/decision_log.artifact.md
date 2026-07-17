# Decision Changelog - Sliding Puzzle Project

This document tracks the evolution of the Sliding Puzzle application, detailing requirements and the reasoning behind technical choices.

## [2026-07-16] Initial Planning & Setup

### Requirement
**Goal:** Create a 3x3 sliding puzzle with a cute **orange cat** image.
**Requested by:** User.

### Implementation Details
- **Tech Stack:** Jetpack Compose (Modern UI), Kotlin (Logic), Coil (Image Loading).
- **Grid Size:** 3x3 (8 active tiles, 1 empty).
- **Architecture:** MVVM pattern to separate game logic from the UI.
- **Game Flow:** Initial state shows the completed image. User must click "Start/Shuffle" to begin.
- **Features:**
    - Move Counter to track efficiency.
    - "Reset" to restart the current puzzle.
    - "New Game" to fetch a fresh cat image.
    - **Reference Image:** A small thumbnail of the solved puzzle is always visible to help the player.

### Decisions & Reasoning
1. **Using Jetpack Compose:** Chosen for its declarative nature, which makes managing the grid state and animations much simpler than traditional Views.
2. **Coil for Images:** Since there is no local "cat" image in the project yet, using a network-based image loader is the most flexible way to provide a high-quality "cute cat" image without bloating the project size initially.
3. **Image Slicing:** Instead of manually cropping 9 separate images, I will use a single image and offset it within each tile's view using `drawWithContent` or `clip`. This is more efficient and easier to maintain.
4. **Shuffling Algorithm:** A simple random shuffle might lead to unsolvable puzzles. I will implement a "random move" shuffle starting from the solved state to guarantee solvability.

## [2026-07-16] SDK Downgrade for Stability

### Requirement
**Goal:** Ensure the app can be run in the emulator.
**Context:** User encountered Windows Hypervisor Platform (WHPX) issues with the API 36 (Android 16 Preview) emulator.

### Decisions & Reasoning
1. **Downgrade to API 35:** Changed `compileSdk` and `targetSdk` from 36 to 35. API 35 (Android 15) is more stable and has better compatibility with current hypervisor versions. This allows the user to use an API 35 emulator which is less likely to trigger strict WHPX version requirements.

## [2026-07-16] Migration to Compose Multiplatform (Desktop)

### Requirement
**Goal:** Run the app as a native Windows application.
**Context:** Emulator issues (WHPX) made Android testing impossible for the user.
**Requested by:** User.

### Decisions & Reasoning
1. **Compose Multiplatform (Desktop):** Since the app is built with Jetpack Compose, we can target the JVM (Desktop) with minimal code changes. This avoids the need for an emulator entirely.
2. **Coil 3 Upgrade:** Coil 2 is Android-only. Coil 3 supports Kotlin Multiplatform, making it the right choice for cross-platform image loading.
3. **Project Restructuring:** Moving UI and logic to `commonMain` follows the industry standard for shared code in Kotlin Multiplatform.

## [2026-07-16] Quality & Layout Improvements

### Requirement
**Goal:** Solid orange cats only (no white), better window size, and larger reference image.
**Requested by:** User.

### Decisions & Reasoning
1. **Curated List:** To satisfy the "no white on chest/paws" requirement, a curated list of images is safer than random tags.
2. **Fixed Window Size:** Setting a default size in `Main.kt` prevents the "hidden buttons" issue and provides a consistent experience.
3. **Prominent Reference Image:** Moving the reference image out of the header makes it more of a "guide" for the player, which was the user's intent.

## [2026-07-17] Local Resource Integration

### Requirement
**Goal:** Store the cat images directly in the project.
**Context:** User wants to see exactly which resources are used and where they are stored.
**Requested by:** User.

### Decisions & Reasoning
1. **Compose Multiplatform Resources:** Moved the 4 selected orange cat images from remote URLs to `commonMain/composeResources/drawable/`. This ensures the app works offline and provides instant image loading.
2. **Resource-based UI:** Switched from Coil 3 (Network) to the native `painterResource` API for better performance and tighter integration with the project's assets.
3. **Cleaned legacy assets:** Deleted the unused baked goods images and legacy template files to keep the resource folder focused on the puzzle.

## [2026-07-17] Final Polish & Branding

### Requirement
**Goal:** Rename the app to "Sammy's Schiebepuzzle" (German translation) and clean up all remaining template artifacts.
**Requested by:** User.

### Decisions & Reasoning
1. **Localization:** Updated the app name and window titles to German ("Sammy's Schiebepuzzle") to match the in-game button labels.
2. **Resource Cleanup:** Deleted all unused string resources (cupcakes, cookies, etc.) from `strings.xml`.
3. **Theme Consistency:** Renamed the Android theme and application package IDs where applicable to reflect the new name.

## [2026-07-16] Strict Image Filtering

### Requirement
**Goal:** Only show solid orange cats (red/ginger). No white chest/paws. No excavators or tigers.
**Requested by:** User.

### Decisions & Reasoning
1. **Curated Image List:** Instead of relying on a purely random API which often returns white-patched cats or tigers, I am moving to a list of hand-picked Unsplash URLs. This is the only way to guarantee the "no white on chest/paws" requirement.
2. **Refined Tags:** If we do use a fallback API, the tags will be changed to `ginger,cat,pet` with the `/all` (AND) logic to filter out tigers and unrelated equipment.
