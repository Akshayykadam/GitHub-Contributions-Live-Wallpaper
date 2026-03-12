<div align="center">
# CodeCalendar 📅
  
  **A beautiful, premium Android Live Wallpaper that brings your GitHub Contributions graph right to your home screen.**
  
  [![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
  [![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  
</div>

## ✨ Features

- **Minimalist Dot Grid:** Replaces standard blocky github squares with an elegant, perfectly rounded circular dot grid.
- **Current Year Overview:** Maps 365 days correctly left-to-right, anchoring January 1st to the top left.
- **Zero API Tokens Required:** Powered by a smart HTML scraping engine on the backend. No authentication needed—just type your username.
- **Live Annual Progress:** Automatically calculates the current day of the year to formulate an elegant `[X] Days Left • [X]%` progress indicator below the graph.
- **Background Syncing:** Uses Android WorkManager to reliably and efficiently pull latest commits every 6 hours without draining your battery.
- **Clock Safe Area:** Automatically pushes the wallpaper down ~30% from the top margin, creating beautiful breathing room for Lock Screen clocks (e.g. Google Pixel series).
- **Multiple Premium Palettes:**
  - 🟠 **Premium Orange:** Rich dark greys with vibrant orange highlights.
  - ⚪ **Monochrome:** Pitch black space with pure white highlight dots.
  - 🟢 **Classic Green:** The iconic GitHub greens overlayed on an OLED-friendly pitch black background.

## Screenshot
<p align="center">
  <img src="https://github.com/user-attachments/assets/95098cc6-6679-4708-9afd-eafec66cad79" width="300"/>
</p>


## 🚀 Installation 

Since the app isn't on the Play Store yet, you can compile and install it directly via CLI.

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/codecalendar.git
   cd codecalendar
   ```

2. Build the Debug APK using Gradle:
   ```bash
   ./gradlew assembleDebug
   ```

3. Install the APK to your connected device:
   ```bash
   adb install "app/build/outputs/apk/debug/app-debug.apk"
   ```

## ⚙️ How to Setup

1. Open **CodeCalendar** from your Android App Drawer.
2. Enter your public **GitHub Username**.
3. Select your preferred color palette (Orange, Monochrome, or Green).
4. Tap **Verify & Link Account**. 
5. Once your account is verified, exit to the Launcher.
6. Long-press your home screen -> Select **Wallpapers** -> **Live Wallpapers**.
7. Select **CodeCalendar** and hit **Set Wallpaper**.

## 🛠️ Tech Stack 
- **Language:** Kotlin
- **Architecture Base:** Modern MVVM-friendly layers
- **Network Layer:** Custom Regex HTML Parser Service using Retrofit wrapper patterns.
- **Persistence Layer:** Room Database & PreferenceManager
- **Background Processes:** WorkManager & Android `WallpaperService`
- **Dependency Injection:** Hilt
- **Graphics Engine:** Hardware accelerated Android `Canvas` rendering.

## 🤝 Contribution

Contributions, issues and feature requests are welcome! 

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---
<p align="center">
  Built passionately for developers who never stop shipping.
</p>
