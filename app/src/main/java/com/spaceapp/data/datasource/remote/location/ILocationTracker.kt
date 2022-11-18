package com.spaceapp.data.datasource.remote.location

import android.location.Location

interface ILocationTracker {
    suspend fun getCurrentLocation(): Location?
}