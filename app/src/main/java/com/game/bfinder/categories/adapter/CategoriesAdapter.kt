/*
 * Luis A. Bejarano Sánchez
 *
 * github.com/Roluisker
 *
 * Proof of concept Intant, Demand, Modules, Architecture components
 *
 */
package com.game.bfinder.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.game.bfinder.categories.CategoriesViewModel
import com.game.bfinder.databinding.CategoryListItemBinding
import com.game.core.model.Category
import java.util.ArrayList

class CategoriesAdapter(
    categoryViewModel: CategoriesViewModel,
    lifecycleOwner: LifecycleOwner,
    private val itemClickListener: ItemClickListener<CategoryViewHolder>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories: ArrayList<Category> = ArrayList()

    init {
        categoryViewModel.categories.observe(lifecycleOwner, Observer {
            categories.addAll(it)
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryListItemBinding.inflate(inflater, parent, false)
        val holder = CategoryViewHolder(binding)
        holder.itemClickListener = itemClickListener
        return holder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding.category = item
        }
    }

    override fun getItemCount(): Int = categories.size

    private fun getItem(index: Int): Category? {
        return when {
            categories.size >= index -> categories[index]
            else -> null
        }
    }

    interface ItemClickListener<CategoryViewHolder> {
        fun onItemClick(holder: CategoryViewHolder)
    }
}