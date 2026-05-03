package it.unipd.francesco_dotoli.simon_game.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.unipd.francesco_dotoli.simon_game.colorsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GameController : ViewModel() {
    var numButton by mutableStateOf(1)
    val sequence = mutableStateListOf<Color>()
    var highlightedColor by mutableStateOf<Color?>(null)
    var isBotPlaying by mutableStateOf(false)
    var isOnPause by mutableStateOf(false)
    var isGameStart by mutableStateOf(false)

    fun playSequence() {
        if (isBotPlaying) return
        isBotPlaying = true
        viewModelScope.launch {
            sequence.add(
                colorsList[Random.nextInt(0, colorsList.size)]
            )
            numButton++

            for (color in sequence) {
                if (isOnPause) {
                    snapshotFlow { isOnPause }.first { !it }
                }
                highlightedColor = color
                withContext(Dispatchers.IO) {
                    playSound(color)
                }
                highlightedColor = null
                delay(200)
            }
            isBotPlaying = false
        }
    }

    fun togglePause() {
        isOnPause = !isOnPause
    }

    fun reset() {
        sequence.clear()
        numButton = 1
        isBotPlaying = false
        highlightedColor = null
    }
}
