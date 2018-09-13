package dev.tomscott.hangman.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class GuessViewModel: ViewModel() {

    private var count = MutableLiveData<Int>()

    fun getCount(): LiveData<Int> {
        return count
    }

    fun setCount(value: Int) {
        count.value = value
    }

    fun incrementCount() {
        count.value = (count.value ?: 0) + 1
    }

}