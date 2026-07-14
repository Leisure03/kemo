package com.hzr.kemo

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// 统一封装 Glide 加载，利用默认参数覆盖日常 90% 的场景，避免重复写链式样板代码
fun ImageView.bindImage(
    url: Any?,
    placeholderRes: Int = 0,
    errorRes: Int = 0,
    cornerRadius: Int = 0
) {
    logd("bindImage: 开始触发加载, url=$url, cornerRadius=$cornerRadius")

    val requestBuilder = Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    if (placeholderRes != 0) {
        requestBuilder.placeholder(placeholderRes)
    }

    if (errorRes != 0) {
        requestBuilder.error(errorRes)
    }

    // 核心修复点：当 ImageView 配合 centerCrop 使用时，必须让 Glide 组合应用 CenterCrop 和 RoundedCorners
    // 否则 ImageView 自身的 CenterCrop 放大裁剪会将圆角挤出屏幕边缘
    if (cornerRadius > 0) {
        requestBuilder.transform(CenterCrop(), RoundedCorners(cornerRadius))
    }

    requestBuilder.listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            logd("bindImage: 图片加载失败, url=$model, error=${e?.message}")
            // 返回 false 让 Glide 继续处理 errorRes 的渲染
            return false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            logd("bindImage: 图片加载成功, url=$model, 数据源=${dataSource.name}")
            // 返回 false 让 Glide 继续将 Drawable 绘制到 ImageView 上
            return false
        }
    }).into(this)
}