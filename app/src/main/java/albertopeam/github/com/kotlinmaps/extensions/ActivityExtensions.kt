package albertopeam.github.com.kotlinmaps.extensions

import albertopeam.github.com.kotlinmaps.R
import android.app.Activity
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.ViewGroup

/**
 * Created by alberto.penas.amor on 6/2/18.
 */
fun Activity.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val coordinatorLayout:CoordinatorLayout? = findViewById(R.id.container)
    val view:ViewGroup = findViewById(android.R.id.content)
    Snackbar.make(coordinatorLayout?:view, message, length).show()
}