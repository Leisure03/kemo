package com.hzr.kemo

import android.os.Bundle
import android.util.Log
import com.hzr.kemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun initView() {

    }

    override fun initListeners() {

    }
    override fun observeFlow() {}
}
