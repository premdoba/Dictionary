# 📖 Dictionary App

> **A Modern Dictionary Application built with Kotlin & Jetpack Compose**

Dictionary App is a lightweight Android application that allows users to instantly search the meanings of English words. Built using **Kotlin**, **Jetpack Compose**, **Retrofit**, and **MVVM Architecture**, the app provides detailed definitions, phonetics, examples, and parts of speech through a clean and responsive user interface.

---

## ✨ Features

### 🔍 Instant Word Search

Search any English word and get detailed information instantly.

---

### 📚 Word Meanings

View multiple definitions for each word with proper organization.

---

### 🔊 Phonetics

Learn the correct pronunciation through phonetic representations.

---

### 💡 Examples

Understand word usage with real-life example sentences.

---

### 📝 Parts of Speech

Identify whether the word is a:

- Noun
- Verb
- Adjective
- Adverb
- Pronoun
- And more

---

### 🎨 Modern UI

- Built entirely with Jetpack Compose
- Material Design
- Responsive Layout
- Clean User Experience

---

## 🏗 Tech Stack

| Category | Technology |
|----------|------------|
| Language | Kotlin |
| UI Toolkit | Jetpack Compose |
| Architecture | MVVM |
| Networking | Retrofit |
| API | Free Dictionary API |
| State Management | ViewModel |
| Asynchronous Programming | Kotlin Coroutines |

---

# 📂 Project Structure

```text
com.example.dictionary
│
├── api
│   ├── DictionaryAPI.kt
│   ├── DictionaryData.kt
│   └── RetrofitInstance.kt
│
├── ui
│   └── theme
│
├── view
│   └── DictionaryScreen.kt
│
├── viewmodel
│   └── DictionaryViewModel.kt
│
└── MainActivity.kt
```

---

# 🧱 Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture to keep the code clean, modular, and maintainable.

```text
User

   ↓

Jetpack Compose UI
(DictionaryScreen)

   ↓

ViewModel
(DictionaryViewModel)

   ↓

Repository/API Layer

   ↓

Retrofit

   ↓

Dictionary API
```

---

# 📂 Module Description

## 📱 View

Contains all Jetpack Compose UI components.

- Dictionary Screen
- Search Bar
- Result Display

---

## 🧠 ViewModel

Responsible for:

- Managing UI State
- Fetching Dictionary Data
- Handling Search Logic
- Updating Compose UI

---

## 🌐 API Layer

Handles communication with the Dictionary API using Retrofit.

Includes:

- API Interface
- Retrofit Configuration
- Response Models

---

## 🚀 App Workflow

1. User enters a word.
2. ViewModel receives the search request.
3. Retrofit sends an API request.
4. Dictionary API returns the response.
5. ViewModel updates the UI state.
6. Compose displays meanings, phonetics, and examples.

---

# 🌟 Highlights

- Built with Jetpack Compose
- Clean MVVM Architecture
- Retrofit API Integration
- Kotlin Coroutines
- Responsive UI
- Fast Word Search
- Multiple Meanings
- Phonetics Support
- Example Sentences
- Material Design UI

---

# 📸 Screens

- Home Screen
- Search Results
- Word Details
- Meanings
- Phonetics
- Examples

---

# 🚀 Future Improvements

- Word Pronunciation Audio
- Search History
- Favorite Words
- Offline Dictionary Support
- Dark Mode
- Recent Searches
- Word of the Day
- Synonyms & Antonyms

---

# 🤝 Contributing

Contributions are welcome!

1. Fork the repository

2. Create a new branch

```bash
git checkout -b feature/NewFeature
```

3. Commit your changes

```bash
git commit -m "Added New Feature"
```

4. Push to GitHub

```bash
git push origin feature/NewFeature
```

5. Create a Pull Request

---

# 📄 License

This project is open-source and available under the MIT License.

---

# 👨‍💻 Developer

**Prem Doba**

Android Developer | Kotlin | Jetpack Compose | Firebase | MVVM

GitHub:
https://github.com/premdoba

---

## ⭐ Support

If you found this project helpful, consider giving it a **⭐ Star** on GitHub!

> **Learn New Words. Expand Your Vocabulary. 📖**
