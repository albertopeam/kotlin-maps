package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Location {

    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("lng")
    @Expose
    var lng: Double? = null

}