package com.hackheroes.newoldtown.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector
import com.hackheroes.newoldtown.R
import com.xinto.taxi.Destination
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed interface AppDestination : Destination {
    @Parcelize
    object CityMap : AppDestination

    @Parcelize
    object AddIdea : AppDestination

    @Parcelize
    object Ideas : AppDestination
}

@Parcelize
enum class HomeDestination(val icon: @RawValue ImageVector, @StringRes val label: Int) : Destination {
    CITY_MAP(Icons.Filled.Map, R.string.city_map),
    ADD_IDEA(Icons.Filled.Add, R.string.add_idea),
    IDEAS(Icons.Filled.Lightbulb, R.string.ideas),
}