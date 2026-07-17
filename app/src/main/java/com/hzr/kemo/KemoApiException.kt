package com.hzr.kemo

class KemoApiException(val code: Int, override val message: String?): Exception(message)