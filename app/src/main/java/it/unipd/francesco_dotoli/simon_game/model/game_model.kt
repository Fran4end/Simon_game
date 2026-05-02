package it.unipd.francesco_dotoli.simon_game.model

data class GameModel(
    val buttonsClicked: Int = 0,
    val correctSequence: String = "",
    val missSequence: String = "",
)

val finishedGames: MutableList<GameModel> = mutableListOf()
