package it.unipd.francesco_dotoli.simon_game.view.pages

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.colorsList
import it.unipd.francesco_dotoli.simon_game.controller.RoomViewModel
import it.unipd.francesco_dotoli.simon_game.defaultPadding
import it.unipd.francesco_dotoli.simon_game.model.GameModel
import it.unipd.francesco_dotoli.simon_game.view.components.ColoredButton
import it.unipd.francesco_dotoli.simon_game.view.components.FunctionButtons
import it.unipd.francesco_dotoli.simon_game.view.components.getLetterFromColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePage(navController: NavController, roomViewModel: RoomViewModel) {
    val sequence = stringResource(R.string.sequence)
    val gridModifier = Modifier
        .fillMaxSize()
        .padding(defaultPadding)
    var text by rememberSaveable { mutableStateOf(sequence) }

    val coloredButtonOnClick = fun(color: Color) {
        var newText = getLetterFromColor(color)
        if (text == sequence) text = ""
        else newText = ", $newText"
        text += newText
    }
    val onDelete = { text = sequence }
    val onEndGame = {
        var size = text.split(',').size
        if (text == sequence) size = 0
        roomViewModel.insert(
            GameModel(
                buttonsClicked = size,
                correctSequence = if (text == sequence) "" else text,
                uid = 0,
            )
        )
        navController.popBackStack()
        text = sequence
    }


    //preso spunto da: https://medium.com/@paritasampa95/auto-scrolling-text-in-jetpack-compose-smooth-horizontal-marquee-for-android-60b20f1e8198
    val scrollState = rememberScrollState()
    LaunchedEffect(text) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    val orientation = LocalConfiguration.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.game_title), fontSize = 32.sp) },
            )
        }
    ) { innerPadding ->
        if (orientation.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Row(
                modifier = gridModifier
                    .padding(innerPadding)
                    .padding(start = defaultPadding * 2),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                LandscapeGrid(coloredButtonOnClick)
                FunctionsArea(
                    onDelete = onDelete,
                    onEndGame = onEndGame,
                    scrollState = scrollState,
                    text = text,
                )
            }
        } else {
            Column(
                modifier = gridModifier.padding(innerPadding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PortraitGrid(coloredButtonOnClick)
                FunctionsArea(
                    onDelete = onDelete,
                    onEndGame = onEndGame,
                    scrollState = scrollState,
                    text = text,
                )
            }
        }
    }
}

@Composable
private fun PortraitGrid(onClick: (color: Color) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        verticalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        userScrollEnabled = false,
    ) {
        items(colorsList) { color ->
            ColoredButton(buttonColor = color) {
                onClick(color)
            }
        }
    }
}

@Composable
private fun LandscapeGrid(onClick: (color: Color) -> Unit) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        verticalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        userScrollEnabled = false,
    ) {
        items(colorsList) { color ->
            ColoredButton(buttonColor = color) {
                onClick(color)
            }
        }
    }
}

//Text box and the 2 buttons under or right of grid
@Composable
private fun FunctionsArea(
    onDelete: () -> Unit,
    onEndGame: () -> Unit,
    scrollState: ScrollState,
    text: String,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = text,
            modifier = Modifier
                .padding(defaultPadding)
                .height(75.dp)
                .verticalScroll(scrollState),
        )

        FunctionButtons(
            onDelete = onDelete,
            onEndGame = onEndGame,
        )
    }
}
