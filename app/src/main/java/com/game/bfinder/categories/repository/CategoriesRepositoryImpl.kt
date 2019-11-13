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
import com.game.core.BaseRepository

import com.game.core.extensions.defaultCategories
import com.game.core.model.Category
import com.game.core.model.livedata.CategoriesLiveData
import kotlin.collections.ArrayList

class CategoriesRepositoryImpl :
    BaseRepository(), CategoriesRepository {

    override fun fetchCategories(): LiveData<ArrayList<Category>> =
        CategoriesLiveData.create(defaultCategories())
}