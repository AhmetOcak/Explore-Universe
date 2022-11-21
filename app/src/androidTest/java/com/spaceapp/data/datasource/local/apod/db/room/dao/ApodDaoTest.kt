package com.spaceapp.data.datasource.local.apod.db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ApodDaoTest {

    private lateinit var dao: ApodDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.apodDao()
    }

    @After
    fun closeDb() {
        db.close()
    }


    @Test
    fun addApod() = runTest {
        val apodItem = ApodEntity(id = 1, title = "mars", image = "mars photo")
        dao.addApod(apodItem)

        val allApods = dao.getApods()

        assertThat(allApods).contains(apodItem)
    }

    @Test
    fun getApods() = runTest {
        val apodItem1 = ApodEntity(id = 1, title = "mars", image = "mars photo")
        val apodItem2 = ApodEntity(id = 2, title = "earth", image = "earth image")

        dao.addApod(apodItem1)
        dao.addApod(apodItem2)

        val allApods = dao.getApods()

        assertThat(allApods).contains(apodItem1)
        assertThat(allApods).contains(apodItem2)
    }

    @Test
    fun deleteAllApods() = runTest {
        val apodItem = ApodEntity(id = 1, title = "mars", image = "mars photo")

        dao.addApod(apodItem)
        dao.deleteAll()

        val allApods = dao.getApods()

        assertThat(allApods.size).isEqualTo(0)
    }
}