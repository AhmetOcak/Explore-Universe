package com.spaceapp.data.repository

import android.content.Context
import com.spaceapp.data.datasource.local.glossary_data.GlossaryLocalDataSource
import com.spaceapp.data.mappers.toGlossary
import com.spaceapp.domain.model.Glossary
import com.spaceapp.domain.repository.GlossaryRepository
import javax.inject.Inject

class GlossaryRepositoryImpl @Inject constructor(
    private val localDataSource: GlossaryLocalDataSource
) : GlossaryRepository {

    override suspend fun getGlossariesFromLocal(applicationContext: Context): Glossary =
        localDataSource.readGlossaryJson(applicationContext = applicationContext).toGlossary()
}