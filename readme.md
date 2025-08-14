# 💓 PaceBeats — Smart Heart Rate Monitoring for Runners

<img src="https://img.shields.io/badge/Kotlin-Android-purple?style=flat-square" alt="Kotlin Android" /> <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-green?style=flat-square" alt="Jetpack Compose" /> <img src="https://img.shields.io/badge/Samsung%20Health-SDK-blue?style=flat-square" alt="Samsung Health SDK" /> <img src="https://img.shields.io/badge/License-Apache%202.0-lightgrey?style=flat-square" alt="Apache License" />

Welcome to **PaceBeats**, a modern Android application built with **Kotlin** and **Jetpack Compose** that provides real-time heart rate monitoring and pace tracking for runners. Seamlessly integrated with **Samsung Health SDK**, PaceBeats transforms your Galaxy Watch into a powerful fitness companion that adapts to your running rhythm. 🏃‍♂️💓🎯

---

## 🚀 What Is PaceBeats and How It Works

PaceBeats is a native Android app that leverages Samsung Health ecosystem to deliver precise heart rate monitoring and intelligent pace analysis:

### 🔄 Core Workflow

1. **Device Connection**
   - Seamlessly connects with Galaxy Watch (5, 6, 7) via Samsung Health SDK
   - Real-time data streaming with minimal latency

2. **Heart Rate Monitoring**
   - Continuous HR tracking during workouts
   - Inter-beat interval (IBI) analysis for precision
   - Advanced filtering algorithms for noise reduction

3. **Pace Intelligence**
   - GPS-based distance calculation
   - Step counting with stride length calibration
   - Kalman filtering for smooth pace computation

4. **Smart Analytics**
   - Real-time pace categorization (recovery, tempo, interval, sprint)
   - Heart rate zone analysis and trend detection
   - Performance metrics with historical tracking

5. **Modern UI Experience**
   - Dark mode design optimized for outdoor visibility
   - Jetpack Compose reactive interface
   - Clean architecture with MVVM pattern

---

## 📥 How to Clone and Run This Project

### Prerequisites
- **Android Studio** (Arctic Fox or newer)
- **Kotlin 1.8+** and **Gradle 8.0+**
- **Galaxy Watch 5, 6, 7** or compatible Samsung wearable
- **Samsung Health** app installed on phone

### 1. Clone the Repository

```bash
git clone https://github.com/YourUsername/Pacebeats-Kotlin.git
cd Pacebeats-Kotlin
```

### 2. Open in Android Studio

```bash
# Open the project in Android Studio
android-studio .
```

### 3. Build and Install

```bash
# Via terminal
./gradlew mobile:assembleDebug
adb install mobile/build/outputs/apk/debug/mobile-debug.apk

# Or use Android Studio's Run button
```

### 4. Setup Samsung Health

1. Install Samsung Health on your device
2. Pair your Galaxy Watch
3. Grant health permissions in PaceBeats
4. Start monitoring! 🎉

---

## 📂 Project Architecture

```
mobile/
├── src/main/java/com/samsung/health/mobile/
│   ├── data/                    # 🗄️ Data layer (repositories, services)
│   │   ├── auth/               # Authentication data sources
│   │   └── health/             # Samsung Health SDK integration
│   │
│   ├── domain/                 # 🧠 Business logic layer
│   │   ├── auth/               # Login/Register use cases
│   │   └── health/             # Heart rate processing logic
│   │
│   └── presentation/           # 🎨 UI layer (Jetpack Compose)
│       ├── ui/
│       │   ├── auth/           # Sign in/up screens
│       │   ├── welcome/        # Onboarding carousel
│       │   ├── main/           # Heart rate monitoring
│       │   └── components/     # Reusable UI components
│       │
│       ├── viewmodel/          # 🎯 State management
│       ├── navigation/         # 🗺️ App navigation
│       ├── theme/              # 🎨 Design system
│       └── di/                 # 💉 Dependency injection
│
├── res/                        # 📱 Android resources
└── AndroidManifest.xml         # 📋 App configuration
```

---

## 💡 Key Features

- 💓 **Real-Time Heart Rate Monitoring** via Samsung Health SDK
- 📊 **Inter-Beat Interval (IBI) Analysis** for precision tracking  
- 🏃‍♂️ **Smart Pace Detection** with Kalman filtering
- 🌙 **Modern Dark UI** built with Jetpack Compose
- 🔐 **Secure Authentication** with clean architecture
- 📱 **Galaxy Watch Integration** for seamless wearable experience
- 🧠 **MVVM Architecture** with Dagger Hilt DI
- 🎯 **Clean Code Principles** following Android best practices

---

## 🛠️ Tech Stack

**Frontend:**
- Jetpack Compose (Modern UI toolkit)
- Material Design 3 (Latest design system)
- Navigation Compose (Type-safe navigation)

**Backend/Data:**
- Samsung Health SDK (Health data integration)
- Kotlin Coroutines (Asynchronous programming)
- Dagger Hilt (Dependency injection)

**Architecture:**
- Clean Architecture (Separation of concerns)
- MVVM Pattern (Reactive state management) 
- Repository Pattern (Data abstraction)

**Development:**
- Kotlin (100% Kotlin codebase)
- Gradle Kotlin DSL (Build configuration)
- Android Studio (IDE)

---

## 📱 Samsung Health Integration

PaceBeats leverages Samsung Health's powerful ecosystem:

```kotlin
// Example: Real-time heart rate data
@Composable
fun HeartRateMonitor(results: List<TrackedData>) {
    LazyColumn {
        items(results.reversed()) { data ->
            HeartRateItem(
                heartRate = data.hr,
                intervals = data.ibi,
                timestamp = data.timestamp
            )
        }
    }
}
```

---

## 🧪 Testing & Development

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Generate APK for testing
./gradlew assembleDebug
```

---

## 📚 Learn More

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Samsung Health SDK](https://developer.samsung.com/health)
- [Android Clean Architecture Guide](https://developer.android.com/topic/architecture)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

---

## 👥 Development Team

- 🚀 **Ken Patrick Garcia** — Project Leader & Android Developer
- 🎨 **Timothy Forte** — UI/UX Designer & Frontend Developer  
- 💻 **Brian Ashley Papa** — Backend Developer & Samsung Health Integration
- 🔧 **Lanz Corpuz** — Mobile Developer & Quality Assurance

---

## 🌐 Connect with Us

- 🔗 **GitHub Repository:** [PaceBeats-Kotlin](https://github.com/YourUsername/Pacebeats-Kotlin)
- 💼 **LinkedIn:** [Ken Patrick Garcia](https://www.linkedin.com/in/ken-patrick-garcia-ba5430285/)
- 📧 **Contact:** kenpatrick.garcia@example.com

---

## 🏆 Features Roadmap

- [ ] 🎵 Music recommendation based on BPM
- [ ] 📈 Advanced analytics dashboard  
- [ ] ☁️ Cloud sync and backup
- [ ] 👥 Social features and challenges
- [ ] 🏃‍♀️ Multi-sport support
- [ ] 📱 Wear OS companion app

---

## 📄 License

```
Copyright 2023 Samsung Electronics Co., Ltd. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

> Built with 💓 by passionate developers and runners, powered by modern Android development practices and Samsung's innovative health ecosystem.

**Made with ❤️ in the Philippines 🇵🇭**

