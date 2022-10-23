package com.hackheroes.newoldtown.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackheroes.newoldtown.common.composable.BasicButton
import com.hackheroes.newoldtown.common.ext.basicButton
import kotlinx.coroutines.delay
import com.hackheroes.newoldtown.R;

private const val SPLASH_TIMEOUT = 1000L

@Composable
fun SplashScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel? = hiltViewModel(),
    isPreview: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPreview) {
            CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
        } else {
            if (viewModel != null) {
                if (viewModel.showError.value) {
                    Text(text = stringResource(R.string.generic_error))

                    BasicButton(R.string.try_again, Modifier.basicButton(), {
                        viewModel.onAppStart(openAndPopUp)
                    })
                } else {
                    CircularProgressIndicator(color = MaterialTheme.colors.onBackground)
                }
            }

            LaunchedEffect(true) {
                delay(SPLASH_TIMEOUT)
                viewModel?.onAppStart(openAndPopUp)
            }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen(
        openAndPopUp = { _, _ -> },
        modifier = Modifier,
        viewModel = null,
        isPreview = true)
}
