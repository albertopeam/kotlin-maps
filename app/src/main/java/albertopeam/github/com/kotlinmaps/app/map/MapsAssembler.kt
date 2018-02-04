package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.app.Provider
import albertopeam.github.com.kotlinmaps.domain.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.gateway.api.NearbyPlacesClient
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutorFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionController
import com.github.albertopeam.infrastructure.exceptions.ExceptionControllerFactory

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class MapsAssembler{
    companion object {
        fun assemble(mapView: MapsActivity, provider: Provider): MapsPresenter {
            val useCaseExecutor:UseCaseExecutor = UseCaseExecutorFactory.provide()
            //TODO: location delegates: permission + disabled!!!
            val exceptionController:ExceptionController = ExceptionControllerFactory.provide(emptyList())
            val nearbyPlaces:NearbyPlacesService = NearbyPlacesClient(provider.searchApi)
            val getNearbyPlaces = GetNearbyPlaces(exceptionController, mapView, nearbyPlaces)
            return MapsPresenter(mapView, useCaseExecutor, getNearbyPlaces)
        }
    }
}