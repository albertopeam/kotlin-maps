package albertopeam.github.com.kotlinmaps.app

import android.app.Application

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class App: Application() {

    companion object {
        lateinit var provider:Provider
    }


    override fun onCreate() {
        super.onCreate()
        provider = Provider(this)
    }
}