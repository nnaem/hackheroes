package com.hackheroes.newoldtown.screens.suggestion_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.firestore.Query
import com.hackheroes.newoldtown.common.composable.ActionToolbar
import com.hackheroes.newoldtown.common.ext.toolbarActions
import com.hackheroes.newoldtown.component.ListItems
import com.hackheroes.newoldtown.model.Suggestion
import com.hackheroes.newoldtown.screens.edit_suggestion.AddSuggestionViewModel
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.hackheroes.newoldtown.R
import com.hackheroes.newoldtown.R.drawable as AppIcon
import com.hackheroes.newoldtown.R.string as AppText

@ExperimentalMaterialApi
@Composable
fun SuggestionListScreen(
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddSuggestionViewModel = hiltViewModel()
){

    SuggestionsListContent(
        modifier = modifier,
        endAction = { viewModel.onExit(popUpScreen) },
    )
}

@ExperimentalMaterialApi
@Composable
fun SuggestionsListContent(
    endAction: () -> Unit,
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
            modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ActionToolbar(
                title = AppText.suggestion_list,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_close,
                endAction = { endAction() })
            Column(
                modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ListItems(suggestionsList)
            }
        }
    }
}