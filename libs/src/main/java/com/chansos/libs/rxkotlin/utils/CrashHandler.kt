package com.chansos.libs.rxkotlin.utils

import com.chansos.libs.rxkotlin.Kt

/**
 * 应用崩溃处理器
 * 处理未被捕捉的异常
 * */
internal class CrashHandler : Thread.UncaughtExceptionHandler {
    /**
     * 默认异常处理器实例
     * */
    private val defaultExceptionHandler: Thread.UncaughtExceptionHandler by lazy {
        Thread.getDefaultUncaughtExceptionHandler()
    }

    companion object {
        private val instance: CrashHandler by lazy {
            CrashHandler()
        }

        /**
         * 初始化应用崩溃处理器
         * */
        fun init() = Thread.setDefaultUncaughtExceptionHandler(instance)
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (handleException(e)) {
            /**
             * 退出应用
             * */
            Kt.App.exit()
        } else {
            /**
             * 使用默认异常处理器处理未被捕捉的异常
             * */
            defaultExceptionHandler.uncaughtException(t, e)
        }
    }

    /**
     * 判断异常是否已被处理
     * */
    private fun handleException(e: Throwable?): Boolean = e != null

}