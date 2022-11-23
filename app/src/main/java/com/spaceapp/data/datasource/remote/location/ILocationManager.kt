package com.spaceapp.data.datasource.remote.location

import android.location.Location

interface ILocationManager {
    suspend fun getCurrentLocation(): Location?
}