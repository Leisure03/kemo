package com.hzr.kemo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
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
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // 保持左右与底部的系统安全区适配，顶部留出 0dp，让子 Fragment 自由绘制到状态栏下方
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)

            val statusBarHeight = systemBars.top
            logd("setupWindowInsets: 动态应用状态栏高度: ${statusBarHeight}px")

            // 递归查找并更新所有加载的 Fragment 中的 top_guideline
            updateGuidelines(v, statusBarHeight)

            insets
        }
    }

    // 递归遍历视图树，动态更新所有的 top_guideline
    private fun updateGuidelines(view: View, height: Int) {
        if (view is Guideline && view.id == R.id.top_guideline) {
            view.setGuidelineBegin(height)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                updateGuidelines(view.getChildAt(i), height)
            }
        }
    }

    open fun initView(){}
    open fun initListeners() {}
    open fun observeFlow(){}
}