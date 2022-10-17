package com.hackheroes.newoldtown.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.common.composable.BasicButton
import com.hackheroes.newoldtown.common.composable.BasicTextButton
import com.hackheroes.newoldtown.common.composable.BasicToolbar
import com.hackheroes.newoldtown.common.composable.EmailField
import com.hackheroes.newoldtown.common.composable.PasswordField
import com.hackheroes.newoldtown.common.composable.RegularCardEditor
import com.hackheroes.newoldtown.common.ext.basicButton
import com.hackheroes.newoldtown.common.ext.card
import com.hackheroes.newoldtown.common.ext.fieldModifier
import com.hackheroes.newoldtown.common.ext.textButton
import com.hackheroes.newoldtown.R.string as AppText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    BasicToolbar(AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())

        BasicButton(AppText.sign_in, Modifier.basicButton().height(48.dp)) {
            viewModel.onSignInClick(openAndPopUp)
        }

        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
            viewModel.onForgotPasswordClick()
        }

        RegularCardEditor(AppText.create_account, R.drawable.ic_create_account, "", Modifier.card()) {
            viewModel.onSignUpClick(openScreen)
        }
    }
}