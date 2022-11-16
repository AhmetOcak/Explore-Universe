package com.spaceapp.domain.usecase.location

import android.location.Location
import com.spaceapp.core.common.Result
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.data.datasource.remote.location.LocationManager

class GetLocationUseCase @Inject constructor(private val locationManager: LocationManager) {
    suspend operator fun invoke() : Flow<Result<Location>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = locationManager.getCurrentLocation()))
        }catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}