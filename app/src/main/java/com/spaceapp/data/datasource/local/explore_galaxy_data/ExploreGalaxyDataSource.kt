package com.spaceapp.data.datasource.local.explore_galaxy_data

import android.content.Context
import com.spaceapp.data.datasource.local.explore_galaxy_data.entity.SpaceObjectDto

interface ExploreGalaxyDataSource {

    fun readExploreGalaxyJson(applicationContext: Context): SpaceObjectDto
}