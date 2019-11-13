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
import com.game.core.model.Category

class CategoriesLiveData private constructor(categories: ArrayList<Category>) :
    LiveData<ArrayList<Category>>() {

    init {
        postValue(categories)
    }

    companion object {
        fun create(categories: ArrayList<Category>): LiveData<ArrayList<Category>> =
            CategoriesLiveData(categories)
    }
}