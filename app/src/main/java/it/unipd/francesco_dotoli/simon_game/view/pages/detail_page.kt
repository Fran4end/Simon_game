package it.unipd.francesco_dotoli.simon_game.view.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unipd.francesco_dotoli.simon_game.R
import it.unipd.francesco_dotoli.simon_game.defaultPadding
import it.unipd.francesco_dotoli.simon_game.model.GameModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(gameModel: GameModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.detail_title), fontSize = 32.sp) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(defaultPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.buttons_clicked) + ": ")
                    }

                    append("${gameModel.buttonsClicked}\n")
                },
            )


            if (!gameModel.correctSequence.isEmpty()) {
                HorizontalDivider()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.correct_sequence) + ":\n\n")
                        }

                        append("${gameModel.correctSequence}\n")
                    },
                )
            }

            if (!gameModel.missSequence.isEmpty()) {
                HorizontalDivider()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(R.string.missing_sequence) + ":\n\n")
                        }

                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append(gameModel.missSequence)
                        }
                    }
                )
            }
        }
    }

}