/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.books.adapter

import android.view.View
import com.game.books.databinding.BookListItemBinding
import com.game.core.BaseOnClickViewHolder

class BooksViewHolder(val binding: BookListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener(this)
    }

    var itemClickListener: BooksAdapter.ItemClickListener<BooksViewHolder>? = null

    override fun onClick(v: View) {
        itemClickListener?.onItemClick(this)
    }
}