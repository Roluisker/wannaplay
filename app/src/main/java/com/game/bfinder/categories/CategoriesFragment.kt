package com.game.bfinder.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.game.bfinder.BuildConfig

import com.game.bfinder.R
import com.game.bfinder.categories.adapter.CategoriesAdapter
import com.game.bfinder.categories.adapter.CategoryViewHolder

import com.game.core.AppConstants
import com.game.core.BaseFragment
import com.game.bfinder.databinding.FragmentCategoriesBinding

import com.game.bfinder.categories.repository.CategoriesRepositoryImpl
import com.google.android.instantapps.InstantApps
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

        return init(categoryViewModel, binding).root
    }

    private fun init(
        viewModel: CategoriesViewModel,
        binding: FragmentCategoriesBinding
    ): FragmentCategoriesBinding {

        /*
        if (InstantApps.isInstantApp(context!!)) {
            binding.button2.hide()
        }*/

        initCategories(viewModel, binding)

        binding.lifecycleOwner = this

        return binding
    }

    private fun initCategories(viewModel: CategoriesViewModel, binding: FragmentCategoriesBinding) {
        binding.viewCategories.apply {
            setHasFixedSize(true)
            adapter = CategoriesAdapter(
                viewModel,
                this@CategoriesFragment,
                CategoryItemClickListener()
            )
        }
    }

    private inner class CategoryItemClickListener :
        CategoriesAdapter.ItemClickListener<CategoryViewHolder> {
        override fun onItemClick(holder: CategoryViewHolder) {
            if (InstantApps.isInstantApp(context!!)) {
                Timber.d(holder.binding.category!!.id.toString())
                showInstallPrompt(holder.binding.category!!.id)
            } else {
                val intent = Intent().setClassName(BuildConfig.APPLICATION_ID, "com.game.books.BooksActivity")
                intent.putExtra(AppConstants.CATEGORY_ID_PARAM, holder.binding.category!!.id)
                startActivity(intent)
            }
        }
    }

    private fun showInstallPrompt(categoryId: Int) {
        InstantApps.showInstallPrompt(
            activity!!,
            Intent().apply {
                putExtra(AppConstants.CATEGORY_ID_PARAM, categoryId)
            },
            AppConstants.CATEGORIES_TO_BOOK_REQUEST,
            AppConstants.CATEGORIES_TO_BOOK_REQUEST_REFER
        )
    }
}