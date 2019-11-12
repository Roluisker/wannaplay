package com.game.bfinder.categories.adapter

import android.view.View
import com.game.bfinder.databinding.CategoryListItemBinding
import com.game.core.BaseOnClickViewHolder
import android.view.animation.AnimationUtils.loadAnimation
import com.game.bfinder.R
import android.view.animation.Animation
import android.R.attr.duration
import android.animation.Animator
import android.view.animation.RotateAnimation
import android.animation.AnimatorSet
import android.R.attr.fromDegrees

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener(this)
    }

    var itemClickListener: CategoriesAdapter.ItemClickListener<CategoryViewHolder>? = null

    override fun onClick(v: View) {
//        val animation1 = loadAnimation(v.context, R.anim.move) as Animator


        /*
        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = duration.toLong()
        rotate.repeatCount = Animation.INFINITE*/

        val rotate = RotateAnimation(
            0f, 360f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 10000
        rotate.isFillEnabled = true
        rotate.fillAfter = true
        rotate.repeatCount = Animation.INFINITE

        binding.categoryImage.startAnimation(rotate)
        //val set = AnimatorSet()
        //set.play(animation1).with(binding.categoryImage)
        //set.playTogether(animation1, rotate as Animator)

        //binding.categoryImage.startAnimation(set)
        //itemClickListener?.onItemClick(this)
    }
}