package com.demo.cryptotracker.crypto.data.networking

import com.demo.cryptotracker.core.domain.util.NetworkError
import com.demo.cryptotracker.core.domain.util.Result
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.coroutineContext

//these catch exception catches the error which happens before API call

suspend inline fun <reified T> safeCall(
    crossinline execute: suspend () -> Response<T>
): Result<T, NetworkError> {
    return try {
        val response = execute()
        responseToResult(response)
    } catch (e: IOException) {
        Result.Error(NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Result.Error(NetworkError.UNKNOWN)
    }
}