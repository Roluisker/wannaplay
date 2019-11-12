package com.game.bfinder.categories

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.game.bfinder.R
import com.game.core.AppConstants

@BindingAdapter("imageId")
fun loadCategoryImage(view: ImageView, categoryId: Int) {
    var imageResource = when (categoryId) {
        AppConstants.ART_FICTION_CATEGORY -> R.drawable.art_category
        AppConstants.SCIENCE_FICTION_CATEGORY -> R.drawable.fiction_category
        else -> R.drawable.ic_launcher_background
    }
    view.setImageResource(imageResource)
}