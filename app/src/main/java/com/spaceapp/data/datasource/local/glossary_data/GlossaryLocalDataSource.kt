package com.spaceapp.data.datasource.local.glossary_data

import android.content.Context
import com.spaceapp.data.datasource.local.glossary_data.entity.GlossaryDto

interface GlossaryLocalDataSource {

    fun readGlossaryJson(applicationContext: Context): GlossaryDto
}