package com.group.pow.search

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.game.core.BaseActivity
import com.group.pow.search.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.lifecycleOwner = this
    }
}