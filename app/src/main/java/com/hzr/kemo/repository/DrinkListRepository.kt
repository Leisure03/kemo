package com.hzr.kemo.repository

import com.hzr.kemo.api.DrinkListApiService
import com.hzr.kemo.ext.unwrapToEntity
import com.hzr.kemo.model.DrinkListEntity
import javax.inject.Inject

class DrinkListRepository @Inject constructor(
    private val apiService: DrinkListApiService
): IDrinkListRepository{
    override suspend fun getDrinkList(): Result<List<DrinkListEntity>> {
        return runCatching { apiService.getDrinkList() }.unwrapToEntity()
    }
}