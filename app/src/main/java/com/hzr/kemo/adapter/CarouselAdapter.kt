package com.hzr.kemo.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hzr.kemo.R
import com.hzr.kemo.bindImage
import com.hzr.kemo.databinding.ItemCarouselBinding
import com.hzr.kemo.logd
import com.hzr.kemo.model.BannerEntity
import com.youth.banner.adapter.BannerAdapter

class CarouselAdapter(
    items: List<BannerEntity>
): BannerAdapter<BannerEntity, CarouselAdapter.CarouselVH>(items) {
    override fun onCreateHolder(
        parent: ViewGroup?,
        viewType: Int
    ): CarouselVH? {
        return CarouselVH(
            ItemCarouselBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
        )
    }

    override fun onBindView(
        holder: CarouselVH?,
        data: BannerEntity?,
        position: Int,
        size: Int
    ) {
        logd("网络请求")
//        holder?.binding?.ivDrink?.bindImage("https://picsum.photos/seed/milktea/400/600", cornerRadius = 45)

        // 核心逻辑：使用当前索引 position 对列表长度取模，安全取出对应位置的图片资源 ID
        holder?.binding?.ivDrink?.bindImage(
            url = data?.imgSrc,
            placeholderRes = R.mipmap.carousel_1, // 加载过程中可以用之前本地的图当作占位符
            cornerRadius = 45
        )

    }



    inner class CarouselVH(val binding: ItemCarouselBinding): RecyclerView.ViewHolder(binding.root)
}