package albertopeam.github.com.kotlinmaps.extensions

import android.app.Activity
import android.support.design.widget.Snackbar

/**
 * Created by alberto.penas.amor on 6/2/18.
 */
fun Activity.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(findViewById(android.R.id.content), message, length).show()
}