package com.hzr.kemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 这里写App全局初始化逻辑
        if (BuildConfig.DEBUG) {
            // 调试：完整打印所有日志
            Timber.plant(Timber.DebugTree())
        } else {
            // 生产：仅打印错误日志
            Timber.plant(ReleaseTree())
        }
    }
}