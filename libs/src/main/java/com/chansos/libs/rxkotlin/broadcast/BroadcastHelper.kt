package com.chansos.libs.rxkotlin.broadcast

import android.content.BroadcastReceiver
import android.support.v4.content.LocalBroadcastManager

/**
 * 广播管理器
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class BroadcastHelper {
    companion object {
        private val instance: BroadcastSupport by lazy {
            BroadcastSupport()
        }

        fun getReceiverManager(): LocalBroadcastManager {
            return instance.receiverManager
        }

        /**
         * 注册广播接收器
         *
         * @param receiver 广播接收器实例
         *
         * @return 是否注册成功
         * */
        fun register(receiver: BroadcastReceiver, vararg actions: String): Boolean = instance.register(receiver, *actions)

        /**
         * 注销广播接收器
         *
         * @param receiver 广播接收器实例
         *
         * @return 是否注销成功
         * */
        fun unregister(receiver: BroadcastReceiver): Boolean = instance.unregister(receiver)

        /**
         * 发送广播
         *
         * @param clazz
         * @param action 广播事件
         * */
        fun send(clazz: Class<*>, action: String) = instance.send(clazz, action)

        /**
         * 同步发送广播
         *
         * @param clazz
         * @param action 广播事件
         * */
        fun sendSync(clazz: Class<*>, action: String) = instance.sendSync(clazz, action)
    }
}