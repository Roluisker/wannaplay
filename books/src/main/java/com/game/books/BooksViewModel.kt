package com.game.books

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.game.books.repository.BooksRepository
import com.game.core.BaseViewModel
import com.game.core.model.Book
import java.util.ArrayList

private const val PACKAGE_NAME = "com.group.pow"
private const val PACKAGE_NAME_ONDEMAND = "$PACKAGE_NAME.search"
private const val SEARCH_ACTIVITY_CLASSNAME = "$PACKAGE_NAME_ONDEMAND.SearchActivity"

class BooksViewModel(booksRepository: BooksRepository) : BaseViewModel() {

    private val _categoryId: MutableLiveData<Int> = MutableLiveData()

    val books: LiveData<ArrayList<Book>> = Transformations
        .switchMap(_categoryId) { id ->
            booksRepository.fetchBooks(id)
        }

    fun onClickBookSearch(view: View) {
        val intent = Intent()
        intent.setClassName(
            com.game.bfinder.BuildConfig.APPLICATION_ID,
            SEARCH_ACTIVITY_CLASSNAME
        )
        startActivity(view.context, intent, null)
    }

    fun searchByCategoryId(categoryId: Int) {
        _categoryId.value = categoryId
    }
}