package albertopeam.github.com.kotlinmaps.gateway.location

import albertopeam.github.com.kotlinmaps.domain.location.Location
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.location.NoLocationException
import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class GoogleLocationClient(private val fusedLocationClient: FusedLocationProviderClient):LocationService{

    @SuppressLint("MissingPermission")
    override fun getLocation(): Location {
        throw UnsupportedOperationException()
        //TODO: la mierda de servicio no puede ir sincrona, hay que bloquear con un while
        //TODO: el isSucessful siem[re da false
        if (fusedLocationClient.lastLocation.isSuccessful){
            fusedLocationClient.lastLocation.getResult()
            val googleLocation: android.location.Location = fusedLocationClient.lastLocation.result ?: throw NoLocationException()
            return Location(googleLocation.latitude, googleLocation.longitude)
        }else{
            throw SecurityException(fusedLocationClient.lastLocation.exception)
        }
    }
}