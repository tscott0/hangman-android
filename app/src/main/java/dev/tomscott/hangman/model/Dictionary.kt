package dev.tomscott.hangman.model

class Dictionary {
    private val dict: List<String> = listOf("BANANA", "ORANGE", "APPLE", "GRAPE", "KIWI")

    fun randomWord(): String {
        return dict.shuffled()[0]
    }
}