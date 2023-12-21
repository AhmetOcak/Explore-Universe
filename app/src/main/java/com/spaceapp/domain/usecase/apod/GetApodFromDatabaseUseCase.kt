package com.spaceapp.domain.usecase.apod

import com.spaceapp.domain.model.apod.Apod
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.dbCall
import com.spaceapp.data.repository.nasa.NasaRepository

class GetApodFromDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    operator fun invoke(): Flow<Response<List<Apod>>> = dbCall { nasaRepository.getApodFromLocal() }
}