package com.spaceapp.data.repository

import android.content.Context
import com.spaceapp.data.datasource.local.explore_galaxy_data.ExploreGalaxyLocalDataSource
import com.spaceapp.data.mappers.toSpaceObject
import com.spaceapp.domain.model.SpaceObject
import com.spaceapp.domain.repository.ExploreGalaxyRepository
import javax.inject.Inject

class ExploreGalaxyRepositoryImpl @Inject constructor(
    private val exploreGalaxyLocalDataSource: ExploreGalaxyLocalDataSource
) : ExploreGalaxyRepository {

    override suspend fun getExploreGalaxyDataFromLocal(applicationContext: Context): SpaceObject =
        exploreGalaxyLocalDataSource.readExploreGalaxyJson(applicationContext = applicationContext).toSpaceObject()
}