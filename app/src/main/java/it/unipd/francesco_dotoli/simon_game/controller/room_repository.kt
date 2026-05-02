package it.unipd.francesco_dotoli.simon_game.controller

import it.unipd.francesco_dotoli.simon_game.model.GameModel

class RoomRepository(private val roomDao: RoomDao) {

    fun insertGame(gameModel: GameModel, onComplete: () -> Unit = {}) {
        Thread {
            roomDao.insert(gameModel)
            onComplete()
        }.start()
    }

    fun deleteGame(gameModel: GameModel, onComplete: () -> Unit = {}) {
        Thread {
            roomDao.delete(gameModel)
            onComplete()
        }.start()
    }

    fun getAllGames(callback: (List<GameModel>) -> Unit) {
        Thread {
            val games = roomDao.getAll()
            callback(games)
        }.start()
    }

    fun deleteAll(onComplete: () -> Unit = {}) {
        Thread {
            roomDao.deleteAll()
            onComplete()
        }.start()
    }
}