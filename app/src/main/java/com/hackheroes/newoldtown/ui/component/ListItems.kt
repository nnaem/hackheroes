package com.hackheroes.newoldtown.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hackheroes.newoldtown.Suggestion

@Composable
fun ListItems(suggestions: List<Suggestion>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    )
    {
        items(suggestions) {
            ListItem(suggestion = it)
        }
    }
}

/*@Composable
@Preview
fun ListItemsPreview() {
    ListItems(
        suggestions = listOf(
            Suggestion(
                "Stacja do ładowania rowerów elektrycznych",
                "Lubię jeździć na rowerze, a żeby to robić jeszcze szybciej jeżdżę na rowerze elektrycznym. W Żyrardowie brakuje stacji do ładowania takich rowerów. Chcę taką stację :D",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "Nie ma wody pitnej",
                "W całym mieście brak wody do picia!!\n",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "A co ze zwierzętami!",
                "Trzeba stworzyć plac zabaw dla psów, żeby miały gdzie się bawić w berka gryzionego. Pilne!",
                "namely1@gmail.com", 0.0, 0.0
            ),
            Suggestion(
                "Stacja do ładowania rowerów elektrycznych",
                "Lubię jeździć na rowerze, a żeby to robić jeszcze szybciej jeżdżę na rowerze elektrycznym. W Żyrardowie brakuje stacji do ładowania takich rowerów. Chcę taką stację :D",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "Nie ma wody pitnej",
                "W całym mieście brak wody do picia!!\n",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "A co ze zwierzętami!",
                "Trzeba stworzyć plac zabaw dla psów, żeby miały gdzie się bawić w berka gryzionego. Pilne!",
                "namely1@gmail.com", 0.0, 0.0
            ),
            Suggestion(
                "Stacja do ładowania rowerów elektrycznych",
                "Lubię jeździć na rowerze, a żeby to robić jeszcze szybciej jeżdżę na rowerze elektrycznym. W Żyrardowie brakuje stacji do ładowania takich rowerów. Chcę taką stację :D",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "Nie ma wody pitnej",
                "W całym mieście brak wody do picia!!\n",
                "krzysiekkucharski7@gmail.com",
                0.0,
                0.0
            ),
            Suggestion(
                "A co ze zwierzętami!",
                "Trzeba stworzyć plac zabaw dla psów, żeby miały gdzie się bawić w berka gryzionego. Pilne!",
                "namely1@gmail.com",
                0.0, 0.0
            ),
        )
    )
}*/