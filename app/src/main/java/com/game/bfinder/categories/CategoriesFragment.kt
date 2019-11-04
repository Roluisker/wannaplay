package com.game.bfinder.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.game.bfinder.R

import com.game.core.AppConstants
import com.game.core.BaseFragment
import com.game.core.extensions.openModule
import kotlinx.android.synthetic.main.fragment_categories.*
import com.game.bfinder.databinding.FragmentCategoriesBinding

import com.game.bfinder.categories.repository.CategoriesRepositoryImpl
import com.game.core.model.Category
import timber.log.Timber
import java.util.ArrayList

class CategoriesFragment : BaseFragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val categoryViewModel = CategoriesViewModel(CategoriesRepositoryImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val testObserver = Observer<ArrayList<Category>> { liveResponse ->
            liveResponse.forEach {
                Timber.d("%s%s", it.title + "", it.id)
            }
        }

        categoryViewModel.categories.observe(this, testObserver)

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_categories, container, false
            )
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