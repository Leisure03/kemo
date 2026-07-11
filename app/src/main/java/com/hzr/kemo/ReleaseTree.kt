package com.hzr.kemo

import android.util.Log
import timber.log.Timber

class ReleaseTree: Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        // 只保留崩溃、错误日志，过滤 debug/info/warn
        if(priority < Log.ERROR) return

        // 这里可以扩展：把错误日志上传Bugly/自定义日志服务器
        val finalTag = tag?:"KEMO_RELEASE"
        Log.println(priority,finalTag,message)
        t?.let {
            Log.e(finalTag, "异常堆栈", it)
        }
    }
}