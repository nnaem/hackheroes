package com.hackheroes.newoldtown.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackheroes.newoldtown.common.composable.DropdownContextMenu
import com.hackheroes.newoldtown.common.ext.contextMenu
import com.hackheroes.newoldtown.model.Suggestion

@Composable
@ExperimentalMaterialApi
fun SuggestionItem(
    suggestion: Suggestion,
    options: List<String>,
    onActionClick: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = suggestion.title, style = MaterialTheme.typography.subtitle2)
            }

            DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
        }
    }
}