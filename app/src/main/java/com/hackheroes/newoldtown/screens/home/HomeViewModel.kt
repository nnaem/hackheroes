package com.hackheroes.newoldtown.screens.home

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hackheroes.newoldtown.ADD_SUGGESTION_SCREEN
import com.hackheroes.newoldtown.SETTINGS_SCREEN
import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.model.service.StorageService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,
) : NewOldTownViewModel() {
    var allSuggestions = mutableStateMapOf<String, Suggestion>()
        private set

    var userSuggestions = mutableStateMapOf<String, Suggestion>()
        private set

    fun addListener() {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.addListener(accountService.getUserId(), ::onUserDocEvent, ::onError)
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.addListener(::onUserDocEvent, ::onError)
        }
    }

    fun removeListener() {
        viewModelScope.launch(showErrorExceptionHandler) { storageService.removeListener() }
    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(ADD_SUGGESTION_SCREEN)

    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    private fun onUserDocEvent(wasDocumentDeleted: Boolean, suggestion: Suggestion) {
        if (wasDocumentDeleted) {
            userSuggestions.remove(suggestion.title)
        } else {
            userSuggestions[suggestion.title] = suggestion
        }
    }
}