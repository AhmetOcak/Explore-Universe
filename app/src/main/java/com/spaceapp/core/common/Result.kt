package com.spaceapp.core.common

sealed interface Result<out T> {
    class Success<T>(val data: T?) : Result<T>
    class Error<T>(val message: String?) : Result<T>
    object Loading : Result<Nothing>
}
