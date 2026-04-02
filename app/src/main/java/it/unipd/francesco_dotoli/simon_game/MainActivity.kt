package it.unipd.francesco_dotoli.simon_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.unipd.francesco_dotoli.simon_game.ui.theme.Simon_gameTheme
import it.unipd.francesco_dotoli.simon_game.view.Routes
import it.unipd.francesco_dotoli.simon_game.view.pages.Screen2
import it.unipd.francesco_dotoli.simon_game.view.pages.Screen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Simon_gameTheme {

                val navController = rememberNavController()
                Scaffold(
                    topBar = {},
                    modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Routes.Screen1.route,
                    ) {
                        composable(Routes.Screen1.route) {Screen1(navController)}
                        composable(Routes.Screen2.route) {Screen2(navController)}
                    }
                }
            }
        }
    }
}

