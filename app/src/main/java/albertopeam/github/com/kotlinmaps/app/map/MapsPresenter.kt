package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.domain.places.Place
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.exceptions.HandledException

/**
 * Created by alberto.penas.amor on 4/2/18.
 */

internal interface MapView {
    fun showPlaces(places:List<Place>)
    fun showEmpty()
}

interface MapPresenter{
    fun getNearbyPlaces()
    fun searchPlaces(query:String?)
}

internal class MapsPresenter(private val mapView: MapView,
                             private val useCaseExecutor: UseCaseExecutor,
                             private val nearbyPlacesUseCase: NearbyPlacesUseCase,
                             private val searchByTextPlacesUseCase: SearchByTextPlacesUseCase): MapPresenter {

    override fun getNearbyPlaces() {
        useCaseExecutor.execute(null, nearbyPlacesUseCase, { response: List<Place> -> Unit
            if (response.isEmpty()){
                mapView.showEmpty()
            }else{
                mapView.showPlaces(response)
            }
        }, { handledException: HandledException -> Unit
            handledException.recover()
        })
    }

    override fun searchPlaces(query:String?){
        useCaseExecutor.execute(query, searchByTextPlacesUseCase, { response: List<Place> -> Unit
            if (response.isEmpty()){
                mapView.showEmpty()
            }else{
                mapView.showPlaces(response)
            }
        }, {handledException: HandledException -> Unit
            handledException.recover()
        })
    }

}