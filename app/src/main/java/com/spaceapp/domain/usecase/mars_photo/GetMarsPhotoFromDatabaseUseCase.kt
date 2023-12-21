package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.caller.dbCall
import com.spaceapp.data.repository.nasa.NasaRepository
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarsPhotoFromDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    operator fun invoke(): Flow<Response<List<MarsPhoto>>> =
        dbCall { nasaRepository.getMarsPhotoFromLocal() }
}