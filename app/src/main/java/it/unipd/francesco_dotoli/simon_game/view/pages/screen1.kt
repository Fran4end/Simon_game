package it.unipd.francesco_dotoli.simon_game.view.pages

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import it.unipd.francesco_dotoli.simon_game.*
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.view.components.colored_button
import it.unipd.francesco_dotoli.simon_game.view.components.getLetterFromColor
import kotlinx.coroutines.delay

@Composable
fun Screen1(){
    val sequence = stringResource(R.string.sequence)
    var text by remember { mutableStateOf(sequence) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(defaultPadding/2),
            verticalArrangement = Arrangement.spacedBy(defaultPadding),
            userScrollEnabled = false,
            modifier = Modifier.padding(horizontal = defaultPadding)
        ) {
          items(colorsList){
              color ->
              colored_button(
                  buttonColor = color,
                  onclick = {
                      var newText = getLetterFromColor(color)
                      if (text.equals(sequence)) text = ""
                      else newText = ", $newText"
                      text += newText
                  }
              )
          }
        }

        //preso spunto da: https://medium.com/@paritasampa95/auto-scrolling-text-in-jetpack-compose-smooth-horizontal-marquee-for-android-60b20f1e8198
        val scrollState = rememberScrollState()
        LaunchedEffect(text) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(defaultPadding)
                .height(75.dp)
                .verticalScroll(scrollState),
        )
    }
}
