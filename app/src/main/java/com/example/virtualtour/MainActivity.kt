package com.example.virtualtour

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.virtualtour.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val virtualTourList = mutableListOf<VirtualTour>()
    private val LOCATION_PERMISSION_REQUEST = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupLocation()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = VirtualTourAdapter(virtualTourList) { tour ->
                openVirtualTour(tour)
            }
        }
    }

    private fun setupLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        
        if (checkLocationPermission()) {
            getCurrentLocation()
        } else {
            requestLocationPermission()
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(
                    this,
                    "Izin lokasi diperlukan untuk fitur ini",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getCurrentLocation() {
        if (!checkLocationPermission()) return

        binding.progressBar.visibility = View.VISIBLE
        
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            binding.progressBar.visibility = View.GONE
            
            location?.let {
                loadVirtualTours(it)
            } ?: run {
                Toast.makeText(
                    this,
                    "Tidak dapat mendapatkan lokasi. Pastikan GPS aktif.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }.addOnFailureListener {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(
                this,
                "Error: ${it.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun loadVirtualTours(currentLocation: Location) {
        // Sample virtual tour data - replace with your actual data
        virtualTourList.clear()
        virtualTourList.addAll(listOf(
            VirtualTour(
                "Museum Nasional Indonesia",
                -6.1754,
                106.8272,
                "https://virtual-tour-museum.com/nasional",
                "https://example.com/museum-nasional.jpg"
            ),
            VirtualTour(
                "Galeri Seni Modern",
                -6.1932,
                106.8236,
                "https://virtual-tour-gallery.com/modern",
                "https://example.com/gallery-modern.jpg"
            ),
            VirtualTour(
                "Taman Mini Indonesia Indah",
                -6.3024,
                106.8951,
                "https://virtual-tour-tmii.com",
                "https://example.com/tmii.jpg"
            )
        ))

        // Calculate distance for each tour
        virtualTourList.forEach { tour ->
            tour.distance = Haversine.distance(
                currentLocation.latitude,
                currentLocation.longitude,
                tour.latitude,
                tour.longitude
            )
        }

        // Sort by distance
        virtualTourList.sortBy { it.distance }

        // Update RecyclerView
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun openVirtualTour(tour: VirtualTour) {
        Intent(this, VirtualTourWebViewActivity::class.java).apply {
            putExtra("TOUR_URL", tour.url)
            startActivity(this)
        }
    }
}
