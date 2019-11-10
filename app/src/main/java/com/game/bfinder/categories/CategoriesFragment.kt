package com.game.bfinder.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.game.bfinder.BuildConfig
import com.game.bfinder.MainActivity

import com.game.bfinder.R
import com.game.bfinder.categories.adapter.CategoriesAdapter
import com.game.bfinder.categories.adapter.CategoryViewHolder

import com.game.core.AppConstants
import com.game.core.BaseFragment
import com.game.bfinder.databinding.FragmentCategoriesBinding

import com.game.bfinder.categories.repository.CategoriesRepositoryImpl
import com.game.core.extensions.hide
import com.game.core.model.ModuleInstallRequest
import com.google.android.instantapps.InstantApps
import timber.log.Timber

private const val PACKAGE_NAME = "com.group.pow"
private const val PACKAGE_NAME_ONDEMAND = "$PACKAGE_NAME.search"
private const val SEARCH_ACTIVITY_CLASSNAME = "$PACKAGE_NAME_ONDEMAND.SearchActivity"

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

        addOnClicks(binding)

        initCategories(viewModel, binding)

        binding.lifecycleOwner = this

        return binding
    }

    private fun addOnClicks(binding: FragmentCategoriesBinding) {
        /*
        binding.button2.setOnClickListener {
            //openModule(AppConstants.BOOKS_MODULE_PATH)
            val intent =
                Intent().setClassName(BuildConfig.APPLICATION_ID, "com.game.books.BooksActivity")
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            (activity as MainActivity).loadAndLaunchModule(
                ModuleInstallRequest(
                    AppConstants.SEARCH_MODULE,
                    SEARCH_ACTIVITY_CLASSNAME
                )
            )
        }

        binding.button4.setOnClickListener {
            val intent = Intent().setClassName(
                BuildConfig.APPLICATION_ID,
                "com.group.pow.search.SearchActivity"
            )
            startActivity(intent)
        }*/
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
                
            }
        }
    }

    private fun showInstallPrompt(categoryId: Int) {
        InstantApps.showInstallPrompt(
            activity!!,
            Intent().apply {
                putExtra(AppConstants.ID_BOOK_PARAM, categoryId)
            },
            AppConstants.CATEGORIES_TO_BOOK_REQUEST,
            AppConstants.CATEGORIES_TO_BOOK_REQUEST_REFER
        )
    }
}