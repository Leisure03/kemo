package com.hzr.kemo.repository

import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.model.DrinkListResponse
import javax.inject.Inject
interface IDrinkListRepository {
    suspend fun getDrinkList(): List<DrinkListEntity>
}