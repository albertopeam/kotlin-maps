package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.R
import albertopeam.github.com.kotlinmaps.app.App
import albertopeam.github.com.kotlinmaps.domain.Place
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsPresenter.MapView {

    private val TAG:String = "MapsActivity"
    private lateinit var mMap: GoogleMap
    private var presenter: MapsPresenter = MapsAssembler.assemble(this, App.provider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        findViewById<FloatingActionButton>(R.id.nearby_button).setOnClickListener({
            presenter.getNearbyPlaces()
        })
    }

    override fun showPlaces(places: List<Place>) {
        Snackbar.make(findViewById(android.R.id.content), "Found ${places.size} places", Snackbar.LENGTH_LONG).show()
    }
}
