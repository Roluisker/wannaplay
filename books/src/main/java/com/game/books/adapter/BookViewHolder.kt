package com.game.books.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.game.books.databinding.BookListItemBinding

class BookViewHolder(val binding: BookListItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    //var categoryClickListener: CategoriesAdapter.CategoryClickListener<CategoryViewHolder>? = null

    override fun onClick(v: View) {
      //  categoryClickListener?.onCategoryClicked(this)
    }

}