package com.game.books.repository

import androidx.lifecycle.LiveData
import com.game.core.model.Book
import java.util.ArrayList

interface BooksRepository {
    fun fetchBooks(categorId: Int): LiveData<ArrayList<Book>>
}