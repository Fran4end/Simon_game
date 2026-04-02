package it.unipd.francesco_dotoli.simon_game.model

data class GameModel(
    val buttons_clicked : Int = 0,
    val seguence : String = ""
){}

val finishedGames : MutableList<GameModel> = mutableListOf<GameModel>()
