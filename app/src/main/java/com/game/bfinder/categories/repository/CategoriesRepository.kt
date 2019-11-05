package com.game.bfinder.categories.repository

import androidx.lifecycle.LiveData
import com.game.core.model.Category
import java.util.ArrayList

interface CategoriesRepository {
    fun fetchCategories(): LiveData<ArrayList<Category>>
}