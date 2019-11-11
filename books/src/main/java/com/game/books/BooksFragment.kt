package com.game.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import com.game.books.adapter.BooksAdapter
import com.game.books.adapter.BooksViewHolder
import com.game.books.databinding.FragmentBooksBinding
import com.game.books.repository.BooksRepositoryImpl
import com.game.core.BaseFragment
import timber.log.Timber
import androidx.recyclerview.widget.GridLayoutManager
import com.game.books.adapter.GRID_COUNT
import com.game.core.SpacesItemDecoration

class BooksFragment : BaseFragment() {

    private lateinit var binding: FragmentBooksBinding
    private val booksViewModel = BooksViewModel(BooksRepositoryImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        arguments?.let {
            val safeArgs = BooksFragmentArgs.fromBundle(it)
            Timber.d(safeArgs.categoryId.toString())
            booksViewModel.searchByCategoryId(safeArgs.categoryId)
        }
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

    private inner class BookItemClickListener :
        BooksAdapter.ItemClickListener<BooksViewHolder> {
        override fun onItemClick(holder: BooksViewHolder) {
            Timber.d(holder.binding.book!!.title)
        }
    }
}