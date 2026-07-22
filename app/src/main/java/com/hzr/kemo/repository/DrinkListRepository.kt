package com.hzr.kemo.repository

import com.hzr.kemo.api.DrinkListApiService
import com.hzr.kemo.ext.unwrapToEntity
import com.hzr.kemo.model.BannerEntity
import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.repository.irepo.IDrinkListRepository
import javax.inject.Inject

class DrinkListRepository @Inject constructor(
    private val apiService: DrinkListApiService
): IDrinkListRepository {
    override suspend fun getDrinkList(): Result<List<DrinkListEntity>> {
        return runCatching { apiService.getDrinkList() }.unwrapToEntity()
    }

    override suspend fun getCarousePics(): Result<List<BannerEntity>> {
        return runCatching { apiService.getCarouselList()}.unwrapToEntity()
    }
}