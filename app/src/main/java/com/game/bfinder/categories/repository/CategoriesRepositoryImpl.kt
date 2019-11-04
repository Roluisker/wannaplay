package com.game.bfinder.categories.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.game.core.BaseRepository
import com.game.core.FirebaseExecutor
import com.game.core.extensions.defaultCategories
import com.game.core.model.Category
import com.game.core.model.livedata.CategoriesLiveData
import java.util.ArrayList

class CategoriesRepositoryImpl(private val executor: FirebaseExecutor = FirebaseExecutor()) :
    BaseRepository(), CategoriesRepository {

    override fun fetchCategories(): LiveData<ArrayList<Category>> =
        CategoriesLiveData.create(defaultCategories())

    /*
    override fun fetchCategoriesPageList(): LiveData<PagedList<Category>> {

        val factory: DataSource.Factory<Int, Category> = DataSource.

        val pagedListBuilder: LivePagedListBuilder<Int, Category> = LivePagedListBuilder(
            factory,
            50
        ).setFetchExecutor(executor.networkIO())

        return pagedListBuilder.build()
    }*/

}