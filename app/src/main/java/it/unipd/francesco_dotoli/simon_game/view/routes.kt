package it.unipd.francesco_dotoli.simon_game.view

/**
 * Sealed class for improve performance on compile:
 * [Sealed Classes](https://kotlinlang.org/docs/sealed-classes.html)
 */
sealed class Routes(val route: String) {
    data object GamePage : Routes("GamePage")
    data object GameListPage : Routes("GameListPage")
}
