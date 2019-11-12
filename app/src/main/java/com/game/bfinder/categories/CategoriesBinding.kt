package com.game.bfinder.categories

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.game.core.AppConstants
import com.squareup.picasso.Picasso

@BindingAdapter("imageId")
fun loadCategoryImage(view: ImageView, categoryId: Int) {
    var url = when (categoryId) {
        AppConstants.ART_CATEGORY ->  AppConstants.ART_CATEGORY_URL
        AppConstants.SCIENCE_FICTION_CATEGORY ->AppConstants.SCIENCE_FICTION_CATEGORY_URL
        else -> ""
    }

    Picasso.get().load(url).into(view)
}