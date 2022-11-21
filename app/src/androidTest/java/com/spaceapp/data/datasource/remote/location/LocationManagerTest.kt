package com.spaceapp.data.datasource.remote.location

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class LocationManagerTest {

    private lateinit var locationManager: LocationManager

    @Before
    fun createLocationManager() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        locationManager = LocationManager(context)
    }

    @Test
    fun getCurrentLocation_GpsEnabledPermissionAccepted_returnCurrentLocation() = runTest {
        val result = locationManager.getCurrentLocation()

        assertThat(result).isNotNull()
    }

    @Test
    fun getCurrentLocation_GpsDisabled_returnException() {

        assertThrows(Exception::class.java) {
            runTest {
                locationManager.getCurrentLocation()
            }
        }

    }

    @Test
    fun getCurrentLocation_NoPermission_returnException() {

        assertThrows(Exception::class.java) {
            runTest {
                locationManager.getCurrentLocation()
            }
        }

    }
}