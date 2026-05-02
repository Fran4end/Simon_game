package it.unipd.francesco_dotoli.simon_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unipd.francesco_dotoli.simon_game.ui.theme.SimonGameTheme
import it.unipd.francesco_dotoli.simon_game.view.Routes
import it.unipd.francesco_dotoli.simon_game.view.pages.Screen1
import it.unipd.francesco_dotoli.simon_game.view.pages.Screen2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimonGameTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Routes.Screen1.route,
                        enterTransition = { fadeIn(animationSpec = tween(100)) }, // speed up animation time
                        exitTransition = { fadeOut(animationSpec = tween(100)) }, // speed up animation time
                    ) {
                        composable(Routes.Screen1.route) { Screen1(navController) }
                        composable(Routes.Screen2.route) { Screen2() }
                    }
                }
            }
        }
    }
}
