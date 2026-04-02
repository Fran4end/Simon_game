package it.unipd.francesco_dotoli.simon_game.view.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import it.unipd.francesco_dotoli.simon_game.colorsList

@Composable
fun Colored_button( buttonColor : Color, onclick : () -> Unit){
    Button(
        onClick = onclick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        modifier = Modifier.aspectRatio(4f/3f),
    ){}
}

fun getLetterFromColor(color : Color) : String{
  return when(color){
      colorsList[0] -> "R"
      colorsList[1] -> "G"
      colorsList[2] -> "B"
      colorsList[3] -> "M"
      colorsList[4] -> "Y"
      colorsList[5] -> "C"
      else -> "N/A"
    }
}