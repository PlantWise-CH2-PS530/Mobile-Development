package com.dicoding.plantwiseapp.ui.maps

import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.dicoding.plantwiseapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)

        mapView = rootView.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(10 * 1000)

        return rootView
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        enableMyLocation()
        setMapClickListener()

        // Example: Add a marker on a specific location
        val defaultLocation = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(defaultLocation).title("Marker in Default Location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation))
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) { googleMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun setMapClickListener() {
        googleMap.setOnMapClickListener { latLng ->
            googleMap.clear() // Clear existing markers

            // Add a new marker at the clicked location
            googleMap.addMarker(MarkerOptions().position(latLng).title("Selected Location"))

            // Show a button or perform an action when a location is clicked
            showButtonForSelectedLocation(latLng)
        }
    }

    private fun showButtonForSelectedLocation(latLng: LatLng) {
        // Implement your logic to show a button or perform an action for the selected location
        // For example, you can display a button at the bottom of the fragment
        // that, when clicked, triggers an action or retrieves the latitude and longitude of the selected location.

        // Here's an example of how to get the latitude and longitude:
        val latitude = latLng.latitude
        val longitude = latLng.longitude

        // Now you can use the latitude and longitude as needed.
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(callback)
//    }
}
