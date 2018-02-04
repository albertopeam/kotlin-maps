package albertopeam.github.com.kotlinmaps

import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.exceptions.HandledException

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class MapsPresenter(val mapView:MapView, val useCaseExecutor: UseCaseExecutor, val getNearbyPlaces: GetNearbyPlaces) {

    interface MapView {
        fun showPlaces(places:List<Place>)
    }

    fun getNearbyPlaces() {
        useCaseExecutor.execute(null, getNearbyPlaces, {
            response: List<Place> -> Unit
            mapView.showPlaces(response)
        }, {
            handledException: HandledException -> Unit
            handledException.recover()
        })
    }

}