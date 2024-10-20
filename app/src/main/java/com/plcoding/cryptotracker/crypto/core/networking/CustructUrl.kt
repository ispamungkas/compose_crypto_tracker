package com.plcoding.cryptotracker.crypto.core.networking

import com.plcoding.cryptotracker.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> BuildConfig.BASE_URL
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url.dropLast(1)
    }
}