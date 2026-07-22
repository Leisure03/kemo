package com.hzr.kemo.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hzr.kemo.BaseFragment
import com.hzr.kemo.adapter.CarouselAdapter
import com.hzr.kemo.adapter.DrinkListAdapter
import com.hzr.kemo.databinding.FragmentHomeBinding
import com.hzr.kemo.ext.logd
import com.hzr.kemo.vm.HomeViewModel
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val carouselAdapter by lazy { CarouselAdapter(items = mutableListOf()) }
    private val drinkListAdapter by lazy { DrinkListAdapter() }

    private val viewModel: HomeViewModel by viewModels()

    override fun initView() {
        logd("HomeFragment initView: 初始化轮播图基础样式")
        binding.bannerDrink.apply {
            adapter = carouselAdapter
            // 添加banner生命周期观察者,让banner自己掌握生命周期
            // 注意：在Fragment中必须使用 viewLifecycleOwner
            addBannerLifecycleObserver(viewLifecycleOwner)
            indicator = CircleIndicator(requireContext())
            // 将指示器稍微往上抬一点，使其垂直居中在图片底部留出的 28dp 空地中
            setIndicatorMargins(IndicatorConfig.Margins(0, 0, 0, 8))

            // 注意：真实场景下，千万别在 initView 里直接写死假列表了！把它移交给后面的 observeFlow
            setBannerGalleryEffect(10, 10)
        }

        binding.rvDrinkList.apply {
            adapter = drinkListAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun initListeners() {
        // 预留给首页的其他点击事件监听
    }

    override fun observeFlow() {
        // 在 Fragment 中监听 Flow，必须使用 viewLifecycleOwner.lifecycleScope
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // 分开两个独立收集，互不阻塞
                launch {
                    // 监听饮品列表数据
                    viewModel.uiState.collect { state ->
                        logd("更新主页列表${state.drinkList}")
                        drinkListAdapter.submitList(state.drinkList)
                    }
                }

                launch {
                    viewModel.uiState.collect { state ->
                        logd("更新轮播图数据${state.carousels}")
                        carouselAdapter.setDatas(state.carousels)
                    }
                }
            }
        }
    }
}