package com.dicoding.plantwiseapp.ui.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dicoding.plantwiseapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MapsFragment1 : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var btnShowPlant: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        mapFragment?.getMapAsync{ googleMap ->
            mMap = googleMap

            mMap.setOnMapClickListener { latLng ->
                mMap.clear()
                mMap.addMarker(MarkerOptions().position(latLng))
                showButton()
            }
        }

        btnShowPlant.setOnClickListener {
            val selectedLatLng = mMap.cameraPosition.target
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val bundle = Bundle()
            bundle.putDouble("latitude", selectedLatLng.latitude)
            bundle.putDouble("longitude", selectedLatLng.longitude)
            bundle.putString("currentDate", currentDate)

            val calendar = Calendar.getInstance()
            calendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(currentDate)!!
            calendar.add(Calendar.DAY_OF_YEAR, -30)
            val thirtyDaysBefore = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            bundle.putString("thirtyDaysBefore", thirtyDaysBefore)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val jakarta = LatLng(-6.2088, 106.8456)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 10f))
    }

    private fun showButton() {
        btnShowPlant.visibility = View.VISIBLE
    }
}