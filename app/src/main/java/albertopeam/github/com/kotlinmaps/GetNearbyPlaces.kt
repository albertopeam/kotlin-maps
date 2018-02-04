package albertopeam.github.com.kotlinmaps

import android.arch.lifecycle.LifecycleOwner
import com.github.albertopeam.infrastructure.concurrency.UseCase
import com.github.albertopeam.infrastructure.exceptions.ExceptionController

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class GetNearbyPlaces(exceptionController: ExceptionController, lifecycleOwner: LifecycleOwner) : UseCase<Unit, List<Place>>(exceptionController, lifecycleOwner) {

    override fun run(args: Unit?): List<Place> {
        //TODO: inject services: location + google search api
        return emptyList()
    }

}