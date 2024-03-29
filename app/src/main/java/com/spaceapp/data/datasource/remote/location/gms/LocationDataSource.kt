package com.spaceapp.data.datasource.remote.location.gms

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.spaceapp.data.datasource.remote.location.ILocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import android.Manifest
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlin.coroutines.resume

class LocationTrackerGms @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : ILocationTracker {

    var location: Location? = null

    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission) {
            throw Exception("No Permission")
        } else if (!isGpsEnabled) {
            throw Exception("Gps Disabled")
        }


        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    this@LocationTrackerGms.location = location
                }
            }
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) cont.resume(result)
                    else cont.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    if (it == null) {
                        locationClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            null
                        ).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                cont.resume(location)
                            } else {
                                cont.cancel()
                            }
                        }

                    } else {
                        cont.resume(it)
                    }
                }
                addOnFailureListener {
                    cont.cancel(it.cause)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}