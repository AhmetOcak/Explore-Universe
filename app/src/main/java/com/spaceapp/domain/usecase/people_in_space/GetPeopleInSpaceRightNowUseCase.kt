package com.spaceapp.domain.usecase.people_in_space

import android.util.Log
import com.spaceapp.data.repository.PeopleInSpaceRepositoryImpl
import com.spaceapp.domain.model.PeopleInSpace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.utils.ERROR

class GetPeopleInSpaceRightNowUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepositoryImpl) {

    operator fun invoke(): Flow<Result<List<PeopleInSpace>>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = listOf(peopleInSpaceRepository.getPeopleInSpaceRightNow())))
        } catch (e: IOException) {
            Log.e("people", e.toString())
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}