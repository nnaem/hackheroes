package com.hackheroes.newoldtown.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.hackheroes.newoldtown.R
import com.xinto.taxi.Destination
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed interface AppDestination : Destination {
    @Parcelize
    object Home : AppDestination

    @Parcelize
    object Messages : AppDestination

    @Parcelize
    object Explore : AppDestination

    @Parcelize
    object Profile : AppDestination
}

@Parcelize
enum class HomeDestination(
    val icon: @RawValue ImageVector,
    @StringRes val label: Int
) : Destination {
    HOME(Icons.Default.Home, R.string.home),
    MESSAGES(Icons.Default.Chat, R.string.messages),
    EXPLORE(Icons.Default.Search, R.string.explore),
    PROFILE(Icons.Default.AccountCircle, R.string.profile)
}