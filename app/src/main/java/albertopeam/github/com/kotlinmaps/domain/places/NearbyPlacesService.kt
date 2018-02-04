package albertopeam.github.com.kotlinmaps.domain.places

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
interface NearbyPlacesService{
    fun nearby(location:String, radius:Int, key:String): List<Place>
}