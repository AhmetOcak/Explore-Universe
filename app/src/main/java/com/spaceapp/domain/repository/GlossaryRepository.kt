package com.spaceapp.domain.repository

import android.content.Context
import com.spaceapp.domain.model.glossary_data.Glossary

interface GlossaryRepository {
    suspend fun getGlossariesFromLocal(applicationContext: Context): Glossary
}