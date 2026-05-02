package it.unipd.francesco_dotoli.simon_game.view.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import it.unipd.francesco_dotoli.simon_game.model.finishedGames
import it.unipd.francesco_dotoli.simon_game.view.components.SavedGameItem

@Composable
fun Screen2() {
    LazyColumn {
        items(finishedGames) { game ->
            SavedGameItem(game)
        }
    }
}
