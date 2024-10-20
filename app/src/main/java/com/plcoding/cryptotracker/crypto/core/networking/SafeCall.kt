package com.plcoding.cryptotracker.crypto.core.networking

import com.plcoding.cryptotracker.crypto.core.domain.utils.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.utils.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    call: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        call()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(error = NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(error = NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(error = NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}