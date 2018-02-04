package albertopeam.github.com.kotlinmaps

import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutor
import com.github.albertopeam.infrastructure.concurrency.UseCaseExecutorFactory
import com.github.albertopeam.infrastructure.exceptions.ExceptionController
import com.github.albertopeam.infrastructure.exceptions.ExceptionControllerFactory

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class MapsAssembler{
    companion object {
        fun assemble(mapView:MapsActivity): MapsPresenter {
            val useCaseExecutor:UseCaseExecutor = UseCaseExecutorFactory.provide()
            //TODO: location delegates: permission + disabled!!!
            val exceptionController:ExceptionController = ExceptionControllerFactory.provide(emptyList())
            val getNearbyPlaces = GetNearbyPlaces(exceptionController, mapView)
            return MapsPresenter(mapView, useCaseExecutor, getNearbyPlaces)
        }
    }
}