package com.hackheroes.newoldtown.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackheroes.newoldtown.Suggestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AddIdeaScreen() {
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val descriptionState = remember { mutableStateOf(TextFieldValue()) }

    var suggestionsList by remember {
        mutableStateOf(listOf<Suggestion>())
    }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .background(color = Color.White, shape = RoundedCornerShape(4.dp) )
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

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(200.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Dodaj")
        }

//        SuggestionsList()
    }
}