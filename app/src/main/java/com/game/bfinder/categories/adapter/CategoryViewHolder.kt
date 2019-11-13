/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.bfinder.categories.adapter

import android.view.View
import com.game.bfinder.databinding.CategoryListItemBinding
import com.game.core.BaseOnClickViewHolder

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener(this)
    }

    var itemClickListener: CategoriesAdapter.ItemClickListener<CategoryViewHolder>? = null

    override fun onClick(v: View) {
        itemClickListener?.onItemClick(this)
    }
}