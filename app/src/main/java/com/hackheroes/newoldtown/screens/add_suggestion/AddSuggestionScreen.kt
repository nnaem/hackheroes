package com.hackheroes.newoldtown.screens.add_suggestion


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.hackheroes.newoldtown.common.composable.ActionToolbar
import com.hackheroes.newoldtown.common.composable.BasicField
import com.hackheroes.newoldtown.common.composable.MapPinOverlay
import com.hackheroes.newoldtown.common.composable.MultiLineField
import com.hackheroes.newoldtown.common.ext.descriptionModifier
import com.hackheroes.newoldtown.common.ext.fieldModifier
import com.hackheroes.newoldtown.common.ext.spacer
import com.hackheroes.newoldtown.common.ext.toolbarActions
import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.screens.edit_suggestion.AddSuggestionViewModel
import kotlin.math.round
import com.hackheroes.newoldtown.R.drawable as AppIcon
import com.hackheroes.newoldtown.R.string as AppText

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@ExperimentalMaterialApi
fun AddSuggestionScreen(
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddSuggestionViewModel = hiltViewModel()
) {
    val suggestion by viewModel.suggestion

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.mapCenter, 16f)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.initialize();
    }

    viewModel.onLatChange(cameraPositionState.position.target.latitude.round(6))
    viewModel.onLngChange(cameraPositionState.position.target.longitude.round(6))

    AddSuggestionContent(
        modifier = modifier,
        addAction = { viewModel.onAddClick(popUpScreen, openScreen) },
        endAction = { viewModel.onExit(popUpScreen) },
        suggestion = suggestion,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDescriptionDone = { keyboardController?.hide() },
        cameraPositionState = cameraPositionState
    )
}

@Composable
@ExperimentalMaterialApi
fun AddSuggestionContent(
    modifier: Modifier = Modifier,
    addAction: () -> Unit,
    endAction: () -> Unit,
    suggestion: Suggestion,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDescriptionDone: () -> Unit,
    cameraPositionState: CameraPositionState
) {
    Column(
        modifier = modifier
            .background(color = androidx.compose.ui.graphics.Color.DarkGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.add_suggestion,
            modifier = Modifier.toolbarActions(),
            endActionIcon = AppIcon.ic_close,
            endAction = { endAction() }
        )

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        val descriptionModifier = Modifier.descriptionModifier();

        BasicField(
            AppText.title, suggestion.title, onTitleChange, fieldModifier, keyboardOptions =
            KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        MultiLineField(AppText.description,
            suggestion.description,
            onDescriptionChange,
            descriptionModifier,
            keyboardActions = KeyboardActions(onDone = { onDescriptionDone() })
        )

        Spacer(modifier = Modifier.spacer())

        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        addAction()
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = modifier.padding(16.dp),
                    text = { Text(text = "Zgłoś") },
                    icon = { Icon(Icons.Filled.Check, "") }
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState
            )
            MapPinOverlay()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun AddSuggestionScreenPreview() {
    AddSuggestionContent(
        addAction = {},
        endAction = {},
        suggestion = Suggestion(),
        onTitleChange = {},
        onDescriptionChange = {},
        onDescriptionDone = {},
        cameraPositionState = rememberCameraPositionState()
    )
}