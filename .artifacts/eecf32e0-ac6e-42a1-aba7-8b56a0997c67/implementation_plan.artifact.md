# Implementation Plan - Generate Windows Distributable (No-Cost)

This plan details the steps to package "Sammy's Schiebepuzzle" as a portable Windows application folder. This avoids the need for the WiX Toolset (installers) and works on any Windows PC without requiring Java or the project source.

## User Review Required

> [!NOTE]
> Instead of a single installer file (`.msi` or `.exe` installer), we will create a **portable folder**.
>
> You can zip this folder and share it. The recipient only needs to unzip it and run the application executable inside. This method is free and requires no additional system tools.

## Proposed Changes

### Build Configuration

- No changes to `build.gradle.kts` are strictly required, but I will ensure the package name is correct.

### Execution

- Run the Gradle task: `./gradlew :app:createDistributable`.

## Verification Plan

### Automated Tests
- Confirm that the Gradle task finishes successfully.
- Locate the generated application folder in the `app/build/compose/binaries/main/app/` directory.

### Manual Verification
- Provide the user with the path to the executable within that folder.
