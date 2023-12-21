package com.spaceapp.domain.usecase.glossary

import android.content.Context
import com.spaceapp.core.common.Result
import com.spaceapp.domain.model.glossary_data.Glossary
import com.spaceapp.data.repository.space_objects.SpaceObjectsRepository
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGlossaryUseCase @Inject constructor(private val repository: SpaceObjectsRepository) {

    suspend operator fun invoke(applicationContext: Context): Flow<Result<Glossary>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = repository.getGlossariesFromLocal(applicationContext = applicationContext)))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}