package com.spaceapp.data.datasource.local.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapp.data.datasource.local.db.entity.*
import com.spaceapp.data.datasource.local.db.room.dao.*

@Database(
    entities = [
        ApodEntity::class, MarsPhotoEntity::class,
        PeopleInSpaceEntity::class, SpaceNewsEntity::class,
        WeatherConditionEntity::class, IssEntity::class
    ],
    version = 1
)
abstract class SpaceDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao
    abstract fun marsPhotoDao(): MarsPhotoDao
    abstract fun peopleInSpaceDao(): PeopleInSpaceDao
    abstract fun spaceNewsDao(): SpaceNewsDao
    abstract fun weatherConditionDao(): WeatherConditionDao
    abstract fun issDao(): IssDao
}