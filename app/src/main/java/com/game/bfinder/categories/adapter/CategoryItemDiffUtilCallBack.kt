package com.game.bfinder.categories.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.game.core.extensions.equalTo
import com.game.core.extensions.isValid
import com.game.core.model.Category

class CategoryItemDiffUtilCallBack : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return if (oldItem.isValid()) {
            oldItem.id == newItem.id
        } else {
            oldItem === newItem
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.equalTo(newItem)

}