package com.example.virtualtour

data class VirtualTour(
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val url: String,
    val thumbnailUrl: String,
    var distance: Double = 0.0
)
