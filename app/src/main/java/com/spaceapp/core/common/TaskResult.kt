package com.spaceapp.core.common

sealed interface TaskResult<out T> {
    class Success<T>(val data: T?) : TaskResult<T>
    class Error<T>(val message: String?) : TaskResult<T>
}