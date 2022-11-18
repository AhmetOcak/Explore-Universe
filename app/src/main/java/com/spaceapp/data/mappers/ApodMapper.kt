package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.datasource.remote.apod.entity.ApodDto
import com.spaceapp.domain.model.apod.Apod

fun List<ApodDto>.toApod(): List<Apod> {
    return map {
        Apod(
            title = it.title,
            image = it.image
        )
    }
}

@JvmName("toApodApodEntity")
fun List<ApodEntity>.toApod(): List<Apod> {
    return map {
        Apod(
            title = it.title,
            image = it.image
        )
    }
}

fun Apod.toApodEntity(): ApodEntity {
    return ApodEntity(
        title = title,
        image = image
    )
}

