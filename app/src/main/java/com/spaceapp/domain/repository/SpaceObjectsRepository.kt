package com.spaceapp.domain.repository

import android.content.Context
import com.spaceapp.domain.model.explore_galaxy_data.SpaceObject
import com.spaceapp.domain.model.glossary_data.Glossary

interface SpaceObjectsRepository {

    suspend fun getExploreGalaxyDataFromLocal(applicationContext: Context): SpaceObject

    suspend fun getGlossariesFromLocal(applicationContext: Context): Glossary
}