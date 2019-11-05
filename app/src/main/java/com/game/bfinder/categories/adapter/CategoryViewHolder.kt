package com.game.bfinder.categories.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.game.bfinder.databinding.CategoryListItemBinding

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    var itemClickListener: CategoriesAdapter.ItemClickListener<CategoryViewHolder>? = null

    override fun onClick(v: View) {
        itemClickListener?.onItemClick(this)
    }

}