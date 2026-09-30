# 📰 NewsApp

A modern **Android News Application** built with **Kotlin** and **Jetpack Compose**.

The project is built to practice modern Android development concepts, including **Jetpack Compose, MVVM, Retrofit, Repository Pattern, StateFlow, and Room**.

---

## 🚀 Features

* 🏠 Browse the latest top headlines and trending news
* 🔍 Search for articles dynamically
* 📖 View full article details with share and browser view actions
* 🔖 Bookmark/save articles to favorites
* 💾 Store and manage bookmarked articles locally using Room Database
* ⏳ Loading and error states handling
* 📱 Modern Jetpack Compose UI with Material 3

---

## 🛠️ Tech Stack

| Technology             | Usage                              |
| ---------------------- | ---------------------------------- |
| **Kotlin**             | Primary programming language       |
| **Jetpack Compose**    | UI development                     |
| **MVVM**               | Application architecture           |
| **Retrofit**           | REST API communication             |
| **Gson**               | JSON serialization/deserialization |
| **StateFlow**          | UI state management                |
| **ViewModel**          | Business/UI logic                  |
| **Room**               | Local database for bookmarks       |
| **Coroutines**         | Asynchronous programming           |
| **Navigation Compose** | Screen navigation                  |
| **Coil**               | Image loading                      |

---

## 🏗️ Architecture & Project Structure

The project follows the **MVVM architecture** with a clear separation between the data and UI layers.

```text
com.example.newsapp
├── data
│   ├── local
│   │   ├── ArticleDao.kt
│   │   ├── ArticleEntity.kt
│   │   └── NewsDatabase.kt
│   │
│   ├── model
│   │   ├── Article.kt
│   │   └── NewsResponse.kt
│   │
│   ├── remote
│   │   ├── NewsApiService.kt
│   │   └── RetrofitInstance.kt
│   │
│   └── repository
│       └── NewsRepository.kt
│
├── navigation
│   ├── AppNavigation.kt
│   └── MainScreen.kt
│
├── ui
│   ├── bookmarks
│   │   └── BookmarkedNewsScreen.kt
│   │
│   ├── components
│   │   ├── ArticleCard.kt
│   │   └── TrendingSection.kt
│   │
│   ├── details
│   │   └── DetailsScreen.kt
│   │
│   ├── home
│   │   ├── HomeScreen.kt
│   │   ├── HomeUiState.kt
│   │   └── HomeViewModelFactory.kt
│   │
│   ├── onboarding
│   │   ├── OnBoardingPage.kt
│   │   └── OnBoardingScreen.kt
│   │
│   ├── search
│   │   ├── SearchScreen.kt
│   │   └── SearchUIState.kt
│   │
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   │
│   └── viewmodels
│       └── NewsViewModel.kt
│
├── utils
│   └── constants
│       └── ApiConstants.kt
│
└── MainActivity.kt
```

### Data Layer

Responsible for handling and providing application data.

* **local** — Room database entities, DAO, and database instance for local bookmarking.
* **model** — API and domain data models (Article, Source, NewsResponse).
* **remote** — Retrofit service and instance for fetching headlines and search results.
* **repository** — Single source of truth coordinating network data and local database favorites.

### UI Layer

Responsible for displaying application content and handling user interactions using Jetpack Compose and ViewModels.

### Navigation

Managed via `AppNavigation.kt` and `MainScreen.kt` using **Navigation Compose** with bottom bar navigation and nested screen routing.

---

## 📂 Main Screens

### 🏠 Home

Displays top headlines and trending news carousel with quick bookmarking and article selection.

### 🔍 Search

Allows users to search for specific news articles with instant results and bookmarking support.

### 📖 Details

Displays full article content, publication details, and action buttons to share, open in browser, or toggle favorites.

### 🔖 Bookmarks

Displays all favorite articles saved locally by the user for offline reading.

---

## 👨‍💻 Author

**Karim Ashraf**

Computer Science Student & Mobile Application Developer

---

⭐ If you find this project interesting, feel free to check out the repository and follow its development.
