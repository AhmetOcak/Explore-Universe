package com.spaceapp.domain.repository

import android.content.Context
import com.spaceapp.domain.model.SpaceObject

interface ExploreGalaxyRepository {
    suspend fun getExploreGalaxyDataFromLocal(applicationContext: Context): SpaceObject
}