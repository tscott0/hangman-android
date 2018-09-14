package dev.tomscott.hangman.utils

object GuessValidator {
    private val validLetters: List<String> = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")

    fun guessIsValid(guess: String): Boolean {
        return guess.isNotEmpty() && this.validLetters.contains(guess)
    }
}