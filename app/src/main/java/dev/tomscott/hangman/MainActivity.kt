package dev.tomscott.hangman

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import dev.tomscott.hangman.model.Game
import dev.tomscott.hangman.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: GameViewModel by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    private lateinit var guessEditText: EditText
    private lateinit var guessButton: Button
    private lateinit var gameWordTextView: TextView
    private lateinit var youWinTextView: TextView
    private lateinit var currentGuesses: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        youWinTextView = findViewById(R.id.you_win)
        guessEditText = findViewById(R.id.guess_edit_text)
        guessButton = findViewById(R.id.guess_button)
        gameWordTextView = findViewById(R.id.game_word)
        currentGuesses = findViewById(R.id.current_guesses)

        guessButton.setOnClickListener {
            viewModel.makeGuess(guessEditText.text.toString())
            guessEditText.text.clear()
        }

        reset_button.setOnClickListener {
            viewModel.reset()
        }

        viewModel.getGame().observe(this, Observer<Game> { game ->
            currentGuesses.text = game?.getCurrentGuesses()
            if (game?.getWon() == true) {
                youWinTextView.visibility = View.VISIBLE
                gameWordTextView.visibility = View.GONE
            } else {
                gameWordTextView.text = viewModel.getGameWord().value
            }
        })

    }
}
