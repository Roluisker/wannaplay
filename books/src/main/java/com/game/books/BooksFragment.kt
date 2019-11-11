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

        initBooks(binding)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val safeArgs = BooksFragmentArgs.fromBundle(it)
            Timber.d(safeArgs.categoryId.toString())
        }
    }

    private fun initBooks(binding: FragmentBooksBinding) {
        binding.viewBooks.apply {
            setHasFixedSize(true)
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