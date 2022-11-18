package com.spaceapp.data.repository

import android.content.Context
import com.spaceapp.data.datasource.local.glossary_data.GlossaryLocalDataSourceImpl
import com.spaceapp.data.mappers.toGlossary
import com.spaceapp.domain.model.glossary_data.Glossary
import com.spaceapp.domain.repository.GlossaryRepository
import javax.inject.Inject

class GlossaryRepositoryImpl @Inject constructor(
    private val localDataSource: GlossaryLocalDataSourceImpl
) : GlossaryRepository {

    override suspend fun getGlossariesFromLocal(applicationContext: Context): Glossary =
        localDataSource.readGlossaryJson(applicationContext = applicationContext).toGlossary()
}