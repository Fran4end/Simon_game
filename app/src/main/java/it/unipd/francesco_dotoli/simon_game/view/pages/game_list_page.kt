package it.unipd.francesco_dotoli.simon_game.view.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.controller.RoomViewModel
import it.unipd.francesco_dotoli.simon_game.view.Routes
import it.unipd.francesco_dotoli.simon_game.view.components.SavedGameItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListPage(navController: NavController, roomViewModel: RoomViewModel) {
    val savedGames by roomViewModel.savedGames.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        roomViewModel.fetchAllGame()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //https://developer.android.com/develop/ui/compose/components/app-bars
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.game_list_title), fontSize = 32.sp) },
                actions = {
                    IconButton(
                        onClick = { roomViewModel.deleteAll() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.delete_all_entries)
                        )
                    }
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.GamePage.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = stringResource(R.string.start_new_game)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(savedGames) { game ->
                SavedGameItem(game)
            }
        }
    }
}
