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
        binding.btnWatermelon.setOnClickListener {
            val num = Random.nextInt(2)
            Timber.tag("tag").d(num.toString())
            binding.imgWatermelon.apply {
                when(num){
                    0 -> this.setImageResource(R.mipmap.bad_watermelon)
                    1 -> this.setImageResource(R.mipmap.good_watermelon)
                }
            }
        }
    }
    override fun observeFlow() {}
}
