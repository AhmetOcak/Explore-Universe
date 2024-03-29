package com.spaceapp.domain.usecase.space_news

import com.spaceapp.domain.model.space_news.SpaceNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.domain.repository.SpaceNewsRepository

class GetSpaceNewsFromNetworkUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) {

    operator fun invoke(): Flow<Response<SpaceNews>> = call {
        spaceNewsRepository.getSpaceNewsFromNetwork()
    }
}