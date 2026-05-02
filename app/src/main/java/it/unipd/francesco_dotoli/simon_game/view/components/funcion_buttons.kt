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
import androidx.compose.ui.unit.sp
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.defaultPadding

@Composable
fun FunctionButtons(onDelete: () -> Unit, onEndGame: () -> Unit) {
    val deleteString = stringResource(R.string.delete)
    val endGameString = stringResource(R.string.end_game)

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(
            onClick = onDelete,
        ) {
            Text(
                deleteString,
                fontSize = 24.sp,
                modifier = Modifier.padding(defaultPadding / 2),
            )
        }

        Button(
            onClick = onEndGame,
        ) {
            Text(
                endGameString,
                fontSize = 24.sp,
                modifier = Modifier.padding(defaultPadding / 2),
            )
        }
    }
}
