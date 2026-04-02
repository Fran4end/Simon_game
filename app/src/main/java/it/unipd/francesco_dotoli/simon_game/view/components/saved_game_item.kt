package it.unipd.francesco_dotoli.simon_game.view.components

import android.graphics.drawable.ShapeDrawable
import android.text.style.UnderlineSpan
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import it.unipd.francesco_dotoli.simon_game.defaultPadding
import it.unipd.francesco_dotoli.simon_game.model.GameModel

@Composable
fun SavedGameItem(game : GameModel){
    Row(
        modifier = Modifier.fillMaxSize().padding(defaultPadding)
            .drawBehind{
                drawLine(
                    color = Color.LightGray,
                    Offset(0f, size.height + 10),
                    Offset(size.width, size.height),
                    4f
                )},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(defaultPadding),
    ) {
        Box(
            modifier = Modifier.border(width = 2.dp, color = Color.Gray,  shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = game.buttons_clicked.toString(),
                modifier = Modifier.padding(horizontal = defaultPadding/2)
            )
        }

        Text(
            text = game.seguence,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

    }
}