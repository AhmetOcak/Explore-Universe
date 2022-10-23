package com.spaceapp.data.datasource.local.glossary_data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spaceapp.data.datasource.local.glossary_data.entity.GlossaryDto
import javax.inject.Inject

class GlossaryLocalDataSource @Inject constructor() {

    fun readGlossaryJson(applicationContext: Context): GlossaryDto {
        val jsonString: String = applicationContext.assets.open("glossary_data.json")
            .bufferedReader()
            .use { it.readText() }

        val listGlossaryDtoType = object : TypeToken<GlossaryDto>() {}.type
        return Gson().fromJson(jsonString, listGlossaryDtoType)
    }
}