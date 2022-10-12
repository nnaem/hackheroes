package com.hackheroes.newoldtown.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hackheroes.newoldtown.di.state
import com.hackheroes.newoldtown.ui.viewmodel.PathsVM
import org.koin.androidx.compose.viewModel
import ovh.plrapps.mapcompose.ui.MapUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: PathsVM = viewModel()) {
    Scaffold(
        topBar = {
            Row {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        Column {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            }
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ElevatedCard {
                Text(
                    text = "Testing",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxSize(),
                    lineHeight = 35.sp,
                )
                MapUI(state = viewModel.state)
            }
        }
    }
}