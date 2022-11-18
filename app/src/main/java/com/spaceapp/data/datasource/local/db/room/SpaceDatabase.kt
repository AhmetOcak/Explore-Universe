package com.spaceapp.data.datasource.local.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.datasource.local.apod.db.room.dao.ApodDao
import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity
import com.spaceapp.data.datasource.local.mars_photos.db.room.dao.MarsPhotoDao
import com.spaceapp.data.datasource.local.people_in_space.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.datasource.local.people_in_space.db.room.dao.PeopleInSpaceDao
import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.local.space_news.db.room.dao.SpaceNewsDao
import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import com.spaceapp.data.datasource.local.where_is_the_iss.db.entity.IssEntity
import com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao.IssDao

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