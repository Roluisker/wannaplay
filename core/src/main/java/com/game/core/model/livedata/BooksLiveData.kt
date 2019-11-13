/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.core.model.livedata

import androidx.lifecycle.LiveData
import com.game.core.model.Book
import com.group.pow.resources.Resource

class BooksLiveData private constructor(books: Resource<ArrayList<Book>>) :
    LiveData<ArrayList<Book>>() {

    init {
        postValue(books.data)
    }

    companion object {
        fun create(books: Resource<ArrayList<Book>>): LiveData<ArrayList<Book>> =
            BooksLiveData(books)
    }
}