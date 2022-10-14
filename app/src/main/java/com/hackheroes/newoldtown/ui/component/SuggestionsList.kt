package com.hackheroes.newoldtown.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.Query
import com.hackheroes.newoldtown.Suggestion
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects

@Composable
fun SuggestionsList() {

    var suggestionsList by remember {
        mutableStateOf(listOf<Suggestion>())
    }

    JetFirestore(
        path = { collection("city_ideas_android") },
        queryOnCollection = { orderBy("author", Query.Direction.DESCENDING) },
        onSingleTimeCollectionFetch = { values, exception ->
            suggestionsList = suggestionsList + values.getListOfObjects()
        }
    ) { pagination ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ListItems(suggestionsList)
            }
        }
    }
}