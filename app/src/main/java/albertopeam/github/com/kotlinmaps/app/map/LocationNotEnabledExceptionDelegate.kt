package albertopeam.github.com.kotlinmaps.app.map

import android.app.Activity
import android.content.IntentSender
import com.github.albertopeam.infrastructure.exceptions.ExceptionDelegate
import com.github.albertopeam.infrastructure.exceptions.HandledException
import com.google.android.gms.common.api.ResolvableApiException
import java.lang.Exception
import java.util.concurrent.ExecutionException


/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class LocationNotEnabledExceptionDelegate(val activity:Activity, val code:Int):ExceptionDelegate{

    override fun canHandle(exception: Exception): Boolean {
        return exception is ExecutionException && exception.cause is ResolvableApiException
    }

    override fun handle(exception: Exception): HandledException {
        class LocationNotEnabledHandledException:HandledException(exception) {
            override fun recover() {
                try {
                    val executionException= exception as ExecutionException
                    val resolvable = executionException.cause as ResolvableApiException
                    resolvable.startResolutionForResult(activity, code)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

            }
        }
        return LocationNotEnabledHandledException()
    }

}