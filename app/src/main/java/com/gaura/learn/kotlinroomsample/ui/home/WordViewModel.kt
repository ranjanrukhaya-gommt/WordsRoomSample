package com.gaura.learn.kotlinroomsample.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gaura.learn.kotlinroomsample.local.Word
import com.gaura.learn.kotlinroomsample.local.WordRoomDatabase
import com.gaura.learn.kotlinroomsample.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     * ViewModels have a coroutine scope based on their life cycle called viewModelScope
     */
    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.delete()
    }
}