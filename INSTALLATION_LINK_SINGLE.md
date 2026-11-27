# Student OS — Single Install Link for Chrome

## The Link
Paste this direct link into Chrome on your Android mobile device:

**[https://github.com/Omprakash-prog03/Daily-note/raw/arena/9b0e1c49-daily-note/app/src/main/assets/install.html](https://github.com/Omprakash-prog03/Daily-note/raw/arena/9b0e1c49-daily-note/app/src/main/assets/install.html)**

> **Note**: Since the actual APK build artifact (`app-release.apk`) is generated in the CI/CD environment (or locally via `./gradlew assembleRelease`), the link above points to the **Installation Landing Page** hosted in the repository. This page provides:
> 1. The **Deep Link** (`studentos://main`) to open the app if already installed.
> 2. The **Direct Download Link** to the APK artifact (once built and uploaded to GitHub Releases).
> 3. The **Engineering Report** reference.

## How it works in Chrome:
1. **Paste the link** into the Chrome address bar.
2. **Landing Page**: Chrome will load the `install.html` page (styled with Student OS design tokens: Indigo Primary, Neutral Background, 16dp rounded card).
3. **Install**: Click the **"Download & Install APK (v1.0.1)"** button. Chrome will prompt for download and installation permissions.
4. **Launch**: After installation, click **"Open Student OS"** (Deep Link `studentos://main`) to start the application.

## Engineering Implementation
- **Asset Path**: `app/src/main/assets/install.html`
- **Deep Link Scheme**: `studentos://main` (configured in `AndroidManifest.xml`)
- **Build Artifact**: `app/build/outputs/apk/release/app-release.apk` (Version 1.0.1)
- **Branch**: `arena/9b0e1c49-daily-note`
- **Commit**: `f5b3a87` + new asset file.

---
**Engineering Team**: Student OS Senior Product Engineering Team
