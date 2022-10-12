package com.hackheroes.newoldtown.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.hackheroes.newoldtown.ui.navigation.AppDestination
import com.hackheroes.newoldtown.ui.navigation.HomeDestination
import com.xinto.taxi.BackstackNavigator
import com.xinto.taxi.Taxi
import com.xinto.taxi.rememberNavigator

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(navigator: BackstackNavigator<AppDestination>) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState(),
        canScroll = { true }
    )

    val mainRootNavigator = rememberNavigator(HomeDestination.HOME)
    val currentDestination = mainRootNavigator.currentDestination

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(mainRootNavigator.currentDestination.label),
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                HomeDestination.values().forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination == destination,
                        icon = { Icon(destination.icon, stringResource(destination.label)) },
                        label = { Text(stringResource(destination.label)) },
                        onClick = { mainRootNavigator.replace(destination) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier.padding(paddingValues)
        ) {
            Taxi(
                modifier = Modifier.weight(1f, true),
                navigator = mainRootNavigator,
                transitionSpec = { fadeIn() with fadeOut() }
            ) { destination ->
                when (destination) {
                    HomeDestination.HOME -> HomeScreen()
                    HomeDestination.MESSAGES -> MessagesScreen()
                    HomeDestination.EXPLORE -> ExploreScreen()
                    HomeDestination.PROFILE -> ProfileScreen()
                }
            }
        }
    }
}