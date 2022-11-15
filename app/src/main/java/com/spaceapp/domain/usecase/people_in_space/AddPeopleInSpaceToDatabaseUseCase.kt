package com.spaceapp.domain.usecase.people_in_space

import com.spaceapp.data.repository.PeopleInSpaceRepositoryImpl
import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import javax.inject.Inject

class AddPeopleInSpaceToDatabaseUseCase @Inject constructor(private val peopleInSpaceRepository: PeopleInSpaceRepositoryImpl) {

    suspend operator fun invoke(peopleInSpace: PeopleInSpace) {
        peopleInSpace.people.forEach {
            peopleInSpaceRepository.addPeopleInSpaceToLocal(
                peopleInSpace = PeopleInSpace(people = listOf(it))
            )
        }
    }
}