package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.PeopleInSpaceRepositoryImpl
import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPeopleInSpaceFromDatabaseUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepositoryImpl) {

    operator fun invoke(): Flow<Result<List<PeopleInSpace>>> = flow {
        try {
            emit(Result.Loading)

            val data = peopleInSpaceRepository.getPeopleInSpaceFromLocal()

            if (data.isNotEmpty()) {
                emit(Result.Success(data = peopleInSpaceRepository.getPeopleInSpaceFromLocal()))
            } else {
                emit(Result.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}