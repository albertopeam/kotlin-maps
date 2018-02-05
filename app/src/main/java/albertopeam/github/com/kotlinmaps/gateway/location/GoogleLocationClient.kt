package albertopeam.github.com.kotlinmaps.gateway.location

import albertopeam.github.com.kotlinmaps.domain.location.Location
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.location.NoLocationException
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.PermissionChecker
import android.util.Log
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import java.util.concurrent.TimeUnit


/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class GoogleLocationClient(private val context: Context, private val fusedLocationClient: FusedLocationProviderClient, private val settingsClient: SettingsClient):LocationService {

    private val timeout:Long = 50000
    private val timeUnit:TimeUnit = TimeUnit.MILLISECONDS


    override fun getLocation(): Location {
        //TODO: la mierda de servicio no puede ir sincrona, hay que bloquear con un while
        //TODO: el isSucessful siem[re da false
        //TODO: use CountdownLatch, if no location connect to callbacks and also use the CountdownLatch
        //https://developer.android.com/training/location/retrieve-current.html
//        val task:Task<android.location.Location> = fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<android.location.Location> ->
//            lock.countDown()
//        }.addOnFailureListener { exception: Exception ->
//            lock.countDown()
//        }
//        val task:Task<android.location.Location> = fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location? ->
//            if (location != null) {
//                // Logic to handle location object
//                Log.d("GoogleLocationClient", "location: $location")
//            }
//        }

        //lock.await()
        if (PermissionChecker.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED){
            //TODO: IN ORDER
        }else{
            throw SecurityException()
        }
        val settingsTask:Task<LocationSettingsResponse> = settingsClient.checkLocationSettings(LocationSettingsRequest.Builder().build())
        val locationSettingsResponse:LocationSettingsResponse = Tasks.await(settingsTask, timeout, timeUnit)
        if (settingsTask.isSuccessful){
            val state:LocationSettingsStates = locationSettingsResponse.locationSettingsStates
            if (state.isLocationPresent && state.isLocationUsable){
                //TODO: temp: OK, location enabled
                Log.d("", "")
                val locationTask:Task<android.location.Location> = fusedLocationClient.lastLocation
                val locationAvailabilityTask:Task<LocationAvailability> =  fusedLocationClient.locationAvailability
                val locationAvailability:LocationAvailability = Tasks.await(locationAvailabilityTask, timeout, timeUnit)
                if (locationAvailability.isLocationAvailable){
                    val googleLocation: android.location.Location = Tasks.await(locationTask, timeout, timeUnit)
                    if (locationTask.isSuccessful){
                        return Location(googleLocation.latitude, googleLocation.longitude)
                    }else{
                        //SECURITY???
                        Log.d("GoogleLocationClient", "location task not succesful")
                        throw SecurityException(fusedLocationClient.lastLocation.exception)
                    }
                }else{
                    //TODO: location disabled
                    throw UnsupportedOperationException()
                    Log.d("GoogleLocationClient", "location old or not available")
                }
            }else{
                Log.d("GoogleLocationClient", "location not present or not usable")
                //TODO: throw some exception to recoveber from ResolvableApiException
//                // Location settings are not satisfied, but this can be fixed
//                // by showing the user a dialog.
//                try {
//                    // Show the dialog by calling startResolutionForResult(),
//                    // and check the result in onActivityResult().
//                    val resolvable = e as ResolvableApiException
//                    resolvable.startResolutionForResult(this@MainActivity,
//                            REQUEST_CHECK_SETTINGS)
//                } catch (sendEx: IntentSender.SendIntentException) {
//                    // Ignore the error.
//                }
//
//                throw settingsTask.exception
                //TODO: temp: OK, location disabled
                throw NoLocationException()
            }
        }else{
            //TODO: temp
            Log.d("GoogleLocationClient", "settingsTask not succesfull")
            throw NoLocationException()
        }




    }
}