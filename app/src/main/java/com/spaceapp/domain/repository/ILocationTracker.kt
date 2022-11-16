package com.spaceapp.domain.repository

import android.location.Location

interface ILocationTracker {
    suspend fun getCurrentLocation(): Location?
}