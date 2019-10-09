package com.game.meet

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.game.core.BaseActivity
import com.sc.convert.R
import com.sc.convert.databinding.ActivityBookListBinding

class BookListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBookListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_book_list)
        binding.lifecycleOwner = this
    }

}