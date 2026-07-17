# Fix "Unresolved reference 'compose'" in build.gradle.kts

The error `Unresolved reference 'compose'` in `app/build.gradle.kts` suggests that the Gradle Version Catalog accessor `libs.plugins.compose.compiler` is not being generated or resolved correctly. This often happens due to naming collisions or issues with how Gradle groups accessors in the version catalog.

I will rename the `compose-compiler` plugin in the version catalog to `kotlin-compose`. This will change the accessor to `libs.plugins.kotlin.compose`, which avoids potential conflicts with the name `compose` and groups it under the existing `kotlin` plugin group.

## Proposed Changes

### [Component] Gradle Configuration

#### [MODIFY] [libs.versions.toml](file:///D:/Projects/SlidingPuzzleAi/gradle/libs.versions.toml)
Rename `compose-compiler` to `kotlin-compose` in the `[plugins]` section.

#### [MODIFY] [build.gradle.kts](file:///D:/Projects/SlidingPuzzleAi/build.gradle.kts) (root)
Update the plugin alias to use `libs.plugins.kotlin.compose`.

#### [MODIFY] [app/build.gradle.kts](file:///D:/Projects/SlidingPuzzleAi/app/build.gradle.kts)
Update the plugin alias to use `libs.plugins.kotlin.compose`.

## Verification Plan

### Automated Tests
- Run Gradle sync to ensure the references are resolved.
- Run `:app:assembleDebug` to verify the build.

### Manual Verification
- Check that the IDE no longer shows the "Unresolved reference" error.
