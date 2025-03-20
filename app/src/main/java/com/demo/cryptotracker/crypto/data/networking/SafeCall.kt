package com.demo.cryptotracker.crypto.data.networking

import com.demo.cryptotracker.core.domain.util.NetworkError
import com.demo.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

//these catch exception catches the error which happens before API call

suspend inline fun <reified T> safeCall(
    execute : () -> HttpResponse
) : Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException){
        return Result.Error(NetworkError.NO_INTERNET)
    }
    catch (e: SerializationException){
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    }
    catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}