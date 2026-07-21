package com.hzr.kemo.api

import com.hzr.kemo.model.BannerEntity
import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.model.KemoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DrinkListApiService @Inject constructor(
    private val client: HttpClient
){
    suspend fun getDrinkList(): KemoResponse<List<DrinkListEntity>> {
        return client.get(ApiPath.DRINK_LIST).body()
    }

    suspend fun getCarouselList(): KemoResponse<List<BannerEntity>>{
        return client.get(ApiPath.CAROUSEL_LIST).body()
    }

}