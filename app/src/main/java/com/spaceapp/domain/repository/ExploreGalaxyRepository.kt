package com.spaceapp.domain.repository

import android.content.Context
import com.spaceapp.domain.model.explore_galaxy_data.SpaceObject

interface ExploreGalaxyRepository {
    suspend fun getExploreGalaxyDataFromLocal(applicationContext: Context): SpaceObject
}