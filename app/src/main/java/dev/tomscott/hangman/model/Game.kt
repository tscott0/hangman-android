package dev.tomscott.hangman.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class Game {

    private val dictionary = Dictionary()

    private var word: String
    private var currentGuesses: MutableSet<Char> = mutableSetOf()
    private var won = false

    init {
        word = dictionary.randomWord()
    }

    fun guess(letter: Char) {
        if (currentGuesses.contains(letter)) {
            return
        }

        currentGuesses.add(letter)

        won = currentGuesses.containsAll(word.toSet())
    }

    fun getWord(): String {
        return word
    }

    fun getCurrentGuesses(): String {
        return currentGuesses.sorted().toString()
    }

    fun getWon(): Boolean {
        return won
    }

    fun reset(): Game {
        word = dictionary.randomWord()
        currentGuesses = mutableSetOf()
        won = false
        return this
    }

}