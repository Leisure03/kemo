package com.hzr.kemo.ext

import timber.log.Timber

// ===== debug 日志 logd =====
// 普通调试日志
fun logd(msg: String) {
    Timber.d(msg)
}

// 带占位符格式化
fun logd(format: String, vararg args: Any?) {
    Timber.d(format, *args)
}

// 自定义TAG + 日志
fun logd(tag: String, msg: String) {
    Timber.tag(tag).d(msg)
}

// 异常堆栈打印
fun logd(t: Throwable, msg: String = "") {
    Timber.d(t, msg)
}

// ===== error 错误日志 loge =====
// 普通错误文本
fun loge(msg: String) {
    Timber.e(msg)
}

// 格式化错误文本
fun loge(format: String, vararg args: Any?) {
    Timber.e(format, *args)
}

// 指定TAG打印错误
fun loge(tag: String, msg: String) {
    Timber.tag(tag).e(msg)
}

// 打印异常堆栈（最常用，排查崩溃）
fun loge(t: Throwable, msg: String = "") {
    Timber.e(t, msg)
}
// info 信息日志
fun logi(msg: String) = Timber.i(msg)
fun logi(format: String, vararg args: Any?) = Timber.i(format, *args)
fun logi(tag: String, msg: String) = Timber.tag(tag).i(msg)
fun logi(t: Throwable, msg: String = "") = Timber.i(t, msg)

// warn 警告日志
fun logw(msg: String) = Timber.w(msg)
fun logw(format: String, vararg args: Any?) = Timber.w(format, *args)
fun logw(tag: String, msg: String) = Timber.tag(tag).w(msg)
fun logw(t: Throwable, msg: String = "") = Timber.w(t, msg)
