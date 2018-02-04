package albertopeam.github.com.kotlinmaps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsPresenter.MapView {

    private val TAG:String = "MapsActivity"
    private lateinit var mMap: GoogleMap
    private var presenter:MapsPresenter = MapsAssembler.assemble(this)

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

    }
}
