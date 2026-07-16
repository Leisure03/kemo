package com.hzr.kemo.api

import com.hzr.kemo.model.DrinkListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DrinkListApiService @Inject constructor(
    private val client: HttpClient
){
    suspend fun getDrinkList(): DrinkListResponse {
        return client.get(ApiPath.DRINK_LIST).body()
    }
}