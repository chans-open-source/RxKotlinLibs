package com.chansos.libs.rxkotlin.helper

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.support.AppManager

/**
 * 广播管理器
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class BroadcastHelper internal constructor() {

    internal val receiverManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(Kt.App.getContext())
    }

    private fun createIntent(clazz: Class<*>, action: String): Intent {
        val intent = Intent()
        intent.setClass(Kt.App.getContext(), clazz)
        intent.action = action
        return intent
    }

    /**
     * 注册广播接收器
     *
     * @param receiver 广播接收器实例
     *
     * @return 是否注册成功
     * */
    fun register(receiver: BroadcastReceiver, vararg actions: String): Boolean {
        return try {
            val filter = IntentFilter()
            actions.forEach { action -> filter.addAction(action) }
            receiverManager.registerReceiver(receiver, filter)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 注销广播接收器
     *
     * @param receiver 广播接收器实例
     *
     * @return 是否注销成功
     * */
    fun unregister(receiver: BroadcastReceiver): Boolean {
        return try {
            receiverManager.unregisterReceiver(receiver)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 发送广播
     *
     * @param clazz
     * @param action 广播事件
     * */
    fun send(clazz: Class<*>, action: String) {
        receiverManager.sendBroadcast(createIntent(clazz, action))
    }

    /**
     * 同步发送广播
     *
     * @param clazz
     * @param action 广播事件
     * */
    fun sendSync(clazz: Class<*>, action: String) {
        receiverManager.sendBroadcastSync(createIntent(clazz, action))
    }
}