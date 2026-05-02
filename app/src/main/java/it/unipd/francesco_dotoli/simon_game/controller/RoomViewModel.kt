package it.unipd.francesco_dotoli.simon_game.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.unipd.francesco_dotoli.simon_game.model.GameModel

class RoomViewModel(private val roomRepository: RoomRepository) : ViewModel() {

    private val _allGames = MutableLiveData<List<GameModel>>()
    val savedGames: LiveData<List<GameModel>> get() = _allGames

    fun fetchAllGame() {
        roomRepository.getAllGames { games ->
            _allGames.postValue(games)
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