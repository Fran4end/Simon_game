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
import it.unipd.francesco_dotoli.simon_game.defaultPadding

@Composable
fun colored_button( buttonColor : Color, onclick : () -> Unit){
    Button(
        onClick = onclick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        modifier = Modifier.fillMaxWidth().wrapContentHeight().aspectRatio(4f/3f),
    ){}
}

fun getLetterFromColor(color : Color) : String{
  return when(color){
        Color(0xfff02626) -> "R"
        Color(0xFF0CB808) -> "G"
        Color(0xFF2196F3) -> "B"
        Color(0xFFE758D8) -> "M"
        Color(0xfff0c23a) -> "Y"
        Color(0xFF00BCD4) -> "C"
        else -> "N/A"
    }
}