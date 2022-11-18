package com.spaceapp.data.datasource.local.explore_galaxy_data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spaceapp.data.datasource.local.explore_galaxy_data.entity.SpaceObjectDto
import javax.inject.Inject

class ExploreGalaxyLocalDataSourceImpl @Inject constructor() : ExploreGalaxyDataSource {

    override fun readExploreGalaxyJson(applicationContext: Context): SpaceObjectDto {
        val jsonString: String = applicationContext.assets.open("explore_galaxy_data.json")
            .bufferedReader()
            .use { it.readText() }

        val listSpaceObjectsDtoType = object : TypeToken<SpaceObjectDto>() {}.type
        return Gson().fromJson(jsonString, listSpaceObjectsDtoType)
    }
}