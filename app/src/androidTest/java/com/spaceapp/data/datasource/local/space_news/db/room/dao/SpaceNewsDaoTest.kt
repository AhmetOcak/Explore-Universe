package com.spaceapp.data.datasource.local.space_news.db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpaceNewsDaoTest {

    private lateinit var dao: SpaceNewsDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.spaceNewsDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addSpaceNews() = runTest {
        val spaceNewsItem = SpaceNewsEntity(
            id = 1,
            title = "Space Accident",
            image = "space rocket",
            date = "10.10.2020",
            author = "Darth Vader",
            newsUrl = "url",
            sourceName = "source"
        )

        dao.addSpaceNews(spaceNewsItem)

        val allSpaceNews = dao.getSpaceNews()

        assertThat(allSpaceNews).contains(spaceNewsItem)
    }

    @Test
    fun getSpaceNews() = runTest {
        val spaceNewsItem1 = SpaceNewsEntity(
            id = 1,
            title = "Space Accident",
            image = "space rocket",
            date = "10.10.2020",
            author = "Darth Vader",
            newsUrl = "url",
            sourceName = "source"
        )
        val spaceNewsItem2 = SpaceNewsEntity(
            id = 2,
            title = "Black Hole",
            image = "black hole",
            date = "22.12.2022",
            author = "Anakin",
            newsUrl = "url",
            sourceName = "lol"
        )

        dao.addSpaceNews(spaceNewsItem1)
        dao.addSpaceNews(spaceNewsItem2)

        val allSpaceNews = dao.getSpaceNews()

        assertThat(allSpaceNews).contains(spaceNewsItem1)
        assertThat(allSpaceNews).contains(spaceNewsItem2)
    }

    @Test
    fun deleteAllSpaceNews() = runTest {
        val spaceNewsItem = SpaceNewsEntity(
            id = 1,
            title = "Space Accident",
            image = "space rocket",
            date = "10.10.2020",
            author = "Darth Vader",
            sourceName = "source",
            newsUrl = "url"
        )

        dao.addSpaceNews(spaceNewsItem)
        dao.deleteAll()

        val allSpaceNews = dao.getSpaceNews()

        assertThat(allSpaceNews.size).isEqualTo(0)
    }
}