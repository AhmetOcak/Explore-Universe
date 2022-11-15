package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.db.entity.IssEntity
import com.spaceapp.data.datasource.remote.where_is_the_iss.entity.IssDto
import com.spaceapp.domain.model.where_is_the_iss.Iss

fun IssDto.toIss(): Iss {
    return Iss(
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        date = date,
        velocity = velocity,
        visibility = visibility
    )
}

fun IssEntity.toIss(): Iss {
    return Iss(
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        date = date,
        velocity = velocity,
        visibility = visibility
    )
}

fun Iss.toIssEntity(): IssEntity {
    return IssEntity(
        id = 0,
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        date = date,
        velocity = velocity,
        visibility = visibility
    )
}