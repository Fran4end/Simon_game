package it.unipd.francesco_dotoli.simon_game.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameModel(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "buttons_clicked") var buttonsClicked: Int = 0,
    @ColumnInfo(name = "correct_sequence") var correctSequence: String = "",
    @ColumnInfo(name = "miss_sequence") var missSequence: String = "",
) {
    constructor(navParam: String) : this() {
        val navParamList = navParam.split(".")
        buttonsClicked = navParamList.getOrElse(0, defaultValue = { "0" }).toIntOrNull()
            ?: 0
        correctSequence = navParamList.getOrElse(1, defaultValue = { "" })
        missSequence = navParamList.getOrElse(2, defaultValue = { "" })
    }

    fun toNavParam(): String {
        return "$buttonsClicked.$correctSequence.$missSequence"
    }
}
