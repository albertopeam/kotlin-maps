package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.places.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.domain.places.Place
import android.arch.lifecycle.LifecycleOwner
import com.github.albertopeam.infrastructure.concurrency.UseCase
import com.github.albertopeam.infrastructure.exceptions.ExceptionController

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class NearbyPlacesUseCase(exceptionController: ExceptionController,
                                   lifecycleOwner: LifecycleOwner,
                                   private val nearbyPlacesService: NearbyPlacesService,
                                   private val locationService: LocationService,
                                   private val token:String) : UseCase<Unit, List<Place>>(exceptionController, lifecycleOwner) {

     override fun run(args: Unit?): List<Place> {
         val location = locationService.getLocation()
         val targetLocation = "${location.latitude},${location.longitude}"
         val radiusMeters = 2500
         return nearbyPlacesService.nearby(targetLocation, radiusMeters, token)
    }

}