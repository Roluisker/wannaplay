package com.game.books

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.game.books.adapter.BooksAdapter
import com.game.books.adapter.BooksViewHolder
import com.game.books.databinding.FragmentBooksBinding
import com.game.books.repository.BooksRepositoryImpl
import com.game.core.BaseFragment
import timber.log.Timber
import androidx.recyclerview.widget.GridLayoutManager
import com.game.books.adapter.GRID_COUNT
import com.game.core.AppConstants
import com.game.core.SpacesItemDecoration
import com.game.core.model.ModuleInstallRequest
import kotlinx.android.synthetic.main.fragment_books.*

class BooksFragment : BaseFragment() {

    private lateinit var binding: FragmentBooksBinding
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        booksViewModel = BooksViewModel(BooksRepositoryImpl(), context!!)

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_books, container, false
            )

        binding.booksViewModel = booksViewModel
        initBooks(binding)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        arguments?.let {
            val safeArgs = BooksFragmentArgs.fromBundle(it)
            Timber.d(safeArgs.categoryId.toString())
            booksViewModel.searchByCategoryId(safeArgs.categoryId)
        }
    }

    private fun init() {
        booksViewModel.showInstallPanel.observe(binding.lifecycleOwner!!, Observer {
            binding.showInstallPanel = it
            if(it){
                installModuleAnimation.playAnimation()
            }
        })
        booksViewModel.launchModuleRequest.observe(binding.lifecycleOwner!!, Observer {
            currentInstallModuleStatus(it)
        })
    }

    private fun initBooks(binding: FragmentBooksBinding) {
        binding.viewBooks.apply {
            setHasFixedSize(false)
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.books_space)))
            layoutManager = GridLayoutManager(activity, GRID_COUNT)
            adapter = BooksAdapter(
                booksViewModel,
                this@BooksFragment,
                BookItemClickListener()
            )
        }
    }

    private fun currentInstallModuleStatus(currentRequest: ModuleInstallRequest) {
        when (currentRequest.currentStatus) {
            ModuleInstallRequest.InstallModuleStatus.INSTALLING -> onInstalling(currentRequest)
            ModuleInstallRequest.InstallModuleStatus.LOADING_MODULE -> updateProgressText(
                getString(
                    R.string.loading_module
                )
            )
            ModuleInstallRequest.InstallModuleStatus.ALREADY_INSTALLED -> onAlreadyInstalled(
                currentRequest
            )
            ModuleInstallRequest.InstallModuleStatus.DOWNLOADING -> updateProgressText(getString(R.string.downloading))
            ModuleInstallRequest.InstallModuleStatus.INSTALLED -> onInstalled(currentRequest)
            else -> updateProgressText(getString(R.string.unkown))
        }
    }

    private fun onInstalled(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.installed))
        onSuccessfulLoad(currentRequest.moduleName, currentRequest.modulePath)
    }

    private fun onInstalling(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.installing))
        binding.maxProgress = currentRequest.maxProgress
        binding.currentProgress = currentRequest.currentProgress
    }

    private fun onAlreadyInstalled(currentRequest: ModuleInstallRequest) {
        updateProgressText(getString(R.string.already_installed))
        onSuccessfulLoad(currentRequest.moduleName, currentRequest.modulePath)
    }

    private fun updateProgressText(text: String) {
        binding.currentTextProgress.text = text
    }

    private fun onSuccessfulLoad(moduleName: String, launchPath: String) {
        when (moduleName) {
            AppConstants.SEARCH_MODULE -> {
                launchActivity(launchPath)
            }
        }
    }

    private fun launchActivity(className: String) {
        val intent = Intent().setClassName(com.game.bfinder.BuildConfig.APPLICATION_ID, className)
        startActivity(intent)
    }

    private inner class BookItemClickListener :
        BooksAdapter.ItemClickListener<BooksViewHolder> {
        override fun onItemClick(holder: BooksViewHolder) {
            Timber.d(holder.binding.book!!.title)
        }
    }

    override fun onResume() {
        booksViewModel.registerModuleInstallListener()
        super.onResume()
    }

    override fun onPause() {
        booksViewModel.removeModuleInstallListener()
        super.onPause()
    }
}