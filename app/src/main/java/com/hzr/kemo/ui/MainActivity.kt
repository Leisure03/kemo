package com.hzr.kemo.ui

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hzr.kemo.BaseActivity
import com.hzr.kemo.adapter.CarouselAdapter
import com.hzr.kemo.databinding.ActivityMainBinding
import com.hzr.kemo.logd
import com.hzr.kemo.model.BannerEntity

import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val carouselAdapter by lazy { CarouselAdapter(items = mutableListOf()) }

    // private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // 必须第一行
        super.onCreate(savedInstanceState)

    }
    override fun initView() {
        logd("MainActivity initView: 初始化轮播图基础样式")
        val banner = binding.bannerDrink
        banner.apply {
            adapter = carouselAdapter
            //添加banner生命周期观察者,让banner自己掌握生命周期
            addBannerLifecycleObserver(this@MainActivity)
            indicator = CircleIndicator(this@MainActivity)
            // 将指示器稍微往上抬一点，使其垂直居中在图片底部留出的 28dp 空地中
            setIndicatorMargins(IndicatorConfig.Margins(0,0,0,8))

            // 注意：真实场景下，千万别在 initView 里直接写死假列表了！把它移交给后面的 observeFlow
            setBannerGalleryEffect(10,10)
        }
    }

    override fun initListeners() {

    }

    override fun observeFlow() {
        logd("MainActivity observeFlow: 开始监听真实网络轮播图数据")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 1. 真实架构下，这里你一般会通过 viewModel.bannerFlow.collect { list -> ... } 来收集网络结果
                // 2. 为了让你现在的代码能跑通测试，我用协程模拟一个“从真实网络请求2秒后返回结果”的过程：


                val realNetworkDataFromApi = listOf(
                    BannerEntity(id = 1, imgSrc = "https://picsum.photos/id/102/600/400"),
                    BannerEntity(id = 2, imgSrc = "https://picsum.photos/id/103/600/400"),
                    BannerEntity(id = 3, imgSrc = "https://picsum.photos/id/104/600/400"),
                    BannerEntity(id = 4, imgSrc = "https://picsum.photos/id/106/600/400")
                )

                logd("MainActivity observeFlow: 成功从真实接口拉取到数据，立刻向 Adapter 喂数据！")
                // 核心关键点：只要这里一执行 setDatas，界面上的空白 Banner 就会瞬间被真实数据点亮！
                carouselAdapter.setDatas(realNetworkDataFromApi)
            }
        }
    }



    private fun eatFood(){

    }
}