package com.game.bfinder.categories.adapter


import android.view.animation.AnimationSet
import android.view.View
import com.game.bfinder.databinding.CategoryListItemBinding
import com.game.core.BaseOnClickViewHolder
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.RotateAnimation
import com.game.bfinder.R
import com.google.android.instantapps.InstantApps

class CategoryViewHolder(val binding: CategoryListItemBinding) :
    BaseOnClickViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener(this)
    }

    var itemClickListener: CategoriesAdapter.ItemClickListener<CategoryViewHolder>? = null

    override fun onClick(v: View) {

        /*
        val rotate = RotateAnimation(
            0f, 360f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 80000
        rotate.isFillEnabled = true
        rotate.fillAfter = true
        rotate.repeatCount = Animation.INFINITE

        binding.categoryImage.startAnimation(rotate)
        binding.categoryImage.startAnimation(animation1)
        val set = AnimationSet(false)
        set.addAnimation(rotate)
        set.addAnimation(animation1)*/

        if(InstantApps.isInstantApp(v.context)) {
            binding.categoryImage.startAnimation(loadAnimation(v.context, R.anim.move))
        }

        //set.play(animation1).with(binding.categoryImage)
        //set.playTogether(animation1, rotate as Animator)

        itemClickListener?.onItemClick(this)
    }
}