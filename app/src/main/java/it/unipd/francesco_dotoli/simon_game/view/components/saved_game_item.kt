package it.unipd.francesco_dotoli.simon_game.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import it.unipd.francesco_dotoli.simon_game.defaultPadding
import it.unipd.francesco_dotoli.simon_game.model.GameModel

@Composable
fun SavedGameItem(game: GameModel, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding)
                .drawBehind {
                    drawLine(
                        color = Color.LightGray,
                        Offset(0f, size.height + 20),
                        Offset(size.width, size.height),
                        4f,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.border(width = 2.dp, color = Color.Gray, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = game.buttonsClicked.toString(),
                    modifier = Modifier.padding(horizontal = defaultPadding),
                )
            }

            VerticalDivider(thickness = 10.dp)

            Text(
                buildAnnotatedString {
                    append(game.correctSequence.trim())

                    if (!game.missSequence.isEmpty() && !game.correctSequence.isEmpty())
                        append(", ")
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append(game.missSequence)
                    }
                },
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}
