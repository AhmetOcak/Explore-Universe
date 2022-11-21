package com.spaceapp.data.datasource.local.mars_photos.db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MarsPhotoDaoTest {

    private lateinit var dao: MarsPhotoDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.marsPhotoDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addMarsPhoto() = runTest {
        val marsPhotoItem = MarsPhotoEntity(id = 1, roverName = "Dragon", image = "Mars Sky", date = "11.11.2022")
        dao.addMarsPhoto(marsPhotoItem)

        val allMarshPhotos = dao.getMarsPhotos()

        assertThat(allMarshPhotos).contains(marsPhotoItem)
    }

    @Test
    fun getMarsPhotos() = runTest {
        val marshPhotoItem1 = MarsPhotoEntity(id = 1, roverName = "Dragon", image = "Mars Sky", date = "11.11.2022")
        val marsPhotoItem2 = MarsPhotoEntity(id = 2, roverName = "Artemis", image = "Mars Land", date = "01.01.2000")

        dao.addMarsPhoto(marshPhotoItem1)
        dao.addMarsPhoto(marsPhotoItem2)

        val allMarsPhotos = dao.getMarsPhotos()

        assertThat(allMarsPhotos).contains(marshPhotoItem1)
        assertThat(allMarsPhotos).contains(marsPhotoItem2)
    }

    @Test
    fun deleteAllMarsPhotos() = runTest {
        val marshPhotoItem1 = MarsPhotoEntity(id = 1, roverName = "Dragon", image = "Mars Sky", date = "11.11.2022")

        dao.addMarsPhoto(marshPhotoItem1)
        dao.deleteAll()

        val allMarsPhotos = dao.getMarsPhotos()

        assertThat(allMarsPhotos.size).isEqualTo(0)
    }
}