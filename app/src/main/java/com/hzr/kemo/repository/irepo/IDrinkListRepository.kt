package com.hzr.kemo.repository.irepo

import com.hzr.kemo.model.BannerEntity
import com.hzr.kemo.model.DrinkListEntity

interface IDrinkListRepository {
    /**
     * 获取主页奶茶列表
     */
    suspend fun getDrinkList(): Result<List<DrinkListEntity>>

    /**
     * 获取主页轮播图图片
     */
    suspend fun getCarousePics(): Result<List<BannerEntity>>
}