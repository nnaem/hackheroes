package com.hackheroes.newoldtown.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.hackheroes.newoldtown.ui.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.ui.state.MapState

val viewModelModule = module {
    viewModelOf(::SettingsViewModel)
}

val state: MapState by mutableStateOf(
    MapState(4, 4096, 4096) {
        scroll(0.5, 0.5)
    }.apply {
        enableRotation()
    }
)