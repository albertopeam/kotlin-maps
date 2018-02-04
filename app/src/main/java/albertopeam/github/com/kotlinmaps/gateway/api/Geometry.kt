package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Geometry {
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("viewport")
    @Expose
    var viewport: Viewport? = null

}