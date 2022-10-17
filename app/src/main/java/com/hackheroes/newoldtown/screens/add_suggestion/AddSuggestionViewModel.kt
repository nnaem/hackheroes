package com.hackheroes.newoldtown.screens.edit_suggestion

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.hackheroes.newoldtown.HOME_SCREEN
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.common.ext.isValidDescription
import com.hackheroes.newoldtown.common.ext.isValidTitle
import com.hackheroes.newoldtown.common.snackbar.SnackbarManager
import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.model.service.StorageService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSuggestionViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : NewOldTownViewModel() {
    var suggestion = mutableStateOf(Suggestion())
        private set

    val mapCenter = LatLng(52.055, 20.44)

    fun initialize() {
        viewModelScope.launch(showErrorExceptionHandler) {
            suggestion.value = Suggestion(userId = accountService.getUserId())
        }
    }

    fun onAddClick(popUpScreen: () -> Unit, openScreen: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            if (!suggestion.value.title.isValidTitle()) {
                SnackbarManager.showMessage(R.string.suggestion_title_error)
                return@launch
            }

            if (!suggestion.value.description.isValidDescription()) {
                SnackbarManager.showMessage(R.string.suggestion_description_error)
                return@launch
            }


            val newSuggestion = suggestion.value.copy(userId = accountService.getUserId())
            saveSuggestion(newSuggestion, popUpScreen, openScreen)
        }
    }

    fun onExit(popUpScreen: () -> Unit) {
        popUpScreen();
    }

    fun onTitleChange(newValue: String) {
        suggestion.value = suggestion.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        suggestion.value = suggestion.value.copy(description = newValue)
    }


    fun onLatChange(d: Double) {
        suggestion.value = suggestion.value.copy(latitude = d)
    }

    fun onLngChange(d: Double) {
        suggestion.value = suggestion.value.copy(longitude = d)
    }

    private fun saveSuggestion(suggestion: Suggestion, popUpScreen: () -> Unit, openScreen: (String) -> Unit) {
        storageService.saveSuggestion(suggestion) { error ->
            if (error == null)
            {
                SnackbarManager.showMessage(R.string.succesfully_added_suggestion)
                popUpScreen()
                openScreen(HOME_SCREEN)
            }
            else
            {
                onError(error)
            }
        }
    }
}
