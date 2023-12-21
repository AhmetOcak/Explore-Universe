package com.spaceapp.data.datasource.local.space_news.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spaceapp.data.utils.RoomTables

@Entity(tableName = RoomTables.SCIENCE_NEWS_TABLE)
data class ScienceNewsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "image_url")
    var image: String,

    @ColumnInfo(name = "source")
    var sourceName: String,

    @ColumnInfo(name = "news_url")
    var newsUrl: String
)