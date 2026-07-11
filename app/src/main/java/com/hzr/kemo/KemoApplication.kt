package com.hzr.kemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 这里写App全局初始化逻辑
    }
}