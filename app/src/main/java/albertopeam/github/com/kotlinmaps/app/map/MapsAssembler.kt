package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.app.Provider
import albertopeam.github.com.kotlinmaps.domain.location.LocationService
import albertopeam.github.com.kotlinmaps.domain.places.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.domain.places.SearchByTextPlacesService
import albertopeam.github.com.kotlinmaps.gateway.api.NearbyPlacesClient
import albertopeam.github.com.kotlinmaps.gateway.api.SearchByTextPlacesClient
import albertopeam.github.com.kotlinmaps.gateway.location.GoogleLocationClient
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutorFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionController
import com.github.albertopeam.infrastructure.exceptions.ExceptionControllerFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionDelegate
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.SettingsClient

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class MapsAssembler{
    companion object {
        val locationNotEnabledRequestCode:Int = 1666
        fun assemble(mapView: MapsActivity, provider: Provider): MapsPresenter {
            val useCaseExecutor:UseCaseExecutor = UseCaseExecutorFactory.provide()
            val delegates:MutableList<ExceptionDelegate> = mutableListOf()
            delegates.add(LocationNotEnabledExceptionDelegate(mapView, locationNotEnabledRequestCode))
            delegates.add(NoLocationExceptionDelegate(mapView))
            val exceptionController:ExceptionController = ExceptionControllerFactory.provide(delegates)
            val nearbyPlaces:NearbyPlacesService = NearbyPlacesClient(provider.searchApi)
            val settingsClient:SettingsClient = LocationServices.getSettingsClient(mapView)
            val locationService:LocationService = GoogleLocationClient(mapView,  provider.fusedLocationClient, settingsClient)
            val getNearbyPlaces = NearbyPlacesUseCase(exceptionController, mapView, nearbyPlaces, locationService, provider.token)
            val searchByTextPlacesClient:SearchByTextPlacesService = SearchByTextPlacesClient(provider.searchApi)
            val searchByTextPlacesUseCase = SearchByTextPlacesUseCase(exceptionController, mapView, searchByTextPlacesClient, locationService, provider.token)
            val presenter = MapsPresenter(mapView, useCaseExecutor, getNearbyPlaces, searchByTextPlacesUseCase)
            delegates.add(LocationPermissionExceptionDelegate(mapView, fun() {
                presenter.getNearbyPlaces()
            }))
            return presenter
        }
    }
}