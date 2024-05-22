package com.mju.deliveryservice.data.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

object CustomLogger {

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private const val TAG = "LOGGER"

    fun d(message: String) {
        Logger.t(TAG).d(message)
    }

    fun i(message: String) {
        Logger.t(TAG).i(message)
    }

    fun w(message: String) {
        Logger.t(TAG).w(message)
    }

    fun e(message: String) {
        Logger.t(TAG).e(message)
    }
}