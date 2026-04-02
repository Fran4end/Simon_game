package it.unipd.francesco_dotoli.simon_game.view

sealed class Routes(val route: String) {
    object Screen1 : Routes("screen1")
    object Screen2 : Routes("screen2")
}
