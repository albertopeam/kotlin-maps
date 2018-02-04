package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Photo {

    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("html_attributions")
    @Expose
    var html_attributions: List<String> = ArrayList()
    @SerializedName("photo_reference")
    @Expose
    var photo_reference: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

}