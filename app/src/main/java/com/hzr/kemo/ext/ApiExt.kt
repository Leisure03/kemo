package com.hzr.kemo.ext

import com.hzr.kemo.KemoApiException
import com.hzr.kemo.api.KemoResponse


fun <T> Result<KemoResponse<T>>.unwrapToEntity(): Result<T> {
    return fold(
        onSuccess = { response ->
            //请求成功||服务器返回成功
            logd("服务器响应网络成功")
            if(response.code == 200){
                logd("服务器响应业务成功:${response.code}")
                val data = response.data
                //有数据
                if(data != null){
                    logd("数据不为空，服务器返回数据")
                    Result.success(data)
                } else{
                    logd("数据为空，抛出异常:${response.msg}")
                    Result.failure(NullPointerException("服务器返回数据为空"))
                }
            }else{
                logd("服务器响应业务错误: ${response.code}")
                val msg = response.msg?:"未知业务错误"
                Result.failure(KemoApiException(response.code,msg))
            }
        },
        onFailure = { exception ->
            //网络超时
            loge(exception)
            Result.failure(exception)
        }
    )
}