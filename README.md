A simple cryptocurrency tracking app built using Jetpack Compose, Ktor, and Koin, following the MVI (Model-View-Intent) architecture.

📌 Features

Displays a list of cryptocurrencies with details such as:

Icon

Name

Symbol

Market Cap (USD)

24-hour Trading Volume (USD)

Navigates to a Coin Detail Screen on item click, showing:

Coin Name

Price (USD)

Market Cap (USD)

Uses Ktor for API calls and Koin for dependency injection.

🏗️ Tech Stack

Kotlin - Primary programming language

Jetpack Compose - Modern UI toolkit

Ktor Client - Networking library for API calls

Koin - Dependency Injection (DI) framework

Navigation Component - For screen transitions

MVI Architecture - Ensuring separation of concerns

Coroutines & Flow - Handling asynchronous data streams

🔗 API Integration

This project fetches real-time cryptocurrency data from CoinCap API.

API Endpoint used: https://api.coincap.io/v2/assets

Implemented using Ktor Client
