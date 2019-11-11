package com.game.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.game.books.repository.BooksRepository
import com.game.core.BaseViewModel
import com.game.core.model.Book
import java.util.ArrayList

class BooksViewModel(booksRepository: BooksRepository) : BaseViewModel() {

    private val _categoryId: MutableLiveData<Int> = MutableLiveData()

    val books: LiveData<ArrayList<Book>> = Transformations
        .switchMap(_categoryId) { id ->
            booksRepository.fetchBooks(id)
        }

    fun searchByCategoryId(categoryId: Int) {
        _categoryId.value = categoryId
    }
}