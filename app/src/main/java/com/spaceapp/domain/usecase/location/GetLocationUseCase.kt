package com.spaceapp.domain.usecase.location

import android.location.Location
import com.spaceapp.core.common.Response
import com.spaceapp.data.datasource.remote.location.ILocationManager
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val locationManager: ILocationManager) {
    suspend operator fun invoke() : Flow<Response<Location>> = flow {
        try {
            emit(Response.Loading)

            emit(Response.Success(data = locationManager.getCurrentLocation()))
        }catch (e: Exception) {
            emit(Response.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}