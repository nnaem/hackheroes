package com.hackheroes.newoldtown.screens.suggestion_list

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.hackheroes.newoldtown.ADD_SUGGESTION_SCREEN
import com.hackheroes.newoldtown.HOME_SCREEN
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.SETTINGS_SCREEN
import com.hackheroes.newoldtown.common.ext.isValidDescription
import com.hackheroes.newoldtown.common.ext.isValidTitle
import com.hackheroes.newoldtown.common.snackbar.SnackbarManager
import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.model.service.StorageService
import com.hackheroes.newoldtown.screens.NewOldTownViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionListViewModel @Inject constructor() : NewOldTownViewModel() {}
