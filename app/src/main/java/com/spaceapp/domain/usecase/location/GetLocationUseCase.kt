package com.spaceapp.domain.usecase.location

import android.location.Location
import com.spaceapp.core.common.Result
import com.spaceapp.data.datasource.remote.hms.DefaultLocationTracker
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val defaultLocationTracker: DefaultLocationTracker) {
    suspend operator fun invoke() : Flow<Result<Location>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = defaultLocationTracker.getCurrentLocation()))
        }catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}