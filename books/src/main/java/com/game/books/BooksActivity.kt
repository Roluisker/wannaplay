package com.game.books

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.game.core.BaseActivity

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_book)
        findNavController(this,  R.id.booksNavigationHost).setGraph(R.navigation.book_navigation, bookBundle())
    }

    private fun bookBundle(): Bundle {
        return when {
            intent != null && intent.extras != null -> BooksActivityArgs.fromBundle(intent?.extras!!).toBundle()
            else -> Bundle()
        }
    }
}