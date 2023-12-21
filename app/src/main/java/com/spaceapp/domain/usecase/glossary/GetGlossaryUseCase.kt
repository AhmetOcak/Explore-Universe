package com.spaceapp.domain.usecase.glossary

import android.content.Context
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.caller.call
import com.spaceapp.domain.model.glossary_data.Glossary
import com.spaceapp.data.repository.space_objects.SpaceObjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGlossaryUseCase @Inject constructor(private val repository: SpaceObjectsRepository) {

    suspend operator fun invoke(applicationContext: Context): Flow<Response<Glossary>> = call {
        repository.getGlossariesFromLocal(applicationContext = applicationContext)
    }
}