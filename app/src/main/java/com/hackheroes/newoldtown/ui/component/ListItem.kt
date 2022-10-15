package com.hackheroes.newoldtown.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackheroes.newoldtown.Suggestion

@Composable
fun ListItem(suggestion: Suggestion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(
                text = suggestion.title,
                fontSize = 20.sp
            )
            Text(
                text = suggestion.description,
                fontSize = 14.sp
            )
            /*Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = suggestion.author,
                    fontSize = 14.sp,
                )*/
            }
        }
    }
//}

@Composable
@Preview
fun ListItemPreview() {
    ListItem(suggestion = Suggestion(
        "Stacja do ładowania rowerów elektrycznych",
        "Lubię jeździć na rowerze, a żeby to robić jeszcze szybciej jeżdżę na rowerze elektrycznym. W Żyrardowie brakuje stacji do ładowania takich rowerów. Chcę taką stację :D",
        //"krzysiekkucharski7@gmail.com",
        0.0,
        0.0)
    );
}