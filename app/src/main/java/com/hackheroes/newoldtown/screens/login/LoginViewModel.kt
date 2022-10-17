package com.hackheroes.newoldtown.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hackheroes.newoldtown.LOGIN_SCREEN
import com.hackheroes.newoldtown.HOME_SCREEN
import com.hackheroes.newoldtown.SIGN_UP_SCREEN
import com.hackheroes.newoldtown.R.string as AppText
import com.hackheroes.newoldtown.common.ext.isValidEmail
import com.hackheroes.newoldtown.common.snackbar.SnackbarManager
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
) : NewOldTownViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.authenticate(email, password) { error ->
                if (error == null) {
                    openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
                } else {
                    onError(error)
                }
            }
        }
    }

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.sendRecoveryEmail(email) { error ->
                if (error != null) {
                    onError(error)
                } else {
                    SnackbarManager.showMessage(AppText.recovery_email_sent)
                }
            }
        }
    }
}