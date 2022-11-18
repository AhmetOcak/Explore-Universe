package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity
import com.spaceapp.data.datasource.remote.mars_photos.entity.MarsPhotoDto
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.domain.model.mars_photos.MarsPhotoDetail
import com.spaceapp.domain.model.mars_photos.Rover

fun MarsPhotoDto.toMarsPhoto(): List<MarsPhoto> {
    return listOf(
        MarsPhoto(
            photos = photos.map {
                MarsPhotoDetail(
                    image = it.image,
                    date = it.date,
                    rover = Rover(
                        name = it.rover.name
                    )
                )
            }
        )
    )
}

fun MarsPhoto.toMarsPhotoEntity(): MarsPhotoEntity {
    return MarsPhotoEntity(
        roverName = photos[0].rover.name,
        date = photos[0].date,
        image = photos[0].image
    )
}

fun List<MarsPhotoEntity>.toMarsPhoto(): List<MarsPhoto> {
    return map {
        MarsPhoto(
            photos = listOf(
                MarsPhotoDetail(
                    image = it.image,
                    date = it.date,
                    rover = Rover(
                        name = it.roverName
                    )
                )
            )
        )
    }
}