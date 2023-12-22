package com.spaceapp.domain.usecase.space_news

import com.spaceapp.core.common.Response
import com.spaceapp.domain.repository.SpaceNewsRepository
import com.spaceapp.domain.model.space_news.SpaceNews
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetScienceNewsFromLocal @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke(): Flow<Response<SpaceNews>> = flow {
        try {
            emit(Response.Loading)

            val data = repository.getScienceNewsFromLocal()

            if (data.articles.isNotEmpty()) {
                emit(Response.Success(data))
            } else {
                emit(Response.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Response.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}