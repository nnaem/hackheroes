package com.hackheroes.newoldtown.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hackheroes.newoldtown.preferences.PreferencesManager
import com.hackheroes.newoldtown.ui.theme.Theme
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.enableRotation
import ovh.plrapps.mapcompose.ui.state.MapState

class SettingsViewModel(
    private val app: Application,
    val prefs: PreferencesManager
) : ViewModel() {
    var showThemePicker by mutableStateOf(false)
        private set

    fun showThemePicker() {
        showThemePicker = true
    }

    fun dismissThemePicker() {
        showThemePicker = false
    }

    fun setTheme(theme: Theme) {
        prefs.theme = theme
    }
}