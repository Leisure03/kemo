package com.hzr.kemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ViewBindingUtil
import com.hzr.kemo.ext.logd

open class BaseFragment<VB : ViewBinding> : Fragment() {

    // Fragment 的视图生命周期与实例生命周期不同，需要在此处处理可空性以防内存泄漏
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logd("BaseFragment onCreateView: 实例化 ViewBinding")
        _binding = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        observeFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logd("BaseFragment onDestroyView: 清除 ViewBinding 引用，防止内存泄漏")
        _binding = null
    }

    open fun initView() {}
    open fun initListeners() {}
    open fun observeFlow() {}
}