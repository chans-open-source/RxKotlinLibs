/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.utils

import android.util.Log

/**
 * 日志工具
 * */
class LogUtils {
    companion object {
        /**
         * 日志标识
         * */
        private val TAG: String by lazy {
            LogUtils::class.java.simpleName
        }

        /**
         * 日志类型
         * */
        enum class Type {
            Info, Error, Debug;
        }

        /**
         * 输出信息日志
         *
         * @param message 内容
         * */
        fun i(message: String) {
            log(
                message,
                Type.Info
            )
        }

        /**
         * 输出错误日志
         *
         * @param err 错误内容
         * */
        fun e(err: Throwable) {
            err.printStackTrace()
            e(err.message!!)
        }

        /**
         * 输出错误日志
         *
         * @param message 错误内容
         * */
        fun e(message: String) {
            log(
                message,
                Type.Error
            )
        }

        /**
         * 输出调试日志
         *
         * @param message 内容
         * */
        fun d(message: String) {
            log(
                message,
                Type.Debug
            )
        }

        /**
         * 封装日志输出
         *
         * @param message 内容
         * @param type 输出类型
         * */
        private fun log(message: String, type: Type) {
            when (type) {
                Type.Info -> {
                    Log.i(TAG, message)
                }
                Type.Error -> {
                    Log.e(TAG, message)
                }
                Type.Debug -> {
                    Log.d(TAG, message)
                }
            }
        }

        /**
         * 在终端打印
         *
         * @param message 内容
         * */
        fun p(message: String) {
            println("$TAG: $message")
        }
    }
}

