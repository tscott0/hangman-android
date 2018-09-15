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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guess_button.setOnClickListener {
            viewModel.makeGuess(guess_edit_text.text.toString())
            guess_edit_text.text.clear()
        }

        reset_button.setOnClickListener {
            viewModel.reset()
        }

        viewModel.getGame().observe(this, Observer<Game> { game ->
            current_guesses.text = game?.getCurrentGuesses()
            if (game?.getWon() == true) {
                game_word.visibility = View.GONE
                guess_button.visibility = View.GONE

                you_win.visibility = View.VISIBLE
                reset_button.visibility = View.VISIBLE
            } else {
                game_word.text = viewModel.getGameWord().value

                you_win.visibility = View.GONE
                reset_button.visibility = View.GONE

                game_word.visibility = View.VISIBLE
                guess_button.visibility = View.VISIBLE
            }
        })

    }
}
