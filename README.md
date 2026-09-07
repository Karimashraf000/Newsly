# 📰 NewsApp

A modern **Android News Application** built with **Kotlin** and **Jetpack Compose**.

The project is currently **under development** and is being built as a learning project to practice modern Android development concepts, including **Jetpack Compose, MVVM, Retrofit, Repository Pattern, StateFlow, and Room**.

---

## 🚧 Project Status

**In Development 🚧**

The core project structure and networking layer are currently being implemented. More features and UI improvements will be added progressively.

---

## ✨ Planned Features

* 🏠 Browse the latest news
* 🗂️ Browse news by category
* 🔍 Search for articles
* 📖 View full article details
* 🔖 Bookmark/save articles
* 💾 Store bookmarked articles locally
* ⏳ Loading and error states
* 📱 Modern Jetpack Compose UI
* 🌙 Dark/Light theme support

---

## 🛠️ Tech Stack

| Technology             | Usage                              |
| ---------------------- | ---------------------------------- |
| **Kotlin**             | Primary programming language       |
| **Jetpack Compose**    | UI development                     |
| **MVVM**               | Application architecture           |
| **Retrofit**           | REST API communication             |
| **OkHttp**             | HTTP client and networking         |
| **Gson**               | JSON serialization/deserialization |
| **StateFlow**          | UI state management                |
| **ViewModel**          | Business/UI logic                  |
| **Room**               | Local database                     |
| **Coroutines**         | Asynchronous programming           |
| **Navigation Compose** | Screen navigation                  |

---

## 🏗️ Architecture

The project follows the **MVVM architecture** with a separation between the data and UI layers.

```text
com.example.newsapp
│
├── data
│   ├── model
│   │   ├── Article.kt
│   │   └── NewsResponse.kt
│   │
│   ├── remote
│   │   ├── NewsApiService.kt
│   │   └── RetrofitInstance.kt
│   │
│   ├── local
│   │   └── NewsDatabase.kt
│   │
│   └── repository
│       └── NewsRepository.kt
│
├── ui
│   ├── home
│   │   ├── HomeScreen.kt
│   │   ├── HomeViewModel.kt
│   │   ├── HomeUiState.kt
│   │   └── HomeViewModelFactory.kt
│   │
│   ├── details
│   │   └── DetailsScreen.kt
│   │
│   ├── search
│   │   └── SearchScreen.kt
│   │
│   ├── bookmarks
│   │   └── BookmarksScreen.kt
│   │
│   ├── components
│   │   ├── NewsCard.kt
│   │   ├── CategoryChip.kt
│   │   └── LoadingScreen.kt
│   │
│   ├── onBoarding
│   │   └── OnBoardingScreen.kt
│   │   └── OnBoardingPage.kt
│   │
│   └── utils 
|         ├── constants 
|         │   ├── ApiConstants.kt
|         │   └── ThemeConstants.kt
|         │
|         ├── theme
│              ├── Color.kt
│              ├── Theme.kt
│              └── Type.kt
├── navigation
│   └── AppNavigation.kt
│
└── MainActivity.kt
```

### Data Layer

Responsible for handling and providing application data.

* **model** — Contains the API/data models.
* **remote** — Handles communication with the news API using Retrofit.
* **local** — Responsible for local data storage using Room.
* **repository** — Acts as the single source of data for the ViewModels.

### UI Layer

Responsible for displaying application content and handling user interactions.

Each feature has its own UI components and ViewModel where needed.

### Navigation

`AppNavigation.kt` manages navigation between the application's screens using **Navigation Compose**.

---

## 📂 Main Screens

### 🏠 Home

Displays the latest news articles and allows users to browse different news categories.

### 🔍 Search

Allows users to search for specific news articles.

### 📖 Details

Displays detailed information about a selected article.

### 🔖 Bookmarks

Displays articles saved by the user for later reading.

---

## 🔌 API

The application uses a news API to retrieve articles.

The networking layer is implemented using:

```text
Retrofit
    ↓
NewsApiService
    ↓
NewsRepository
    ↓
HomeViewModel
    ↓
HomeUiState
    ↓
HomeScreen
```

This separation keeps networking logic independent from the UI.

---


## 📸 Screenshots

> Screenshots will be added as the UI development progresses.

---
## 🧠 What I'm Learning

This project is being developed as a practical way to learn and strengthen my knowledge of modern Android development.

Some of the concepts being practiced:

* Jetpack Compose
* MVVM Architecture
* ViewModel
* StateFlow
* UI State management
* Retrofit
* REST APIs
* Repository Pattern
* Coroutines
* Room Database
* Navigation Compose
* Dependency separation
* Reusable Compose components
* Error and loading state handling

---
## 📌 Project Status

This project is **actively under development**.

The architecture and features may change as I continue learning and improving the application.

---

## 👨‍💻 Author

**Karim Ashraf**

Computer Science Student
Android / Kotlin Developer

---

⭐ If you find this project interesting, feel free to check out the repository and follow its development.
