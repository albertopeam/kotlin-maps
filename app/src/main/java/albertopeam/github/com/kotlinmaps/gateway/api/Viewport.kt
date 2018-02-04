package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Viewport {

    @SerializedName("northeast")
    @Expose
    var northeast: Northeast? = null
    @SerializedName("southwest")
    @Expose
    var southwest: Southwest? = null

}