package com.chansos.libs.rxkotlin.crash

import com.chansos.libs.rxkotlin.AppManager

/**
 * 应用崩溃处理器
 * 处理未被捕捉的异常
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class CrashHandler : Thread.UncaughtExceptionHandler {
    /**
     * 默认异常处理器实例
     * */
    private var defaultExceptionHandler: Thread.UncaughtExceptionHandler? = null

    companion object {
        private val instance: CrashHandler by lazy {
            val i = CrashHandler()
            i.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
            i
        }

        /**
         * 初始化应用崩溃处理器
         * */
        fun init() {
            Thread.setDefaultUncaughtExceptionHandler(instance)
        }
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (handleException(e)) {
            /**
             * 退出应用
             * */
            AppManager.exit()
        } else {
            /**
             * 使用默认异常处理器处理未被捕捉的异常
             * */
            defaultExceptionHandler?.uncaughtException(t, e)
        }
    }

    /**
     * 判断异常是否已被处理
     * */
    private fun handleException(e: Throwable?): Boolean {
        if (e == null) {
            return false
        }
        return true
    }

}