package com.spaceapp.domain.usecase.space_news

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.data.repository.space_news.SpaceNewsRepository
import com.spaceapp.domain.model.space_news.SpaceNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestScienceNewsFromNetworkUseCase @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke(): Flow<Response<SpaceNews>> =
        call { repository.getLatestScienceNewsFromNetwork() }
}