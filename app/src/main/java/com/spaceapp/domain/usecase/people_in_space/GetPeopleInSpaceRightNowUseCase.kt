package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.domain.repository.PeopleInSpaceRepository

class GetPeopleInSpaceRightNowUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepository) {

    operator fun invoke(): Flow<Response<List<PeopleInSpace>>> =
        call { listOf(peopleInSpaceRepository.getPeopleInSpaceRightNow()) }
}