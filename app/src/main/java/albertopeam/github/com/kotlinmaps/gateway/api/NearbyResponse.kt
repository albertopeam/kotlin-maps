package albertopeam.github.com.kotlinmaps.gateway.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class NearbyResponse {

    @SerializedName("html_attributions")
    @Expose
    var html_attributions: List<Any> = ArrayList()
    @SerializedName("next_page_token")
    @Expose
    var next_page_token: String? = null
    @SerializedName("results")
    @Expose
    var results: List<Result> = ArrayList<Result>()
    @SerializedName("status")
    @Expose
    var status: String? = null

}