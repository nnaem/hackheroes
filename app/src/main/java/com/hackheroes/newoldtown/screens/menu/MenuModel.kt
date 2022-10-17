package com.hackheroes.newoldtown.screens.menu

import androidx.lifecycle.viewModelScope
import com.hackheroes.newoldtown.ADD_SUGGESTION_SCREEN
import com.hackheroes.newoldtown.HOME_SCREEN
import com.hackheroes.newoldtown.SPLASH_SCREEN
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.model.service.StorageService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService
) : NewOldTownViewModel() {

    fun onExit(openScreen: (String) -> Unit) = openScreen(HOME_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }

    fun onGoToMapClick(openScreen: (String) -> Unit) = openScreen(HOME_SCREEN)

    fun onAddSuggestionClick(openScreen: (String) -> Unit) = openScreen(ADD_SUGGESTION_SCREEN)

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            storageService.deleteAllForUser(accountService.getUserId()) { error ->
                if (error == null) deleteAccount(restartApp) else onError(error)
            }
        }
    }

    private fun deleteAccount(restartApp: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.deleteAccount { error ->
                if (error == null) restartApp(SPLASH_SCREEN) else onError(error)
            }
        }
    }
}