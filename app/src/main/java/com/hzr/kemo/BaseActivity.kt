package com.hzr.kemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ViewBindingUtil
import com.hzr.kemo.ext.logd


open class BaseActivity<VB : ViewBinding>() : AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ViewBindingUtil.inflateWithGeneric(this, layoutInflater)
        setContentView(binding.root)
        setupWindowInsets()
        initView()
        initListeners()
        observeFlow()
    }

    // 抽离为 open 方法，以便特定需要沉浸式全屏组件的子 Activity 能够重写边距逻辑
    open fun setupWindowInsets() {
        logd("BaseActivity setupWindowInsets: 应用全局默认系统栏边距适配")
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    open fun initView(){}
    open fun initListeners() {}
    open fun observeFlow(){}
}