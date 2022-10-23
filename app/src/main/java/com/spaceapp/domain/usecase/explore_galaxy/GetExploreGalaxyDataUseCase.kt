package com.spaceapp.domain.usecase.explore_galaxy

import android.content.Context
import com.spaceapp.data.repository.ExploreGalaxyRepositoryImpl
import com.spaceapp.domain.model.SpaceObject
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.core.common.Result

class GetExploreGalaxyDataUseCase @Inject constructor(private val exploreGalaxyRepository: ExploreGalaxyRepositoryImpl) {

    suspend operator fun invoke(applicationContext: Context): Flow<Result<SpaceObject>> = flow {
        try {
            emit(Result.Loading)

            emit(
                Result.Success(
                    data = exploreGalaxyRepository.getExploreGalaxyDataFromLocal(
                        applicationContext = applicationContext
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}