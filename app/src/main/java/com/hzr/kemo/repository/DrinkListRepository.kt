package com.hzr.kemo.repository

import com.hzr.kemo.api.DrinkListApiService
import com.hzr.kemo.ext.unWarpToEntity
import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.model.DrinkListResponse
import io.ktor.client.HttpClient
import javax.inject.Inject

class DrinkListRepository @Inject constructor(
    private val apiService: DrinkListApiService
): IDrinkListRepository{
    override suspend fun getDrinkList(): List<DrinkListEntity> {
        return apiService.getDrinkList().unWarpToEntity()
    }
}