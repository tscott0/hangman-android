package dev.tomscott.hangman

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.tomscott.hangman.model.GameState
import dev.tomscott.hangman.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameViewModel: GameViewModel by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    private val DEFAULT_GAME_STATE = false

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

        gameViewModel.getIncorrectGuesses().observe(this, Observer {
            incorrect_guesses.text = it
        })

        gameViewModel.getGameWordLiveData().observe(this, Observer {
            game_word.text = it
        })

        gameViewModel.getTargetWord().observe(this, Observer {
            target_word.text = it
        })

        gameViewModel.getGameStateLiveData().observe(this, Observer {
            val gameState = it ?: DEFAULT_GAME_STATE

            when (gameState) {
                GameState.WON -> {
                    end_message.text = "YOU WON \uD83D\uDE01"
                    game_word.visibility = View.GONE
                    guess_button.visibility = View.GONE

                    game_ended.visibility = View.VISIBLE
                    reset_button.visibility = View.VISIBLE
                }
                GameState.LOST -> {
                    end_message.text = "YOU LOST \uD83D\uDE14"
                    game_word.visibility = View.GONE
                    guess_button.visibility = View.GONE

                    game_ended.visibility = View.VISIBLE
                    reset_button.visibility = View.VISIBLE
                }
                GameState.PLAYING -> {
                    game_ended.visibility = View.GONE
                    reset_button.visibility = View.GONE

                    game_word.visibility = View.VISIBLE
                    guess_button.visibility = View.VISIBLE
                }
            }
        })
    }
}
