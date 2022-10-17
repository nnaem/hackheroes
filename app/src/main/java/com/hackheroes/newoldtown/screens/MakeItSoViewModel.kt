package com.hackheroes.newoldtown.screens

import androidx.lifecycle.ViewModel
import com.hackheroes.newoldtown.common.snackbar.SnackbarManager
import com.hackheroes.newoldtown.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler

open class NewOldTownViewModel() : ViewModel() {
    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
    }
}