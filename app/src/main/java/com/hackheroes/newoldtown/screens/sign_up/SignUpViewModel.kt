package com.hackheroes.newoldtown.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hackheroes.newoldtown.LOGIN_SCREEN
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.SETTINGS_SCREEN
import com.hackheroes.newoldtown.SIGN_UP_SCREEN
import com.hackheroes.newoldtown.R.string as AppText
import com.hackheroes.newoldtown.common.ext.isValidEmail
import com.hackheroes.newoldtown.common.ext.isValidPassword
import com.hackheroes.newoldtown.common.snackbar.SnackbarManager
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : NewOldTownViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onBackClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(LOGIN_SCREEN, SETTINGS_SCREEN)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        viewModelScope.launch(showErrorExceptionHandler) {
            accountService.createAccount(email, password, onSuccess = {
                openAndPopUp(LOGIN_SCREEN, SIGN_UP_SCREEN)
                SnackbarManager.showMessage(R.string.account_created);
            }, onFail = {
                SnackbarManager.showMessage(R.string.sign_up_error);
            })
        }
    }
}