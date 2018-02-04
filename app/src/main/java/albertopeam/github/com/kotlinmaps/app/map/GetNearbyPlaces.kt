package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.domain.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.domain.Place
import android.arch.lifecycle.LifecycleOwner
import com.github.albertopeam.infrastructure.concurrency.UseCase
import com.github.albertopeam.infrastructure.exceptions.ExceptionController

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class GetNearbyPlaces(exceptionController: ExceptionController, lifecycleOwner: LifecycleOwner, private val nearbyPlacesService: NearbyPlacesService) : UseCase<Unit, List<Place>>(exceptionController, lifecycleOwner) {

     override fun run(args: Unit?): List<Place> {
         val location = "43.333038,-8.369988" //TODO: hardcoded until locatio service is available
         val radius = 2500
         return nearbyPlacesService.nearby(location, radius, "AIzaSyBXfAzdkMfEKpa8Rsm7VwVSD6JHKa-hku0")
    }

}