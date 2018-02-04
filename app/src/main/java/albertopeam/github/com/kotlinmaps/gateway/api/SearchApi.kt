package albertopeam.github.com.kotlinmaps.gateway.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
interface SearchApi {
    @GET("place/nearbysearch/json")
    fun nearby(@Query("location") location: String, @Query("radius") radius: Int, @Query("key") key: String): Call<NearbyResponse>
}