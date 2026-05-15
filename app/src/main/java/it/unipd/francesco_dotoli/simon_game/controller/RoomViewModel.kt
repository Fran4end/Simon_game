package it.unipd.francesco_dotoli.simon_game.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import it.unipd.francesco_dotoli.simon_game.model.AppDatabase
import it.unipd.francesco_dotoli.simon_game.model.GameModel

class RoomViewModel(application: Application) : AndroidViewModel(application) {
    private val roomRepository: RoomRepository
    val allGames = MutableLiveData<List<GameModel>>()

    init {
        val roomDao = AppDatabase.getDatabase(application).roomDao()
        roomRepository = RoomRepository(roomDao)
        fetchAllGame()
    }

    fun fetchAllGame() {
        roomRepository.getAllGames { games ->
            allGames.postValue(games)
        }
    }

    fun insert(gameModel: GameModel) {
        roomRepository.insertGame(gameModel) {
            fetchAllGame()
        }
    }

    fun delete(gameModel: GameModel) {
        roomRepository.deleteGame(gameModel) {
            fetchAllGame()
        }
    }

    fun deleteAll() {
        roomRepository.deleteAll() {
            fetchAllGame()
        }
    }
}