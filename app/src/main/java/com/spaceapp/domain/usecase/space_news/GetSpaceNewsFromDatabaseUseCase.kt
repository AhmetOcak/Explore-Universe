package com.spaceapp.domain.usecase.space_news

import com.spaceapp.domain.model.space_news.SpaceNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.dbCall
import com.spaceapp.data.repository.space_news.SpaceNewsRepository

class GetSpaceNewsFromDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) {

    suspend operator fun invoke(): Flow<Response<List<SpaceNews>>> = dbCall {
        spaceNewsRepository.getSpaceNewsFromLocal()
    }
}