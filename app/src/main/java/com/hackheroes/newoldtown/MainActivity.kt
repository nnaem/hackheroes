package com.hackheroes.newoldtown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.hackheroes.newoldtown.ui.navigation.AppDestination
import com.hackheroes.newoldtown.ui.screen.MainHomeScreen
import com.xinto.taxi.Taxi
import com.xinto.taxi.rememberBackstackNavigator

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val navigator = rememberBackstackNavigator<AppDestination>(AppDestination.Home)

                BackHandler {
                    if (!navigator.pop()) finish()
                }

                Taxi(
                    modifier = Modifier.fillMaxSize(),
                    navigator = navigator,
                    transitionSpec = { fadeIn() with fadeOut() }
                ) { destination ->
                    when (destination) {
                        is AppDestination.Home -> MainHomeScreen(navigator = navigator)
                        AppDestination.Messages -> MainHomeScreen(navigator = navigator)
                        AppDestination.Explore -> MainHomeScreen(navigator = navigator)
                        AppDestination.Profile -> MainHomeScreen(navigator = navigator)
                    }
                }
            }
        }
    }