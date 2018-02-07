package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.domain.location.Location
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.location.NoLocationException
import albertopeam.github.com.kotlinmaps.domain.places.Place
import albertopeam.github.com.kotlinmaps.domain.places.SearchByTextPlacesService
import android.arch.lifecycle.LifecycleOwner
import com.github.albertopeam.infrastructure.concurrency.UseCase
import com.github.albertopeam.infrastructure.exceptions.ExceptionController

/**
 * Created by alberto.penas.amor on 7/2/18.
 */
internal class SearchByTextPlacesUseCase(exceptionController: ExceptionController,
                                         lifecycleOwner: LifecycleOwner,
                                         private val searchByTextPlacesService: SearchByTextPlacesService,
                                         private val locationService: LocationService,
                                         private val token:String) : UseCase<String, List<Place>>(exceptionController, lifecycleOwner) {

    override fun run(args: String?): List<Place> {
        try {
            var location: Location = locationService.getLocation()
            val targetLocation = "${location.latitude},${location.longitude}"
            val radiusMeters = 2500
            return searchByTextPlacesService.search(args!!, targetLocation, radiusMeters, token)
        }catch (e:NoLocationException){
            return searchByTextPlacesService.search(args!!, null, null, token)
        }

    }

}