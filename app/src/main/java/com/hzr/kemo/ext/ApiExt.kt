package com.hzr.kemo.ext

import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.model.DrinkListResponse

fun DrinkListResponse.unWarpToEntity(): List<DrinkListEntity>{
    if(code!= 200){
        throw RuntimeException("请求异常")
    }
    return data.map {
        it
    }
}