package it.unipd.francesco_dotoli.simon_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import it.unipd.francesco_dotoli.simon_game.ui.theme.Simon_gameTheme
import it.unipd.francesco_dotoli.simon_game.view.pages.Screen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Simon_gameTheme {
                Scaffold(
                    topBar = {},
                    modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Screen1()
                    }
                }
            }
        }
    }
}

