package com.hackheroes.newoldtown.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackheroes.newoldtown.R.drawable as AppIcon
import com.hackheroes.newoldtown.R.string as AppText
import com.hackheroes.newoldtown.common.composable.*
import com.hackheroes.newoldtown.common.ext.card
import com.hackheroes.newoldtown.common.ext.spacer
import com.hackheroes.newoldtown.common.ext.toolbarActions

@ExperimentalMaterialApi
@Composable
fun MenuScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MenuModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.menu,
            modifier = Modifier.toolbarActions(),
            endActionIcon = AppIcon.ic_close,
            endAction = { viewModel.onExit(openScreen) }
        )

        Spacer(modifier = Modifier.spacer())
        GoToMapCard { viewModel.onGoToMapClick(openScreen) }
        AddSuggestionCard { viewModel.onAddSuggestionClick(openScreen) }
        SignOutCard { viewModel.onSignOutClick(restartApp) }
        DeleteMyAccountCard { viewModel.onDeleteMyAccountClick(restartApp) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun GoToMapCard(navigateFunc: () -> Unit) {
    RegularCardEditor(AppText.suggestion_map, Icons.Filled.Map, "", Modifier.card()) {
        // navigate to home:
        navigateFunc();
    }
}

@ExperimentalMaterialApi
@Composable
private fun AddSuggestionCard(navigateFunc: () -> Unit) {
    RegularCardEditor(AppText.add_suggestion, Icons.Filled.Add, "", Modifier.card()) {
        // navigate to home:
        navigateFunc();
    }
}

@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(AppText.sign_out, AppIcon.ic_exit, "", Modifier.card()) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    DangerousCardEditor(AppText.delete_my_account, AppIcon.ic_delete_my_account, "", Modifier.card()) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.delete_account_title)) },
            text = { Text(stringResource(AppText.delete_account_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}
