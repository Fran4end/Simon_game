package it.unipd.francesco_dotoli.simon_game.view.components

import android.R
import android.widget.Button
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import it.unipd.francesco_dotoli.simon_game.colorsList
import it.unipd.francesco_dotoli.simon_game.defaultPadding

@Composable
fun colored_button( buttonColor : Color, onclick : () -> Unit){
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