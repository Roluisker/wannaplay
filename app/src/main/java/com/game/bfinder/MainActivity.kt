package com.game.bfinder

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.game.bfinder.databinding.ActivityMainBinding
import com.game.core.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }
}
