package com.game.bfinder.books.repository

import androidx.lifecycle.LiveData
import com.game.core.model.Book

interface BooksRepository {
    fun fetchBooks(): LiveData<ArrayList<Book>>
}