package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.R
import albertopeam.github.com.kotlinmaps.domain.location.NoLocationException
import albertopeam.github.com.kotlinmaps.extensions.snack
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
internal class NoLocationExceptionDelegate(val activity:Activity):ExceptionDelegate{

    override fun canHandle(exception: Exception): Boolean {
        return exception is NoLocationException
    }

    override fun handle(exception: Exception): HandledException {
        class NoLocationHandledException:HandledException(exception) {
            override fun recover() {
                activity.snack(activity.resources.getString(R.string.no_location_available))
            }
        }
        return NoLocationHandledException()
    }

}