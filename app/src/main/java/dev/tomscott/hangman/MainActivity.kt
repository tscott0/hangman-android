package dev.tomscott.hangman

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.tomscott.hangman.model.Game
import dev.tomscott.hangman.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guess_button.setOnClickListener {
            gameViewModel.makeGuess(guess_edit_text.text.toString())
            guess_edit_text.text.clear()
        }

        reset_button.setOnClickListener {
            gameViewModel.reset()
        }

        gameViewModel.getGame().observe(this, Observer<Game> { game ->
            if (game != null) {
                incorrect_guesses.text = gameViewModel.getIncorrectGuesses()

                if (game.getWon()) {
                    game_word.visibility = View.GONE
                    guess_button.visibility = View.GONE

                    you_win.visibility = View.VISIBLE
                    reset_button.visibility = View.VISIBLE
                } else {
                    game_word.text = gameViewModel.getGameWord()

                    you_win.visibility = View.GONE
                    reset_button.visibility = View.GONE

                    game_word.visibility = View.VISIBLE
                    guess_button.visibility = View.VISIBLE
                }
            }
        })
    }
}
