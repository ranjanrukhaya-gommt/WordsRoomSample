package com.gaura.learn.kotlinroomsample.repository

import androidx.lifecycle.LiveData
import com.gaura.learn.kotlinroomsample.local.Word
import com.gaura.learn.kotlinroomsample.local.WordDao

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun delete() {
        wordDao.deleteAll()
    }
}