package com.game.books

import android.os.Bundle
import com.game.core.BaseActivity

class BooksActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_book)
    }
}