package com.plcoding.cryptotracker.crypto.core.presentation.utils

import android.content.Context
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError

fun NetworkError.errorToDisplayString(context: Context): String {
    val result = when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.request_timeout_message
        NetworkError.NO_INTERNET -> R.string.no_internet_message
        NetworkError.SERIALIZATION -> R.string.serialization_message
        NetworkError.SERVER_ERROR -> R.string.server_error_message
        NetworkError.TOO_MANY_REQUEST -> R.string.too_many_request_message
        NetworkError.UNKNOWN -> R.string.unkown_message
    }

    return context.resources.getString(result)
}