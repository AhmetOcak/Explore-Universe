package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.caller.call
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.data.repository.nasa.NasaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestMarsPhotoFromNetworkUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    operator fun invoke(): Flow<Response<List<MarsPhoto>>> =
        call { nasaRepository.getLatestMarsPhotos() }
}