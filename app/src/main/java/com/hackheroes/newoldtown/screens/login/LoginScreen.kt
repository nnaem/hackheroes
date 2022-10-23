package com.hackheroes.newoldtown.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.common.composable.BasicButton
import com.hackheroes.newoldtown.common.composable.EmailField
import com.hackheroes.newoldtown.common.composable.PasswordField
import com.hackheroes.newoldtown.common.ext.basicButton
import com.hackheroes.newoldtown.common.ext.fieldModifier
import com.hackheroes.newoldtown.R.string as AppText

@Composable
fun LoginScreen(
    openScreen: (String) -> Unit,
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        modifier = modifier,
        email = uiState.email,
        password = uiState.password,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = {
            viewModel.onSignInClick(openAndPopUp)
        },
        onSignUpClick = {
            viewModel.onSignUpClick(openScreen)
        },
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
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
            text = stringResource(id = R.string.sign_in),
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        EmailField(email, onEmailChange, Modifier.fieldModifier())

        Spacer(Modifier.height(16.dp))

        PasswordField(password, onPasswordChange, Modifier.fieldModifier())

        Spacer(Modifier.height(16.dp))

        BasicButton(AppText.sign_in,
            Modifier
                .basicButton()
                .height(48.dp), {
            onSignInClick()
        })

        Spacer(Modifier.height(16.dp))

        BasicButton(AppText.create_account,
            Modifier
                .basicButton()
                .height(48.dp), {
            onSignUpClick()
        }, Color(0xFF681531)
        )
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreenContent(
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {},
        onSignUpClick = {}
    )
}
