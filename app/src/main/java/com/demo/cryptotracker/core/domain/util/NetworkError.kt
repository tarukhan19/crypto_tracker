package com.demo.cryptotracker.core.domain.util

enum class NetworkError : Error {
    REQUEST_TIMEOUTS,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN
}