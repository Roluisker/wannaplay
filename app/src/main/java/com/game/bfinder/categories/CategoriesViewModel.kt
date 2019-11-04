package com.game.bfinder.categories

import androidx.lifecycle.LiveData

import com.game.bfinder.categories.repository.CategoriesRepository
import com.game.core.BaseViewModel
import com.game.core.model.Category
import java.util.ArrayList

class CategoriesViewModel(categoriesRepository: CategoriesRepository) : BaseViewModel() {
    val categories: LiveData<ArrayList<Category>> = categoriesRepository.fetchCategories()
}