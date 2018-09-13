package dev.tomscott.hangman

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import dev.tomscott.hangman.viewmodel.GuessViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: GuessViewModel by lazy {
        ViewModelProviders.of(this).get(GuessViewModel::class.java)
    }

    private lateinit var incrementButton: Button
    private lateinit var countTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        incrementButton = findViewById(R.id.increment)
        incrementButton.setOnClickListener {
            viewModel.incrementCount()
        }

        countTextView = findViewById(R.id.count)

        viewModel.getCount().observe(this, Observer<Int> { count ->
            countTextView.text = count.toString()
        })


    }
}
