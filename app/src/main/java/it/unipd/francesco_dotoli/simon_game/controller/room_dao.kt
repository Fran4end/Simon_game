package it.unipd.francesco_dotoli.simon_game.controller

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.unipd.francesco_dotoli.simon_game.model.GameModel

@Dao
interface RoomDao {

    @Query("SELECT * FROM GameModel")
    fun getAll(): List<GameModel>

    @Insert
    fun insert(game: GameModel)

    @Delete
    fun delete(game: GameModel)

    @Query("DELETE FROM GameModel")
    fun deleteAll()
}
