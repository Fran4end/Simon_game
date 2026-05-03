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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.colorsList
import it.unipd.francesco_dotoli.simon_game.controller.GameController
import it.unipd.francesco_dotoli.simon_game.controller.RoomViewModel
import it.unipd.francesco_dotoli.simon_game.controller.playSound
import it.unipd.francesco_dotoli.simon_game.defaultPadding
import it.unipd.francesco_dotoli.simon_game.model.GameModel
import it.unipd.francesco_dotoli.simon_game.view.components.ColoredButton
import it.unipd.francesco_dotoli.simon_game.view.components.FunctionButtons
import it.unipd.francesco_dotoli.simon_game.view.components.getLetterFromColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePage(navController: NavController, roomViewModel: RoomViewModel) {
    val sequencePlaceholder = stringResource(R.string.sequence)
    val gridModifier = Modifier
        .fillMaxSize()
        .padding(defaultPadding)

    val gameController: GameController = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var text by rememberSaveable { mutableStateOf(sequencePlaceholder) }

    val coloredButtonOnClick = fun(color: Color) {
        if (gameController.isBotPlaying || !gameController.isGameStart) return
        gameController.highlightedColor = color
        coroutineScope.launch(Dispatchers.IO) {
            playSound(color)
            delay(300)
            gameController.highlightedColor = null
        }
        var newText = getLetterFromColor(color)
        if (text == sequencePlaceholder) text = ""
        else newText = ", $newText"
        text += newText
    }

    val onEndGame = {
        var size = text.split(',').size
        if (text == sequencePlaceholder) size = 0
        roomViewModel.insert(
            GameModel(
                buttonsClicked = size,
                correctSequence = if (text == sequencePlaceholder) "" else text,
                uid = 0,
            )
        )
        navController.popBackStack()
        text = sequencePlaceholder
        gameController.reset()
    }

    val onStart = {
        gameController.playSequence()
        gameController.isGameStart = true
    }

    val onPause = {
        gameController.isOnPause = !gameController.isOnPause
    }

    // scroll text
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
                LandscapeGrid(coloredButtonOnClick, gameController.highlightedColor)
                FunctionsArea(
                    onPause = onPause,
                    onEndGame = onEndGame,
                    scrollState = scrollState,
                    text = text,
                    onStart = onStart,
                    isOnPause = gameController.isOnPause,
                    isGameStart = gameController.isGameStart,
                )
            }
        } else {
            Column(
                modifier = gridModifier.padding(innerPadding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PortraitGrid(coloredButtonOnClick, gameController.highlightedColor)
                FunctionsArea(
                    onPause = onPause,
                    onEndGame = onEndGame,
                    scrollState = scrollState,
                    text = text,
                    onStart = onStart,
                    isOnPause = gameController.isOnPause,
                    isGameStart = gameController.isGameStart,
                )
            }
        }
    }
}

@Composable
private fun PortraitGrid(onClick: (color: Color) -> Unit, highlightedColor: Color?) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        verticalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        userScrollEnabled = false,
    ) {
        items(colorsList) { color ->
            ColoredButton(
                buttonColor = color,
                isHighlighted = color == highlightedColor
            ) {
                onClick(color)
            }
        }
    }
}

@Composable
private fun LandscapeGrid(onClick: (color: Color) -> Unit, highlightedColor: Color?) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        verticalArrangement = Arrangement.spacedBy(defaultPadding / 2),
        userScrollEnabled = false,
    ) {
        items(colorsList) { color ->
            ColoredButton(
                buttonColor = color,
                isHighlighted = color == highlightedColor
            ) {
                onClick(color)
            }
        }
    }
}

//Text box and the 2 buttons under or right of grid
@Composable
private fun FunctionsArea(
    onEndGame: () -> Unit,
    onPause: () -> Unit,
    onStart: () -> Unit,
    scrollState: ScrollState,
    text: String,
    isGameStart: Boolean = false,
    isOnPause: Boolean = false,
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
            onPause = onPause,
            onEndGame = onEndGame,
            onStart = onStart,
            isOnPause = isOnPause,
            isGameStart = isGameStart,
        )
    }
}
