package com.game.books

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import com.game.core.AppConstants
import com.game.core.BaseActivity

const val DEFAULT_TITLE = "Books Finder"

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_book)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_arrow))
        findNavController(this,  R.id.booksNavigationHost).setGraph(R.navigation.book_navigation, bookBundle())
    }

    private fun bookBundle(): Bundle {
        return when {
            intent != null && intent.extras != null -> handleArg()
            else -> {
                title(-1)
                Bundle()
            }
        }
    }

    private fun handleArg(): Bundle {
        val booksArg = BooksActivityArgs.fromBundle(intent?.extras!!)
        title(booksArg.categoryId)
        return booksArg.toBundle()
    }

    private fun title(categoryId: Int) {
        var currentTitle = DEFAULT_TITLE

        when {
            AppConstants.ART_FICTION_CATEGORY != categoryId -> if (AppConstants.SCIENCE_FICTION_CATEGORY == categoryId){
                currentTitle = "Science Fiction"
            }
            else -> currentTitle = "Art"
        }
        title = currentTitle
    }
}