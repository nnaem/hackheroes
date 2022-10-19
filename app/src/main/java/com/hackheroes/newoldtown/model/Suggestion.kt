package com.hackheroes.newoldtown.model

data class Suggestion(
    val title: String = "",
    val description: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val userId: String = "",
    val voteCount: Int = 0
)