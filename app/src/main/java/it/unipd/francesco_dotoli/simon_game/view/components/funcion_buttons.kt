package it.unipd.francesco_dotoli.simon_game.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.defaultPadding

@Composable
fun FunctionButtons(onDelete : () -> Unit, onEndGame : () -> Unit){
    val deleteString = stringResource(R.string.delete)
    val endGameString = stringResource(R.string.end_game)

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onDelete,
            enabled = true,
        ) {
            Text(
                deleteString,
                fontSize = TextUnit(24f, TextUnitType.Sp),
                modifier = Modifier.padding(defaultPadding/2)
            )
        }

        Button(
            onClick = { },
            enabled = true,
        ) {
            Text(
                endGameString,
                fontSize = TextUnit(24f, TextUnitType.Sp),
                modifier = Modifier.padding(defaultPadding/2)
            )
        }
    }
}
