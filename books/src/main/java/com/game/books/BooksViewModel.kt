package com.game.books

import androidx.lifecycle.LiveData
import com.game.books.repository.BooksRepository
import com.game.core.BaseViewModel
import com.game.core.model.Book
import java.util.ArrayList

class BooksViewModel(booksRepository: BooksRepository) : BaseViewModel() {
    val books: LiveData<ArrayList<Book>> = booksRepository.fetchBooks()
}