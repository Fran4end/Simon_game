package it.unipd.francesco_dotoli.simon_game.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.defaultPadding

@Composable
fun FunctionButtons(
    onStart: () -> Unit,
    onPause: () -> Unit,
    onEndGame: () -> Unit,
    isGameStart: Boolean,
    isOnPause: Boolean,
) {
    val endGameString = stringResource(R.string.end_game)
    val startString = stringResource(R.string.start)
    val pauseString = stringResource(R.string.pause)
    val resumeString = stringResource(R.string.resume)

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = defaultPadding / 2),
    ) {
        Button(
            onClick = onStart,
            enabled = !isGameStart,
        ) {
            Text(
                startString,
            )
        }

        Button(
            onClick = onPause,
            enabled = isGameStart,
        ) {
            Text(
                if (isOnPause) resumeString else pauseString,
            )
        }

        Button(
            onClick = onEndGame,
        ) {
            Text(
                endGameString,
            )
        }
    }
}
