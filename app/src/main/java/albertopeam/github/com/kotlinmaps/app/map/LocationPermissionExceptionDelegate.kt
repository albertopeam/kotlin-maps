package albertopeam.github.com.kotlinmaps.app.map

import android.Manifest
import android.app.Activity
import com.github.albertopeam.infrastructure.exceptions.ExceptionDelegate
import com.github.albertopeam.infrastructure.exceptions.HandledException
import java.lang.Exception
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest


/**
 * Created by alberto.penas.amor on 4/2/18.
 */
internal class LocationPermissionExceptionDelegate(val activity:Activity, val permissionGranted: () -> Unit):ExceptionDelegate{

    override fun canHandle(exception: Exception): Boolean {
        return exception is SecurityException
    }

    override fun handle(exception: Exception): HandledException {
        class PermissionHandledException:HandledException(exception) {
            override fun recover() {
                Dexter.withActivity(activity)
                        .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        .withListener(object : PermissionListener {
                            override fun onPermissionGranted(response: PermissionGrantedResponse) {
                                permissionGranted()
                            }

                            override fun onPermissionDenied(response: PermissionDeniedResponse) {}

                            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                                token.continuePermissionRequest()
                            }
                        }).check()
            }
        }
        return PermissionHandledException()
    }

}