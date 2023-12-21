package com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.where_is_the_iss.db.entity.IssEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class IssStateDaoTest {

    private lateinit var dao: IssDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.issDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addIss() = runTest {
        val issItem = IssEntity(
            id = 1,
            latitude = 0.0,
            longitude = 0.0,
            date = 164646474,
            velocity = 3.4,
            visibility = "clear",
            altitude = 34.5
        )

        dao.addIss(issItem)

        val iss = dao.getIss()

        assertThat(iss).isEqualTo(issItem)
    }

    @Test
    fun getIss() = runTest {
        val issItem = IssEntity(
            id = 1,
            latitude = 0.0,
            longitude = 0.0,
            date = 164646474,
            velocity = 3.4,
            visibility = "clear",
            altitude = 34.5
        )

        dao.addIss(issItem)

        val iss = dao.getIss()

        assertThat(iss).isEqualTo(issItem)
    }

    @Test
    fun updateIss() = runTest {
        val issItem = IssEntity(
            id = 1,
            latitude = 0.0,
            longitude = 0.0,
            date = 164646474,
            velocity = 3.4,
            visibility = "clear",
            altitude = 34.5
        )

        val newIssItem = IssEntity(
            id = 1,
            latitude = 20.0,
            longitude = 32.0,
            date = 164646498,
            velocity = 13.3,
            visibility = "eclipsed",
            altitude = 39.4
        )

        dao.addIss(issItem)
        dao.updateIss(newIssItem)

        val iss = dao.getIss()

        assertThat(iss).isEqualTo(newIssItem)
    }

}
