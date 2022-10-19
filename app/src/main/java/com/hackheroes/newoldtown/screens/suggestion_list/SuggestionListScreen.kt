package com.hackheroes.newoldtown.screens.suggestion_list

import android.app.DownloadManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.firestore.Query
import com.hackheroes.newoldtown.component.ListItems
import com.hackheroes.newoldtown.model.Suggestion
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects

@ExperimentalMaterialApi
@Composable
fun SuggestionListScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuggestionListViewModel = hiltViewModel()
) {
    var suggestionsList by remember {
        mutableStateOf(listOf<Suggestion>())
    }

    JetFirestore(
        path = { collection("city_ideas_android") },
        queryOnCollection = { orderBy("title", Query.Direction.ASCENDING) },
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