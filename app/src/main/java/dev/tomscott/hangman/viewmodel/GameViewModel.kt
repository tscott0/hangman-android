package dev.tomscott.hangman.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import dev.tomscott.hangman.model.Game
import dev.tomscott.hangman.utils.GuessValidator

class GameViewModel : ViewModel() {

    private var game = MutableLiveData<Game>()
    fun getGame(): LiveData<Game> {
        return game
    }

    private var gameWord = MutableLiveData<String>()
    fun getGameWord(): LiveData<String> {
        return gameWord
    }

    fun updateGameWord() {
        game.value?.let {
            val currentWord = it.getWord()

            var displayString = ""

            for (value in currentWord) {
                displayString += if (it.getCurrentGuesses().contains(value)) {
                    value
                } else {
                    "_"
                }
            }

            gameWord.value = displayString
        }
    }

    init {
        game.value = Game()
        updateGameWord()
    }

    fun makeGuess(guessString: String): Boolean {
        Log.i("GuessViewModel", "makeGuess $guessString")

        val sanitizedGuess = guessString.toUpperCase().trim()

        if (!GuessValidator.guessIsValid(sanitizedGuess)) {
            return false
        }

        val guessChar = sanitizedGuess.toCharArray()[0]

        game.value?.guess(guessChar)
        updateGameWord()

        game.postValue(game.value)
        return true
    }

    fun reset() {
        game.postValue(game.value?.reset())
        updateGameWord()
    }

}