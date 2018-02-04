package albertopeam.github.com.kotlinmaps.gateway.location

//package albertopeam.github.com.kotlinmaps
//
//import android.content.Context
//import android.location.Location
//import android.os.Looper
//import android.util.Log
//import com.google.android.gms.common.api.GoogleApiClient
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationListener
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.location.LocationServices.FusedLocationApi
//import java.util.concurrent.TimeUnit
//
///**
// * Created by alberto.penas.amor on 3/2/18.
// */
//internal class GoogleLocationService(private val context: Context, private val looper: Looper, private val timeoutToConnectSecs: Int, maxRetries: Int, retryTimeMs: Int) : LocationClient, LocationListener {
//
//    private val fusedLocationProviderClient: FusedLocationProviderClient? = null
//    private var locationRequest: LocationRequest? = null
//    private val maxRetries = 20
//    private val retryTimeMs = 250
//    private var stopped = true
//
//    @Synchronized
//    @Throws(LocationConnectionException::class)
//    override fun start(): Boolean {
//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = GoogleApiClient.Builder(context)
//                    .addApi(LocationServices.API)
//                    .build()
//        }
//        val result = mGoogleApiClient!!.blockingConnect(timeoutToConnectSecs.toLong(), TimeUnit.SECONDS)
//        if (!result.isSuccess) {
//            stopped = true
//            throw LocationConnectionException(result.errorMessage!!)
//        }
//        stopped = false
//        return result.isSuccess
//    }
//
//
//    @Synchronized override fun stop(): Boolean {
//        locationRequest = null
//        stopped = true
//        if (hasApiClient()) {
//            if (mGoogleApiClient!!.isConnected) {
//                FusedLocationProviderClient.
//                FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
//                mGoogleApiClient!!.disconnect()
//                return true
//            }
//        }
//        return false
//    }
//
//
//    @Throws(NullPointerException::class, IllegalStateException::class, SecurityException::class, IllegalArgumentException::class)
//    override fun currentLocation(): Location {
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
//        fusedLocationProviderClient.lastLocation.
//                inyectar objecto
//                parece que las apis cambiaron...
//
//
//
//
//
//        if (!mGoogleApiClient!!.isConnected) {
//            throw IllegalArgumentException()
//        }
//        if (doItHaveConnectToLocationUpdates()) {
//            createLocationRequest()
//            connectToLocatioUpdates()
//        }
//        var retries = 0
//        var location: Location? = null
//        while (retries < maxRetries || stopped) {
//            try {
//                location = FusedLocationApi.getLastLocation(mGoogleApiClient)
//                if (location == null) {
//                    Thread.sleep(retryTimeMs.toLong())
//                } else {
//                    break
//                }
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//                throw NullPointerException()
//            } catch (e: Exception) {
//                e.printStackTrace()
//                throw e
//            } finally {
//                retries++
//            }
//        }
//        if (location == null) {
//            throw NullPointerException()
//        }
//        return es.hiro.data.model.Location(location.latitude, location.longitude)
//    }
//
//    /**
//     * Needed to get locations
//     * @throws SecurityException
//     */
//    @Throws(SecurityException::class)
//    private fun connectToLocatioUpdates() {
//        FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this, looper)
//    }
//
//    private fun createLocationRequest() {
//        locationRequest = LocationRequest.create()
//        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        locationRequest!!.interval = 1000
//        locationRequest!!.fastestInterval = 5000
//    }
//
//
//    private fun doItHaveConnectToLocationUpdates(): Boolean {
//        return locationRequest == null
//    }
//
//
//    private fun hasApiClient(): Boolean {
//        return mGoogleApiClient != null
//    }
//
//
//    private fun hasNotApiClient(): Boolean {
//        return !hasApiClient()
//    }
//
//    override fun onLocationChanged(location: Location) {
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "location received: " + location.toString())
//        }
//    }
//
//    companion object {
//        private val TAG = GoogleLocationService::class.java.simpleName
//    }
//}