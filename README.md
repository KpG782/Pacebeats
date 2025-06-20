# 🎧 PaceBeats — Adaptive Music Recommender for Runners

Welcome to **PaceBeats**, a mobile application built using **React Native (Expo)** that dynamically recommends music based on your running pace and heartbeat. Powered by real-time IoT integration and hybrid recommendation algorithms, PaceBeats enhances your workout experience by syncing the perfect beat to your stride. 🏃‍♂️❤️🎶

---

## 📥 How to Clone This Project

Follow the steps below to get a local copy of the app running on your machine.

### 1. Clone the Repository

```bash
git clone https://github.com/KpG782/Pacebeats.git
cd Pacebeats
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Start the Development Server

```bash
npx expo start
```

You can then launch the app via:
- 📱 **Expo Go** (scan the QR code)
- 🤖 **Android Emulator**
- 🍎 **iOS Simulator**
- 🔧 **Development build** (for full native access)

---

## 📂 Project Structure

```
PaceBeats/
├── app/                    # 📱 Main app screens (uses Expo Router)
├── assets/                 # 🎵 Music samples, icons, images
├── components/             # 🧩 Reusable UI components
├── constants/              # 📌 Static values (colors, fonts, etc.)
├── hooks/                  # 🧠 Custom React hooks
├── utils/                  # 🧪 Helper functions and logic
├── services/               # 🔗 APIs and data communication (e.g., Health Connect)
├── App.js                  # 🧠 App entry point
├── package.json            # 📃 Project metadata
├── vite.config.js          # ⚙️ Vite/Expo configuration
└── README.md               # 📘 You're here!
```

---

## 💡 Key Features

- 🏃‍♂️ **Real-Time Heart Rate Monitoring** (IoT-Integrated)
- 🎶 **Music Recommendations Based on BPM/Pace**
- 🧠 **Hybrid Filtering Algorithm** (Rule-Based + Content-Based)
- 🌙 **Dark Mode Design** with Custom UI
- 🔌 **Health Connect API Integration** for Android

---

## 📦 Reset Project (Optional)

To reset the app to a clean state:

```bash
npm run reset-project
```

This moves the starter code to `app-example/` and creates a fresh `app/` directory.

---

## 📚 Learn More

- [Expo Documentation](https://docs.expo.dev/)
- [React Native Docs](https://reactnative.dev/)
- [Health Connect Integration](https://developer.android.com/health-and-fitness/guides/health-connect)

---

## 🌐 Connect with Us

- 🔗 GitHub Repo: [https://github.com/KpG782/Pacebeats](https://github.com/KpG782/Pacebeats)
- 💼 LinkedIn: [Ken Patrick Garcia](https://www.linkedin.com/in/ken-patrick-garcia-ba5430285/)
- 👥 Contributors: Ken Garcia, Timothy Forte, Lanz Corpuz, Brian Ashley

---

## 🧠 License

MIT License. Feel free to fork, contribute, or remix this project for your own research and passion projects!

---

> Built with ❤️ by passionate runners and developers from the University of Makati.
