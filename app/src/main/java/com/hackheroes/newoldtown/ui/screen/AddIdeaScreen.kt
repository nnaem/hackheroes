package com.hackheroes.newoldtown.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.hackheroes.newoldtown.Suggestion

private fun mToast(context: Context, message: String, length: Int) {
    Toast.makeText(context, message, length)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AddIdeaScreen() {
    val context = LocalContext.current
    var db:FirebaseFirestore = FirebaseFirestore.getInstance()
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }

    val singapore = LatLng(52.055, 20.44)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 16f)
    }

    var suggestionsList by remember {
        mutableStateOf(listOf<Suggestion>())
    }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .width(300.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent, //hide the indicator
                disabledIndicatorColor = Color.Transparent, //hide the indicator
                errorIndicatorColor = Color.Transparent, //hide the indicator
                focusedLabelColor = Color.Black, //hide the label
                unfocusedLabelColor = Color.Gray, //hide the label
                disabledLabelColor = Color.Transparent, //hide the label
                errorLabelColor = Color.Red, //hide the label
                textColor = Color.Black,
                cursorColor = Color.Black,
            ),
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text(text = "Tytuł") }
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            modifier = Modifier
                .width(300.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent, //hide the indicator
                disabledIndicatorColor = Color.Transparent, //hide the indicator
                errorIndicatorColor = Color.Transparent, //hide the indicator
                focusedLabelColor = Color.Black, //hide the label
                unfocusedLabelColor = Color.Gray, //hide the label
                disabledLabelColor = Color.Transparent, //hide the label
                errorLabelColor = Color.Red, //hide the label
                textColor = Color.Black,
                cursorColor = Color.Black,
            ),
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            label = { Text(text = "Opis") }
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedCard () {
            GoogleMap(
                modifier = Modifier.size(300.dp).pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            Log.d("debug", it.toString())
                        }
                    )
                },
                cameraPositionState = cameraPositionState
            ) {
                
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                val add = HashMap<String,Any>()

                add["title"] = titleState.value.text
                add["description"] = descriptionState.value.text
                add["lat"] = 69
                add["lng"] = 1

                db.collection("city_ideas_android")
                    .add(add)
                    .addOnSuccessListener {
                        mToast(context, "Failed to add suggestion", Toast.LENGTH_SHORT)
                    }
                    .addOnFailureListener {
                        mToast(context, "Failed to add suggestion", Toast.LENGTH_SHORT)
                    }
            },
            modifier = Modifier
                .width(200.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Dodaj")
        }

//        SuggestionsList()
    }
}