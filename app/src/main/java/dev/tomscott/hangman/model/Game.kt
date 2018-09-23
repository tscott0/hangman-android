package dev.tomscott.hangman.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class Game {

    init {
        reset()
    }

    private val dictionary = Dictionary()

    // LiveData
    private var targetWordLiveData = MutableLiveData<String>()
    fun getTargetWord(): LiveData<String> {
        return targetWordLiveData
    }

    private var gameState = MutableLiveData<GameState>()
    fun getGameState(): LiveData<GameState> {
        return gameState
    }

    private var currentGuesses: MutableList<Char> = mutableListOf()
    val targetWord: String
        get() {
            return targetWordLiveData.value ?: ""
        }

    fun guess(letter: Char) {
        if (currentGuesses.contains(letter)) {
            return
        }

        currentGuesses.add(letter)

        if (currentGuesses.containsAll(targetWord.toSet())) {
            gameState.value = GameState.WON
        } else if (getIncorrectGuesses().size > 8) {
            gameState.value = GameState.LOST
        }
    }

    fun reset(): Game {
        targetWordLiveData.value = dictionary.randomWord()
        currentGuesses = mutableListOf()
        gameState.value = GameState.PLAYING
        return this
    }

    fun getCurrentGuesses(): List<Char> {
        return currentGuesses
    }

    fun getIncorrectGuesses(): List<Char> {
        return currentGuesses.minus(targetWord.asIterable())
    }

}