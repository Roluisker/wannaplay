/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.books.repository

import androidx.lifecycle.LiveData
import com.game.core.model.Book
import com.group.pow.resources.Resource
import java.util.ArrayList

interface BooksRepository {
    fun fetchBooksByCategory(categorId: Int): LiveData<Resource<ArrayList<Book>>>
}