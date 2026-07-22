package com.hzr.kemo.ui.activity

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.hzr.kemo.BaseActivity
import com.hzr.kemo.R
import com.hzr.kemo.databinding.ActivityMainBinding
import com.hzr.kemo.ext.logd
import com.hzr.kemo.ui.fragment.CartFragment
import com.hzr.kemo.ui.fragment.HomeFragment
import com.hzr.kemo.ui.fragment.MineFragment
import com.hzr.kemo.ui.fragment.OrderFragment
import com.hzr.kemo.ui.fragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val cartFragment = CartFragment()
    // 预留其他 Fragment
     private val orderFragment = OrderFragment()
     private val mineFragment = MineFragment()

    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // 必须第一行
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        logd("MainActivity initView: 初始化 Fragment 容器并加载首页")
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment, "Home")
            .commit()
    }

    override fun initListeners() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    logd("切换到首页")
                    switchFragment(homeFragment)
                    true
                }
                R.id.menu_search ->{
                    logd("切换到搜索页")
                    switchFragment(searchFragment)
                    true
                }
                R.id.menu_cart ->{
                    logd("切换到搜索页")
                    switchFragment(cartFragment)
                    true
                }
                R.id.menu_order ->{
                    logd("切换到订单页")
                    switchFragment(orderFragment)
                    true
                }
                 R.id.menu_mine -> {
                     logd("切换到我的页")
                     switchFragment(mineFragment)
                     true
                 }
                else -> false
            }
        }
    }

    override fun observeFlow() {
        // 主 Activity 的全局状态监听（如有需要可以放在这里）
    }

    // 使用 hide/show 切换，保留 Fragment 的视图状态，避免重复 inflate 引发性能损耗
    private fun switchFragment(targetFragment: Fragment) {
        if (activeFragment == targetFragment) return

        val transaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            transaction.hide(activeFragment)
                .add(R.id.fragment_container, targetFragment)
                .commit()
        } else {
            transaction.hide(activeFragment)
                .show(targetFragment)
                .commit()
        }
        activeFragment = targetFragment
    }
}