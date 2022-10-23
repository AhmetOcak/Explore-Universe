package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.data.repository.PeopleInSpaceRepositoryImpl
import javax.inject.Inject

class ClearPeopleInSpaceDatabaseUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepositoryImpl) {

    suspend operator fun invoke() = peopleInSpaceRepository.deleteLocalPeopleInSpace()
}