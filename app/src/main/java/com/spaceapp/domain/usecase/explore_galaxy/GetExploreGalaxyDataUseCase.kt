package com.spaceapp.domain.usecase.explore_galaxy

import android.content.Context
import com.spaceapp.domain.model.explore_galaxy_data.SpaceObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.domain.repository.SpaceObjectsRepository

class GetExploreGalaxyDataUseCase @Inject constructor(private val repository: SpaceObjectsRepository) {

    suspend operator fun invoke(applicationContext: Context): Flow<Response<SpaceObject>> = call {
        repository.getExploreGalaxyDataFromLocal(
            applicationContext = applicationContext
        )
    }
}