package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.dbCall
import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import com.spaceapp.domain.repository.PeopleInSpaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleInSpaceFromDatabaseUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepository) {

    operator fun invoke(): Flow<Response<List<PeopleInSpace>>> = dbCall {
        peopleInSpaceRepository.getPeopleInSpaceFromLocal()
    }
}