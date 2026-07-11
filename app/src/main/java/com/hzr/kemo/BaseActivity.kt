package com.hzr.kemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ViewBindingUtil

open class BaseActivity<VB : ViewBinding>(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ViewBindingUtil.inflateWithGeneric(this, layoutInflater)

        // 全局统一设置系统栏边距适配，所有子Activity自动生效
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initListeners()
        observeFlow()
    }

    open fun initView(){}
    open fun initListeners() {}
    open fun observeFlow(){}
}
