/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.bfinder.categories.repository

import androidx.lifecycle.LiveData
import com.game.core.model.Category
import java.util.ArrayList

interface CategoriesRepository {
    fun fetchCategories(): LiveData<ArrayList<Category>>
}