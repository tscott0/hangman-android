package dev.tomscott.hangman.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import dev.tomscott.hangman.model.Game
import dev.tomscott.hangman.utils.GuessValidator
import dev.tomscott.hangman.utils.StringFormatter.formatGameWord
import dev.tomscott.hangman.utils.StringFormatter.formatIncorrectGuesses

class GameViewModel : ViewModel() {

    // LiveData
    private var gameLiveData = MutableLiveData<Game>()
    private var gameWordLiveData: LiveData<String> = Transformations.map(gameLiveData) { game ->
        formatGameWord(game.getWord(), game.getCurrentGuesses())
    }
    private var incorrectGuessesLiveData: LiveData<String> = Transformations.map(gameLiveData) { game ->
        formatIncorrectGuesses(game.getIncorrectGuesses())
    }
    private var winStateLiveData: LiveData<Boolean> = Transformations.map(gameLiveData) { game ->
        game.getGameState()
    }

    fun getGameWordLiveData(): LiveData<String> {
        return gameWordLiveData
    }

    fun getIncorrectGuesses(): LiveData<String> {
        return incorrectGuessesLiveData
    }

    fun getGameStateLiveData(): LiveData<Boolean> {
        return winStateLiveData
    }

    init {
        gameLiveData.value = Game()
    }

    // User Interaction
    fun makeGuess(guessString: String) {
        Log.i("GuessViewModel", "makeGuess $guessString")

        val sanitizedGuess = guessString.toUpperCase().trim()

        if (!GuessValidator.guessIsValid(sanitizedGuess)) {
            return
        }

        val guessChar = sanitizedGuess.toCharArray()[0]

        gameLiveData.value?.guess(guessChar)
        gameLiveData.postValue(gameLiveData.value)
    }

    fun reset() {
        gameLiveData.postValue(gameLiveData.value?.reset())
    }

}