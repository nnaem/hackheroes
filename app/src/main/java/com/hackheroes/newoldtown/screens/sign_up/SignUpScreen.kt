package com.hackheroes.newoldtown.screens.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.common.composable.*
import com.hackheroes.newoldtown.common.ext.basicButton
import com.hackheroes.newoldtown.common.ext.fieldModifier
import com.hackheroes.newoldtown.R.string as AppText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()
    val keyboardController = LocalSoftwareKeyboardController.current

    SignUpContent(
        modifier = modifier,
        fieldModifier,
        email = uiState.email,
        password = uiState.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignUpClick = {
            viewModel.onSignUpClick(openAndPopUp)
        },
        onBackClick = {
            viewModel.onBackClick(openAndPopUp)
        },
        onPasswordDoneClick = {
            keyboardController?.hide()
        }
    )
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onBackClick: () -> Unit,
    onPasswordDoneClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.zyrardow),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )

        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.sign_up),
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        EmailField(email, onEmailChange, fieldModifier)

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(password, onPasswordChange, fieldModifier, onPasswordDoneClick)

        Spacer(modifier = Modifier.height(16.dp))

        BasicButton(AppText.create_account,
            Modifier
                .basicButton()
                .height(48.dp), {
            onSignUpClick()
        },
            color = Color(0xFF5E112B),
        )

        BasicButton(R.string.back,
            Modifier
                .basicButton()
                .height(48.dp), {
            onBackClick()
        }, color = Color(0xFF5F647E)
        )
    }
}

@Composable
@Preview
fun SignUpScreenPreview() {
    SignUpContent(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignUpClick = {},
        onBackClick = {}
    )
}
