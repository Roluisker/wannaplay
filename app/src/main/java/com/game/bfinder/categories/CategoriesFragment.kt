package com.game.bfinder.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.LinearLayoutManager
import com.game.bfinder.R
import com.game.bfinder.categories.adapter.CategoriesAdapter
import com.game.bfinder.categories.adapter.CategoryViewHolder

import com.game.core.AppConstants
import com.game.core.BaseFragment
import com.game.core.extensions.openModule
import kotlinx.android.synthetic.main.fragment_categories.*
import com.game.bfinder.databinding.FragmentCategoriesBinding

import com.game.bfinder.categories.repository.CategoriesRepositoryImpl
import timber.log.Timber

class CategoriesFragment : BaseFragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoryViewModel = CategoriesViewModel(CategoriesRepositoryImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_categories, container, false
            )

        binding.viewCategories.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = CategoriesAdapter(
                categoryViewModel,
                this@CategoriesFragment,
                CategoryItemClickListener()
            )
        }

        binding.lifecycleOwner = this

        return binding.root

    }

    private inner class CategoryItemClickListener :
        CategoriesAdapter.ItemClickListener<CategoryViewHolder> {
        override fun onItemClick(holder: CategoryViewHolder) {
            Timber.d(holder.binding.category!!.title)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            openModule(AppConstants.MEET_MODULE_PATH)
        }

    }

}