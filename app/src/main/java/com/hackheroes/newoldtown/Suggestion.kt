package com.hackheroes.newoldtown

data class Suggestion(
    val title: String, val description: String, val author: String, val lat: Double, val lng: Double
) {
    constructor(): this("", "", "", 0.0, 0.0)
}