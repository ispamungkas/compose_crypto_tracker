package com.plcoding.cryptotracker.crypto.core.domain.utils

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    SERIALIZATION,
    NO_INTERNET,
    SERVER_ERROR,
    TOO_MANY_REQUEST,
    UNKNOWN
}