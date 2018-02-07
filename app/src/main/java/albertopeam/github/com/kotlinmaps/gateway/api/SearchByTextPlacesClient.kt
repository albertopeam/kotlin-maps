package albertopeam.github.com.kotlinmaps.gateway.api

import albertopeam.github.com.kotlinmaps.domain.places.NearbyPlacesService
import albertopeam.github.com.kotlinmaps.domain.places.NetworkException
import albertopeam.github.com.kotlinmaps.domain.places.Place
import albertopeam.github.com.kotlinmaps.domain.places.SearchByTextPlacesService

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class SearchByTextPlacesClient(val searchApi:SearchApi): SearchByTextPlacesService {

    override fun search(text: String, location: String?, radius: Int?, key: String): List<Place> {
        val response = searchApi.search(text, location, radius, key).execute()
        if (response.isSuccessful){
            val nearbys = response.body()
            return nearbys!!.results.map { result ->
                Place(result.id!!, result.name!!, result.place_id!!, result.geometry?.location?.lat!!, result.geometry?.location?.lng!!) }
        }else{
            throw NetworkException()
        }
    }

}