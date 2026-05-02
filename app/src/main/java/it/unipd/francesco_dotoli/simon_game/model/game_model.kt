package it.unipd.francesco_dotoli.simon_game.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameModel(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "buttons_clicked") val buttonsClicked: Int = 0,
    @ColumnInfo(name = "correct_sequence") val correctSequence: String = "",
    @ColumnInfo(name = "miss_sequence") val missSequence: String = "",
)
