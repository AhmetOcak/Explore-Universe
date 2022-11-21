package com.spaceapp.data.datasource.local.weather_condition_db.room.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class WeatherConditionDaoTest {

    private lateinit var dao: WeatherConditionDao
    private lateinit var db: SpaceDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SpaceDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.weatherConditionDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun addWeatherCondition() = runTest {
        val weatherConditionItem = WeatherConditionEntity(
            id = 1,
            temp = 23.3,
            description = "clear sky",
            mainDescription = "clear"
        )

        dao.addWeatherCondition(weatherConditionItem)

        val weatherCondition = dao.getWeatherCondition()

        assertThat(weatherCondition).isEqualTo(weatherConditionItem)
    }

    @Test
    fun getWeatherCondition() = runTest {
        val weatherConditionItem = WeatherConditionEntity(
            id = 1,
            temp = 23.3,
            description = "clear sky",
            mainDescription = "clear"
        )

        dao.addWeatherCondition(weatherConditionItem)

        val weatherCondition = dao.getWeatherCondition()

        assertThat(weatherCondition).isEqualTo(weatherCondition)
    }

    @Test
    fun updateWeatherCondition() = runTest {
        val weatherConditionItem = WeatherConditionEntity(
            id = 1,
            temp = 23.3,
            description = "clear sky",
            mainDescription = "clear"
        )

        val newWeatherConditionItem = WeatherConditionEntity(
            id = 1,
            temp = 5.3,
            description = "heavy rain",
            mainDescription = "rain"
        )

        dao.addWeatherCondition(weatherConditionItem)
        dao.updateWeatherCondition(newWeatherConditionItem)

        val weatherCondition = dao.getWeatherCondition()

        assertThat(weatherCondition).isEqualTo(weatherCondition)
    }
}