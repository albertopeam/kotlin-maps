package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.domain.places.Place
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.exceptions.HandledException

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class MapsPresenter(private val mapView: MapView,
                             private val useCaseExecutor: UseCaseExecutor,
                             private val nearbyPlacesUseCase: NearbyPlacesUseCase,
                             private val searchByTextPlacesUseCase: SearchByTextPlacesUseCase) {

    interface MapView {
        fun showPlaces(places:List<Place>)
    }

    fun getNearbyPlaces() {
        useCaseExecutor.execute(null, nearbyPlacesUseCase, { response: List<Place> -> Unit
            mapView.showPlaces(response)
        }, { handledException: HandledException -> Unit
            handledException.recover()
        })
    }

    fun searchPlaces(query:String?){
        useCaseExecutor.execute(query, searchByTextPlacesUseCase, { response: List<Place> -> Unit
            mapView.showPlaces(response)
        }, {handledException: HandledException -> Unit
            handledException.recover()
        })
    }

}