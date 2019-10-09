package com.game.bfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.game.core.AppConstants
import com.game.core.BaseFragment
import com.game.core.extensions.openModule
import kotlinx.android.synthetic.main.fragment_categories.*
import com.game.bfinder.databinding.FragmentCategoriesBinding

class CategoriesFragment : BaseFragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            openModule(AppConstants.MEET_MODULE_PATH)
        }

    }

}