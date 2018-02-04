package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.app.Provider
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.places.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.gateway.api.NearbyPlacesClient
import albertopeam.github.com.kotlinmaps.gateway.location.GoogleLocationClient
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutorFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionController
import com.github.albertopeam.infrastructure.exceptions.ExceptionControllerFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionDelegate

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class MapsAssembler{
    companion object {
        fun assemble(mapView: MapsActivity, provider: Provider): MapsPresenter {
            val useCaseExecutor:UseCaseExecutor = UseCaseExecutorFactory.provide()
            val delegates:MutableList<ExceptionDelegate> = mutableListOf()
            val exceptionController:ExceptionController = ExceptionControllerFactory.provide(delegates)
            val nearbyPlaces:NearbyPlacesService = NearbyPlacesClient(provider.searchApi)
            val locationService:LocationService = GoogleLocationClient(provider.fusedLocationClient)
            val getNearbyPlaces = GetNearbyPlaces(exceptionController, mapView, nearbyPlaces, locationService)
            val presenter = MapsPresenter(mapView, useCaseExecutor, getNearbyPlaces)
            delegates.add(LocationPermissionExceptionDelegate(mapView, fun() {
                presenter.getNearbyPlaces()
            }))
            return presenter
        }
    }
}