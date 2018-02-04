package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Result {

    @SerializedName("geometry")
    @Expose
    var geometry: Geometry? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("place_id")
    @Expose
    var place_id: String? = null
    @SerializedName("reference")
    @Expose
    var reference: String? = null
    @SerializedName("scope")
    @Expose
    var scope: String? = null
    @SerializedName("types")
    @Expose
    var types: List<String> = ArrayList()
    @SerializedName("vicinity")
    @Expose
    var vicinity: String? = null
    @SerializedName("opening_hours")
    @Expose
    var opening_hours: OpeningHours? = null
    @SerializedName("rating")
    @Expose
    var rating: Double? = null
    @SerializedName("photos")
    @Expose
    var photos: List<Photo> = ArrayList()

}