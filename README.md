# 📈 Cryptocurrency Tracker App

A simple cryptocurrency tracking app built using Jetpack Compose, Ktor, and Koin, following the MVI (Model-View-Intent) architecture.

---

## 🚀 Features

- Displays a list of cryptocurrencies with key details:
  - 🪙 **Icon**
  - 🔤 **Name**
  - 💲 **Symbol**
  - 💰 **Market Cap (USD)**
  - 📊 **24-hour Trading Volume (USD)**
- Navigates to a **Coin Detail Screen** on item selection, displaying:
  - 🏷 **Coin Name**
  - 💵 **Price (USD)**
  - 💲 **Market Cap (USD)**
- Uses **Ktor** for API calls to fetch real-time data.
- Implements **Koin** for Dependency Injection.

---

## 🏗️ Tech Stack

| Technology          | Purpose                                      |
|---------------------|----------------------------------------------|
| **Kotlin**         | Primary programming language                 |
| **Jetpack Compose** | Modern UI toolkit for building UI            |
| **Ktor Client**    | Networking library for API calls             |
| **Koin**           | Dependency Injection (DI) framework          |
| **Navigation Component** | Handles screen transitions          |
| **MVI Architecture** | Ensures a clean separation of concerns      |
| **Coroutines & Flow** | Manages asynchronous data streams         |

---

## 🔗 API Integration

This project fetches real-time cryptocurrency data from **CoinCap API**.

- **API Endpoint Used:** [https://api.coincap.io/v2/assets](https://api.coincap.io/v2/assets)
- **Networking:** Implemented using **Ktor Client** for efficient API calls.

---


