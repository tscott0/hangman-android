package dev.tomscott.hangman.model

class Game {

    private val dictionary = Dictionary()

    private var word: String
    private var currentGuesses: MutableList<Char> = mutableListOf()
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

    fun getCurrentGuesses(): List<Char> {
        return currentGuesses
    }

    fun getIncorrectGuesses(): List<Char> {
        return currentGuesses.minus(word.asIterable())
    }

    fun getWon(): Boolean {
        return won
    }

    fun reset(): Game {
        word = dictionary.randomWord()
        currentGuesses = mutableListOf()
        won = false
        return this
    }

}