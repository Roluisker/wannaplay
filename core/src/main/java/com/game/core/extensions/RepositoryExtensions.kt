/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.core.extensions

import com.game.core.BaseRepository
import com.game.core.model.Book
import com.game.core.model.Category
import java.util.ArrayList

/**
 * Repository extensions
 **/
fun BaseRepository.defaultCategories(): ArrayList<Category> = ArrayList<Category>().apply {
    add(Category(1, "Art"))
    add(Category(2, "Science fiction"))
}

fun BaseRepository.defaultBooks(): ArrayList<Book> = ArrayList<Book>().apply {
    add(Book("pjoj", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
    add(Book("dfg sfsf", "sdasd", "tet"))
}