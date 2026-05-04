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
import it.unipd.francesco_dotoli.simon_game.model.GameModel
import it.unipd.francesco_dotoli.simon_game.view.components.getLetterFromColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GameController() : ViewModel() {
    val sequence = mutableStateListOf<Color>()
    var numButton by mutableStateOf(1)
    var highlightedColor by mutableStateOf<Color?>(null)
    var isBotPlaying by mutableStateOf(false)
    var isOnPause by mutableStateOf(false)
    var isGameStart by mutableStateOf(false)
    var text by mutableStateOf("")
    var correctSequence by mutableStateOf("")
    var userSequence = mutableStateListOf<Color>()

    init {
        viewModelScope.launch {
            // Watch for unpause to repeat sequence if it's the user's turn
            snapshotFlow { isOnPause }.collect { paused ->
                if (!paused && isGameStart && !isBotPlaying && sequence.isNotEmpty()) {
                    userSequence.clear()
                    // Reset text to empty so it can be refilled or show placeholder
                    text = ""
                    playExistingSequence()
                }
            }
        }
    }

    fun onClick(color: Color, onGameOver: (GameModel) -> Unit) {
        if (isBotPlaying || !isGameStart || isOnPause) return

        viewModelScope.launch {
            userSequence.add(color)
            val currentIndex = userSequence.size - 1

            if (color == sequence[currentIndex]) {

                var newText = getLetterFromColor(color)
                if (text.isNotEmpty()) newText = ", $newText"
                text += newText
                correctSequence = text

                highlightedColor = color
                withContext(Dispatchers.IO) {
                    playSound(color)
                    delay(150)
                    highlightedColor = null
                }

                if (userSequence.size == sequence.size) {
                    userSequence.clear()
                    delay(1000)
                    playSequence()
                    text = ""
                }
            } else {
                lose(onGameOver)
                userSequence.clear()
                correctSequence = text
            }
        }
    }


    fun playSequence() {
        if (isBotPlaying) return
        sequence.add(
            colorsList[Random.nextInt(0, colorsList.size)]
        )
        numButton++
        playExistingSequence()
    }

    private fun playExistingSequence() {
        if (isBotPlaying) return
        isBotPlaying = true
        viewModelScope.launch {
            var i = 0
            while (i < sequence.size) {
                if (isOnPause) {
                    highlightedColor = null
                    snapshotFlow { isOnPause }.first { !it }
                    delay(500) // Small delay after unpause
                    i = 0 // Restart from beginning
                }
                val color = sequence[i]
                highlightedColor = color
                withContext(Dispatchers.IO) {
                    playSound(color)
                }
                highlightedColor = null
                delay(200)
                i++
            }
            isBotPlaying = false
        }
    }

    fun endGame(
        callback: (GameModel) -> Unit,
    ) {
        val size = if (correctSequence.isEmpty()) 0 else correctSequence.split(',').size
        var missSequence: String = ""

        for (i in size..<sequence.size) {
            var newText = getLetterFromColor(sequence[i])
            if (missSequence != "") newText = ", $newText"
            missSequence += newText
        }

        callback(
            GameModel(
                uid = 0,
                buttonsClicked = size,
                correctSequence = correctSequence,
                missSequence = missSequence,
            )
        )

        reset()
    }

    fun lose(
        callback: (GameModel) -> Unit
    ) {
        if (isBotPlaying) return
        isBotPlaying = true
        highlightedColor = null
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                playSound(Color(0xfff02020))
            }

            delay(200)
            for (color in colorsList) {
                highlightedColor = color
                delay(300)
                highlightedColor = null
                delay(10)
            }

            endGame(
                callback = callback,
            )
        }
    }

    fun reset() {
        sequence.clear()
        correctSequence = ""
        text = ""
        numButton = 1
        isBotPlaying = false
        isGameStart = false
        highlightedColor = null
    }
}
