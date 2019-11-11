package com.game.books.repository

import androidx.lifecycle.LiveData
import com.game.core.BaseRepository
import com.game.core.model.Book
import com.game.core.model.livedata.BooksLiveData
import com.game.core.extensions.defaultBooks

class BooksRepositoryImpl : BaseRepository(), BooksRepository {

    override fun fetchBooks(categoryId: Int): LiveData<ArrayList<Book>> =
        BooksLiveData.create(defaultBooks())
}