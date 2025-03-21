package com.demo.cryptotracker.core.presentation.util

import android.content.Context
import com.demo.cryptotracker.core.domain.util.NetworkError
import com.demo.cryptotracker.R

fun NetworkError.toStrings(context : Context) : String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUTS -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)
}