package it.unipd.francesco_dotoli.simon_game.view.pages

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Screen2(onClick : () -> Unit){
    Button(
        onClick = onClick,
        enabled = true,
    ){}
}