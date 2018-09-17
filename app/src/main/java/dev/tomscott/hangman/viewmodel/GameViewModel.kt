package dev.tomscott.hangman.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import dev.tomscott.hangman.model.Game
import dev.tomscott.hangman.utils.GuessValidator
import dev.tomscott.hangman.utils.StringFormatter.formatGameWord
import dev.tomscott.hangman.utils.StringFormatter.formatIncorrectGuesses

class GameViewModel : ViewModel() {

    private var game = MutableLiveData<Game>()
    fun getGame(): LiveData<Game> {
        return game
    }

    fun getGameWord(): String {
        game.value?.let {
            return formatGameWord(it.getWord(), it.getCurrentGuesses())
        }

        return ""
    }

    fun getIncorrectGuesses(): String {
        game.value?.let {
            return formatIncorrectGuesses(it.getIncorrectGuesses())
        }

        return ""
    }

    init {
        game.value = Game()
    }

    fun makeGuess(guessString: String) {
        Log.i("GuessViewModel", "makeGuess $guessString")

        val sanitizedGuess = guessString.toUpperCase().trim()

        if (!GuessValidator.guessIsValid(sanitizedGuess)) {
            return
        }

        val guessChar = sanitizedGuess.toCharArray()[0]

        game.value?.guess(guessChar)
        game.postValue(game.value)
    }

    fun reset() {
        game.postValue(game.value?.reset())
    }

}