package it.unipd.francesco_dotoli.simon_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unipd.francesco_dotoli.simon_game.controller.RoomRepository
import it.unipd.francesco_dotoli.simon_game.controller.RoomViewModel
import it.unipd.francesco_dotoli.simon_game.model.AppDatabase
import it.unipd.francesco_dotoli.simon_game.ui.theme.SimonGameTheme
import it.unipd.francesco_dotoli.simon_game.view.Routes
import it.unipd.francesco_dotoli.simon_game.view.pages.GameListPage
import it.unipd.francesco_dotoli.simon_game.view.pages.GamePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roomDao = AppDatabase.getDatabase(applicationContext).roomDao()
        val roomRepository = RoomRepository(roomDao)
        var roomViewModel = RoomViewModel(roomRepository)

        enableEdgeToEdge()
        setContent {
            SimonGameTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.GameListPage.route,
                    enterTransition = { fadeIn(animationSpec = tween(100)) }, // speed up animation time
                    exitTransition = { fadeOut(animationSpec = tween(100)) }, // speed up animation time
                ) {
                    composable(Routes.GameListPage.route) {
                        GameListPage(
                            navController,
                            roomViewModel,
                        )
                    }
                    composable(Routes.GamePage.route) {
                        GamePage(
                            navController,
                            roomViewModel,
                        )
                    }
                }
            }
        }
    }
}
