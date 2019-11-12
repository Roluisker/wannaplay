package com.game.books

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

//"https://images-na.ssl-images-amazon.com/images/I/41ZNV2KEBML._SX258_BO1,204,203,200_.jpg"

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}