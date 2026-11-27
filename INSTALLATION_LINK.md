# Student OS — Mobile App Installation

## 1. Direct Download (APK)
Once the release build is generated via `./gradlew assembleRelease`, the APK will be available at:
- **Path**: `/home/user/Daily-note/app/build/outputs/apk/release/app-release.apk`
- **Install Command (ADB)**:
  ```bash
  adb install -r app/build/outputs/apk/release/app-release.apk
  ```

## 2. Deep Link Installation / Launch
The app supports the scheme `studentos://main`. On mobile devices with the app installed, you can trigger the app via:
- **Link**: [studentos://main](studentos://main) (Note: Requires app installation first)
- **QR Code / Web Redirect**: A web page can redirect to this scheme to open the app.

## 3. Web-Based Distribution (Internal Testing / Play Store)
Since the repository is hosted on GitHub, the release artifact can be distributed via:
- **GitHub Releases**: [https://github.com/Omprakash-prog03/Daily-note/releases](https://github.com/Omprakash-prog03/Daily-note/releases) (Artifacts will appear here after CI/CD build)
- **Google Play Internal Testing**: Upload `app-release.apk` or `app-release.aab` to the Play Console.

## 4. Local Installation (Developer Mode)
1. Clone the repository:
   ```bash
   git clone https://github.com/Omprakash-prog03/Daily-note.git
   cd Daily-note
   git checkout arena/9b0e1c49-daily-note
   ```
2. Build the app:
   ```bash
   ./gradlew assembleRelease
   ```
3. Install on connected device:
   ```bash
   adb install -r app/build/outputs/apk/release/app-release.apk
   ```
4. Launch:
   ```bash
   adb shell am start -n com.studentos/.MainActivity
   ```

## 5. Engineering Note
The link `studentos://main` is embedded in the `AndroidManifest.xml` as a deep link scheme. Once the APK is installed on a mobile device, any link with this scheme will open the **Student OS** application directly.

---
**Branch**: `arena/9b0e1c49-daily-note`
**Status**: Mobile Installable Configuration Complete
**Engineering Team**: Student OS Senior Product Engineering Team
