package dev.tomscott.hangman.utils

object StringFormatter {

    fun formatIncorrectGuesses(incorrectGeusses: Iterable<Char>?): String {
        incorrectGeusses?.let {
            return it.joinToString(separator=" ")
        }

        return ""
    }

    fun formatGameWord(targetWord: String, currentGuesses: Iterable<Char>): String {
        var displayString = ""

        for (value in targetWord) {
            displayString += if (currentGuesses.contains(value)) {
                value
            } else {
                "_"
            }
        }

        return displayString
    }
}