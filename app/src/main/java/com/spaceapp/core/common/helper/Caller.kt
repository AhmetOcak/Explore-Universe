package com.spaceapp.core.common.helper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import com.spaceapp.core.common.Response
import com.spaceapp.domain.utils.ERROR

inline fun <T> call(crossinline call: suspend () -> T): Flow<Response<T>> {
    return flow {
        try {
            emit(Response.Loading)

            emit(Response.Success(call()))
        } catch (e: IOException) {
            emit(Response.Error(message = "Please check your internet connection."))
        } catch (e: Exception) {
            emit(Response.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }
}

inline fun <T> dbCall(crossinline call: suspend () -> List<T>): Flow<Response<List<T>>> {
    return flow {
        try {
            emit(Response.Loading)

            val data = call()

            if (data.isEmpty()) {
                emit(Response.Success(data = data))
            } else {
                emit(Response.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Response.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}