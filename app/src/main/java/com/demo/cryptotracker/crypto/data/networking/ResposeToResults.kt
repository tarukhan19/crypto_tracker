package com.demo.cryptotracker.crypto.data.networking

import com.demo.cryptotracker.core.domain.util.NetworkError
import com.demo.cryptotracker.core.domain.util.Result
import retrofit2.Response

//these catch exception catches the error which happens after API call

suspend inline fun <reified T> responseToResult(response: Response<T>): Result<T, NetworkError> {
    return when {
        response.isSuccessful -> {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }
        response.code() == 408 -> Result.Error(NetworkError.REQUEST_TIMEOUTS)
        response.code() == 429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        response.code() in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}
