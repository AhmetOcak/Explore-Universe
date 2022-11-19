package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.repository.PeopleInSpaceRepository
import com.spaceapp.domain.utils.ERROR

class GetPeopleInSpaceRightNowUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepository) {

    operator fun invoke(): Flow<Result<List<PeopleInSpace>>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = listOf(peopleInSpaceRepository.getPeopleInSpaceRightNow())))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}