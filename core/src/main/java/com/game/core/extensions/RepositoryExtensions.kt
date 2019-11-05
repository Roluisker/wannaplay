package com.game.core.extensions

import com.game.core.BaseRepository
import com.game.core.model.Category
import java.util.ArrayList

/**
 * Repository extensions
 **/
fun BaseRepository.defaultCategories(): ArrayList<Category> = ArrayList<Category>().apply {
    add(Category(1, "Ciencia Ficcion"))
    add(Category(2, "Ciencia Ficcion"))
    add(Category(3, "Ciencia Ficcion"))
    add(Category(4, "Ciencia Ficcion"))
}

/*
fun BaseRepository.defaultCategories(): ArrayList<Category> = ArrayList<Category>().apply {
    add(Category(1, "Ciencia Ficcion", "asdad"))
    add(Category(2, "Ciencia Ficcion", "asdad"))
    add(Category(3, "Ciencia Ficcion", "asdad"))
    add(Category(4, "Ciencia Ficcion", "asdad"))
}*/