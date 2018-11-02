/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.support

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import com.chansos.libs.rxkotlin.utils.CrashHandler
import com.chansos.libs.rxkotlin.utils.LogUtils
import java.util.*

/**
 * 应用管理器
 * 记录与操作Activity实例
 * */
class AppManager internal constructor() {
    /**
     * 应用根Context实例
     * */
    private lateinit var mContext: Context

    private var instance: S = S()

    /**
     * 初始化应用管理起
     *
     * @param context 应用根Context实例
     * */
    fun init(context: Context) {
        /**
         * 初始化应用崩溃事件处理工具
         * */
        CrashHandler.init()
        mContext = context
    }

    /**
     * 获取应用根Context实例
     *
     * @return 应用根Context实例
     * */
    fun getContext(): Context {
        return mContext
    }

    /**
     * 获取应用Resources实例
     *
     * @return 应用Resources实例
     * */
    fun getResources(): Resources {
        return getContext().resources
    }

    /**
     * 存储Activity实例
     *
     * @param activity Activity实例
     *
     * @return Activity实例
     * */
    fun add(activity: Activity): Activity {
        var act: Activity?
        synchronized(instance) {
            act = instance.push(activity)
            instance.status()
        }
        return act!!
    }

    /**
     * 获取栈顶的Activity实例
     *
     * @return Activity实例
     * */
    fun last(): Activity {
        return instance.lastElement()
    }

    /**
     * 结束并关闭Activity实例
     *
     * @param activity Activity实例
     * */
    fun finish(activity: Activity) {
        if (!activity.isFinishing) {
            synchronized(instance) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
        }
        remove(activity)
    }

    /**
     * 从Activity栈中移除Activity实例
     *
     * @param activity Activity实例
     * */
    fun remove(activity: Activity) {
        synchronized(instance) {
            instance.remove(activity)
            instance.status()
        }
    }

    /**
     * 退出应用
     * */
    fun exit() {
        while (instance.size > 0) {
            finish(instance.pop())
        }
        LogUtils.i("Exit at ${System.currentTimeMillis()}")
    }


    inner class S internal constructor() : Stack<Activity>() {

        /**
         * 标记Activity栈的最后高度
         * */
        private var lastSize: Int = 0

        /**
         * 输出Activity栈的信息
         * */
        fun status() {
            LogUtils.i("${AppManager::class.java.simpleName}: ${this}")
            if (lastSize != this.size && this.size > 0 && (get(0) as? AppCompatActivity)?.supportActionBar != null) {
                (get(0) as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            lastSize = this.size
        }
    }
}
