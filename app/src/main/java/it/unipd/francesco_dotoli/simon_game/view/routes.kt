package it.unipd.francesco_dotoli.simon_game.view

//sealed because improve performance on compile: https://kotlinlang.org/docs/sealed-classes.html:
sealed class Routes(val route: String) {
    object Screen1 : Routes("screen1")
    object Screen2 : Routes("screen2")
}
