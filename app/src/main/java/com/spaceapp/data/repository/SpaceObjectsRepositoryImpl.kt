package com.spaceapp.data.repository

import android.content.Context
import com.spaceapp.data.datasource.local.explore_galaxy_data.ExploreGalaxyDataSource
import com.spaceapp.data.datasource.local.glossary_data.GlossaryLocalDataSource
import com.spaceapp.data.mappers.toGlossary
import com.spaceapp.data.mappers.toSpaceObject
import com.spaceapp.domain.model.explore_galaxy_data.SpaceObject
import com.spaceapp.domain.model.glossary_data.Glossary
import com.spaceapp.domain.repository.SpaceObjectsRepository
import javax.inject.Inject

class SpaceObjectsRepositoryImpl @Inject constructor(
    private val exploreGalaxyLocalDataSource: ExploreGalaxyDataSource,
    private val glossaryLocalDataSource: GlossaryLocalDataSource
) : SpaceObjectsRepository {

    override suspend fun getExploreGalaxyDataFromLocal(applicationContext: Context): SpaceObject =
        exploreGalaxyLocalDataSource.readExploreGalaxyJson(applicationContext = applicationContext).toSpaceObject()

    override suspend fun getGlossariesFromLocal(applicationContext: Context): Glossary =
        glossaryLocalDataSource.readGlossaryJson(applicationContext = applicationContext).toGlossary()
}