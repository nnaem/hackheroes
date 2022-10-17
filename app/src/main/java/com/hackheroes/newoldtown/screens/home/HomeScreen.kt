package com.hackheroes.newoldtown.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hackheroes.newoldtown.common.composable.ActionToolbar
import com.hackheroes.newoldtown.common.ext.smallSpacer
import com.hackheroes.newoldtown.common.ext.toolbarActions
import com.hackheroes.newoldtown.R.drawable as AppIcon
import com.hackheroes.newoldtown.R.string as AppText

data class MarkerData(
    val posforall: LatLng,
    val title: String?,
    val snippet: String?
)

@Composable
@ExperimentalMaterialApi
fun HomeScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val suggestions = viewModel.allSuggestions
    val center = LatLng(52.055, 20.44)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(center, 16f)
    }

    var db = Firebase.firestore

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(16.dp),
                text = { Text(text = "Dodaj sugestię") },
                icon ={ Icon(Icons.Filled.Add,"")}
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            ActionToolbar(
                title = AppText.suggestion_map,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_hamburger,
                endAction = { viewModel.onSettingsClick(openScreen) }
            )

            Spacer(modifier = Modifier.smallSpacer())

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    val markers = remember { mutableStateListOf<MarkerData>() }
                    LaunchedEffect(Unit) {
                        db.collection("suggestions")
                            .get()
                            .addOnSuccessListener { result ->
                                for (document in result) {
                                    markers.add(
                                        MarkerData(
                                            LatLng(
                                                document.data["latitude"].toString().toDouble(),
                                                document.data["longitude"].toString().toDouble()
                                            ),
                                            document.data["title"].toString(),
                                            document.data["description"].toString()
                                        )
                                    )
                                }
                            }
                    }
                    for (marker in markers) {
                        Marker(
                            state = MarkerState(marker.posforall),
                            title = marker.title,
                            snippet = marker.snippet
                        )
                    }
                }
        }
    }

    DisposableEffect(viewModel) {
        viewModel.addListener()
        viewModel.loadSuggestionOptions()
        onDispose { viewModel.removeListener() }
    }
}