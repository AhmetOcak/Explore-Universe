package com.spaceapp.data.datasource.local.people_in_space.db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.people_in_space.db.entity.PeopleInSpaceEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PeopleInSpaceDaoTest {

    private lateinit var dao: PeopleInSpaceDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.peopleInSpaceDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addPeople() = runTest {
        val peopleItem = PeopleInSpaceEntity(id = 1, name = "Ahmet", craft = "ISS")

        dao.addPeople(peopleItem)

        val allPeople = dao.getPeopleInSpace()

        assertThat(allPeople).contains(peopleItem)
    }

    @Test
    fun getPeopleInSpace() = runTest {
        val peopleItem1 = PeopleInSpaceEntity(id = 1, name = "Ahmet", craft = "ISS")
        val peopleItem2 = PeopleInSpaceEntity(id = 2, name = "Darth Vader", craft = "Death Star")

        dao.addPeople(peopleItem1)
        dao.addPeople(peopleItem2)

        val allPeople = dao.getPeopleInSpace()

        assertThat(allPeople).contains(peopleItem1)
        assertThat(allPeople).contains(peopleItem2)
    }

    @Test
    fun deleteAllPeople() = runTest {
        val peopleItem = PeopleInSpaceEntity(id = 2, name = "Darth Vader", craft = "Death Star")

        dao.addPeople(peopleItem)
        dao.deleteAll()

        val allPeople = dao.getPeopleInSpace()

        assertThat(allPeople.size).isEqualTo(0)
    }










}