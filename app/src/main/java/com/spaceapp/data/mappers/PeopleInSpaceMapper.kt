package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.datasource.remote.people_in_space.entity.DetailDto
import com.spaceapp.data.datasource.remote.people_in_space.entity.PeopleInSpaceDto
import com.spaceapp.domain.model.PeopleInSpace
import com.spaceapp.domain.model.PeopleInSpaceDetail

fun PeopleInSpaceDto.toPeopleInSpace(): PeopleInSpace {
    return PeopleInSpace(
        people = people.map {
            PeopleInSpaceDetail(
                name = it.name,
                craft = it.craft
            )
        }
    )
}

fun List<PeopleInSpaceEntity>.toPeopleInSpace(): List<PeopleInSpace> {
    return map {
        PeopleInSpace(
            people = listOf(
                PeopleInSpaceDetail(
                    name = it.name,
                    craft = it.craft
                )
            )
        )
    }
}

fun PeopleInSpace.toPeopleInSpaceEntity(): PeopleInSpaceEntity {
    return PeopleInSpaceEntity(
        name = people[0].name,
        craft = people[0].craft
    )
}