package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class OpeningHours {

    @SerializedName("open_now")
    @Expose
    var open_now: Boolean? = null
    @SerializedName("weekday_text")
    @Expose
    var weekday_text: List<Any> = ArrayList()

}