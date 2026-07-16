package com.hzr.kemo.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LoggingConfig
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    val client: HttpClient by lazy {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true //忽略 JSON 中存在但 Kotlin 类中未定义的键
                    prettyPrint = true //格式化打印输出美观、带换行缩进的 JSON
                    isLenient = true //宽松解析模式，允许不标准的 JSON 格式
                    encodeDefaults = true //序列化时包含默认值
                })
            }
            engine {
                requestTimeout = 15000 //15秒超时
            }
        }
    }
}