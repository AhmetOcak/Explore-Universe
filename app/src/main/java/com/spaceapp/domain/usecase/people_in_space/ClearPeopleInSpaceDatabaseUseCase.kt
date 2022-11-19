package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.domain.repository.PeopleInSpaceRepository
import javax.inject.Inject

class ClearPeopleInSpaceDatabaseUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepository) {

    suspend operator fun invoke() = peopleInSpaceRepository.deleteLocalPeopleInSpace()
}