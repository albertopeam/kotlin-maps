package albertopeam.github.com.kotlinmaps.app.map

import albertopeam.github.com.kotlinmaps.R
import albertopeam.github.com.kotlinmaps.app.App
import albertopeam.github.com.kotlinmaps.domain.places.Place
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsPresenter.MapView {

    private lateinit var map: GoogleMap
    private lateinit var presenter: MapsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        presenter = MapsAssembler.assemble(this, App.provider)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MapsAssembler.locationNotEnabledRequestCode && resultCode == Activity.RESULT_OK){
            presenter.getNearbyPlaces()
        }
    }
    
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        findViewById<FloatingActionButton>(R.id.nearby_button).setOnClickListener({
            presenter.getNearbyPlaces()
        })
    }

    override fun showPlaces(places: List<Place>) {
        val boundsBuilder:LatLngBounds.Builder = LatLngBounds.Builder()
        places.forEach { place: Place ->
            val latLng = LatLng(place.latitude, place.longitude)
            map.addMarker(MarkerOptions()
                    .position(latLng)
                    .title(place.name)
                    .draggable(false))
            boundsBuilder.include(latLng)
        }
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 16))
    }
}
