package com.hackheroes.newoldtown.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

data class MarkerData(
    val posforall: LatLng,
    val title: String?,
    val snippet: String?
)

@Composable
@Preview
fun CityMapScreen() {

    val singapore = LatLng(52.055, 20.44)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 16f)
    }
    var db = Firebase.firestore
    Log.println(Log.ERROR, "debug", "TESTING")

    ElevatedCard {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            val markers = remember { mutableStateListOf<MarkerData>() }
            LaunchedEffect(Unit) {
                val suggestions = db.collection("city_ideas_android")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            markers.add(
                                MarkerData(
                                    posforall = LatLng(java.lang.Double.parseDouble(document.data["lat"].toString()), java.lang.Double.parseDouble(document.data["lng"].toString())),
                                    title = document.data["title"] as String?,
                                    snippet = document.data["description"] as String?
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
            Marker(
                state = MarkerState(position = singapore),
                title = "Żyrardów",
                snippet = "Centrum Żyrardowa"
            )
        }
    }
}