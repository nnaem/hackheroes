package com.hackheroes.newoldtown.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.hackheroes.newoldtown.LOGIN_SCREEN
import com.hackheroes.newoldtown.SPLASH_SCREEN
import com.hackheroes.newoldtown.HOME_SCREEN
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
) : NewOldTownViewModel() {
    val showError = mutableStateOf(false)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false

        if (accountService.hasUser())
        {
            openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
        }
        else
        {
            openAndPopUp(LOGIN_SCREEN, SPLASH_SCREEN)
        }
    }
}