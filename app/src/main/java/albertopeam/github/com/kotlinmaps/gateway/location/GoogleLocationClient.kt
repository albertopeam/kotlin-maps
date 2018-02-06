package albertopeam.github.com.kotlinmaps.gateway.location

import albertopeam.github.com.kotlinmaps.domain.location.Location
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.location.NoLocationException
import android.Manifest
import android.content.Context
import android.support.v4.content.PermissionChecker
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.TimeUnit
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest

/**
 * Created by alberto.penas.amor on 4/2/18.
 * Get current location
 * Throws SecurityException and ExecutionException(ResolvableApiException)
 */
class GoogleLocationClient(private val context: Context, private val fusedLocationClient: FusedLocationProviderClient, private val settingsClient: SettingsClient):LocationService {

    private val timeout:Long = 50000
    private val timeUnit:TimeUnit = TimeUnit.MILLISECONDS

    override fun getLocation(): Location {
        if (PermissionChecker.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED){
            val locationRequest = LocationRequest()
            locationRequest.interval = 10000
            locationRequest.fastestInterval = 5000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            val settingsTask:Task<LocationSettingsResponse> = settingsClient.checkLocationSettings(builder.build())
            val locationSettingsResponse:LocationSettingsResponse = Tasks.await(settingsTask, timeout, timeUnit) //revienta sin location...
            val state:LocationSettingsStates = locationSettingsResponse.locationSettingsStates
            if (state.isLocationPresent && state.isLocationUsable){
                val locationTask:Task<android.location.Location> = fusedLocationClient.lastLocation
                val googleLocation: android.location.Location = Tasks.await(locationTask, timeout, timeUnit)
                return Location(googleLocation.latitude, googleLocation.longitude)
            }else{
                throw NoLocationException()
            }
        }else{
            throw SecurityException()
        }
    }
}