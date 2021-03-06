/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.game.books.BooksViewModel
import com.game.books.databinding.BookListItemBinding
import com.game.core.model.Book
import java.util.ArrayList

const val GRID_COUNT = 3

class BooksAdapter(
    categoryViewModel: BooksViewModel,
    lifecycleOwner: LifecycleOwner,
    private val itemClickListener: ItemClickListener<BooksViewHolder>
) : RecyclerView.Adapter<BooksViewHolder>() {

    private var books: ArrayList<Book> = ArrayList()

    init {
        categoryViewModel.books.observe(lifecycleOwner, Observer {
            books.addAll(it.data!!)
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(inflater, parent, false)
        val holder = BooksViewHolder(binding)
        holder.itemClickListener = itemClickListener
        return holder
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding.book = item
        }
    }

    private fun getItem(index: Int): Book? {
        return when {
            books.size >= index -> books[index]
            else -> null
        }
    }

    override fun getItemCount(): Int = books.size

    interface ItemClickListener<BooksViewHolder> {
        fun onItemClick(holder: BooksViewHolder)
    }
}

