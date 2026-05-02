package it.unipd.francesco_dotoli.simon_game.view

/**
 * Sealed class for improve performance on compile:
 * [Sealed Classes](https://kotlinlang.org/docs/sealed-classes.html)
 */
sealed class Routes(val route: String) {
    data object Screen1 : Routes("screen1")
    data object Screen2 : Routes("screen2")
}
