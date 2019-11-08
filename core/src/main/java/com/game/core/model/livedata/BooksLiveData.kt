package com.game.core.model.livedata

import androidx.lifecycle.LiveData
import com.game.core.model.Book

class BooksLiveData private constructor(books: ArrayList<Book>) :
    LiveData<ArrayList<Book>>() {

    init {
        postValue(books)
    }

    companion object {
        fun create(books: ArrayList<Book>): LiveData<ArrayList<Book>> =
            BooksLiveData(books)
    }
}