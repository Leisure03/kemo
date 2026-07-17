package com.hzr.kemo.repository

import com.hzr.kemo.model.DrinkListEntity

interface IDrinkListRepository {
    suspend fun getDrinkList(): Result<List<DrinkListEntity>>
}