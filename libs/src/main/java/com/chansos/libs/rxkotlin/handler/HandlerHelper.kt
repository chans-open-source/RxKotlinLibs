package com.chansos.libs.rxkotlin.handler

import android.os.Handler
import android.os.Message

/**
 * Handler操作工具
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class HandlerHelper {
    companion object {
        private val instance = HandlerSupport()

        /**
         * 为某个对象创建Handler实例
         *
         * @param obj 操作对象
         * @param onHandlerMessage OnHandlerMessage事件实例
         *
         * @return Handler实例
         * */
        fun create(obj: Any, onHandlerMessage: OnHandlerMessage?): Handler? = instance.create(obj, onHandlerMessage)

        /**
         * 发送一个空消息
         *
         * @param obj 操作对象
         * @param what 消息类型
         *
         * @return 是否发送成功
         * */
        fun what(obj: Any, what: Int): Boolean = instance.what(obj, what)

        /**
         * 延时发送一个空消息
         *
         * @param obj 操作对象
         * @param what 消息类型
         * @param delay 延时（毫秒）
         *
         * @return 是否发送成功
         * */
        fun whatDelayed(obj: Any, what: Int, delay: Long): Boolean = instance.whatDelayed(obj, what, delay)

        /**
         * 数毫秒后发送一个空消息
         *
         * @param obj 操作对象
         * @param what 消息类型
         * @param uptime（毫秒）
         *
         * @return 是否发送成功
         * */
        fun whatAtTime(obj: Any, what: Int, uptime: Long): Boolean = instance.whatAtTime(obj, what, uptime)

        /**
         * 发送一个消息
         *
         * @param obj 操作对象
         * @param msg 消息
         *
         * @return 是否发送成功
         * */
        fun send(obj: Any, msg: Message): Boolean = instance.send(obj, msg)

        /**
         * 延时发送一个消息
         *
         * @param obj 操作对象
         * @param msg 消息
         * @param delay 延时（毫秒）
         *
         * @return 是否发送成功
         * */
        fun sendDelayed(obj: Any, msg: Message, delay: Long): Boolean = instance.sendDelayed(obj, msg, delay)

        /**
         * 数毫秒后发送一个消息
         *
         * @param obj 操作对象
         * @param msg 消息
         * @param uptime（毫秒）
         *
         * @return 是否发送成功
         * */
        fun sendAtTime(obj: Any, msg: Message, uptime: Long): Boolean = instance.sendAtTime(obj, msg, uptime)

        /**
         * 最高优先级发送一个消息
         *
         * @param obj 操作对象
         * @param msg 消息
         *
         * @return 是否发送成功
         * */
        fun sendAtFirst(obj: Any, msg: Message): Boolean = instance.sendAtFirst(obj, msg)

        /**
         * 移除某类消息
         *
         * @param obj 操作对象
         * @param what 消息类型
         * */
        fun remove(obj: Any, what: Int) = instance.remove(obj, what)

        /**
         * 摧毁Handler
         *
         * @param obj 操作对象
         * */
        fun destory(obj: Any) = instance.destory(obj)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         *
         * @return 消息实例
         * */
        fun obtainMessage(obj: Any): Message = instance.obtainMessage(obj)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         *
         * @return 消息实例
         * */
        fun obtainMessage(obj: Any, what: Int): Message = instance.obtainMessage(obj, what)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         *
         * @return 消息实例
         * */
        fun obtainMessage(obj: Any, what: Int, msgObj: Any): Message = instance.obtainMessage(obj, what, msgObj)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 消息实例
         * */
        fun obtainMessage(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Message = instance.obtainMessage(obj, what, msgArg1, msgArg2)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 消息实例
         * */
        fun obtainMessage(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Message = instance.obtainMessage(obj, what, msgObj, msgArg1, msgArg2)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         *
         * @return 发送结果
         * */
        fun sendMessage(obj: Any, what: Int): Boolean = send(obj, obtainMessage(obj, what))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         *
         * @return 发送结果
         * */
        fun sendMessage(obj: Any, what: Int, msgObj: Any): Boolean = send(obj, obtainMessage(obj, what, msgObj))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 发送结果
         * */
        fun sendMessage(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Boolean = send(obj, obtainMessage(obj, what, msgArg1, msgArg2))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 发送结果
         * */
        fun sendMessage(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Boolean = send(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param delay 延时（毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageDelayed(obj: Any, what: Int, delay: Long): Boolean = sendDelayed(obj, obtainMessage(obj, what), delay)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param delay 延时（毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageDelayed(obj: Any, what: Int, msgObj: Any, delay: Long): Boolean = sendDelayed(obj, obtainMessage(obj, what, msgObj), delay)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         * @param delay 延时（毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageDelayed(obj: Any, what: Int, msgArg1: Int, msgArg2: Int, delay: Long): Boolean = sendDelayed(obj, obtainMessage(obj, what, msgArg1, msgArg2), delay)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         * @param delay 延时（毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageDelayed(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int, delay: Long): Boolean = sendDelayed(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2), delay)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param uptime （毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageAtTime(obj: Any, what: Int, uptime: Long): Boolean = sendAtTime(obj, obtainMessage(obj, what), uptime)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param uptime （毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageAtTime(obj: Any, what: Int, msgObj: Any, uptime: Long): Boolean = sendAtTime(obj, obtainMessage(obj, what, msgObj), uptime)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         * @param uptime （毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageAtTime(obj: Any, what: Int, msgArg1: Int, msgArg2: Int, uptime: Long): Boolean = sendAtTime(obj, obtainMessage(obj, what, msgArg1, msgArg2), uptime)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         * @param uptime （毫秒）
         *
         * @return 发送结果
         * */
        fun sendMessageAtTime(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int, uptime: Long): Boolean = sendAtTime(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2), uptime)

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         *
         * @return 发送结果
         * */
        fun sendMessageAtFirst(obj: Any, what: Int): Boolean = sendAtFirst(obj, obtainMessage(obj, what))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         *
         * @return 发送结果
         * */
        fun sendMessageAtFirst(obj: Any, what: Int, msgObj: Any): Boolean = sendAtFirst(obj, obtainMessage(obj, what, msgObj))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 发送结果
         * */
        fun sendMessageAtFirst(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Boolean = sendAtFirst(obj, obtainMessage(obj, what, msgArg1, msgArg2))

        /**
         * 从消息池中获取消息实例
         *
         * @param obj 操作对象
         * @param what 赋值给消息实例的msg.what
         * @param msgObj 赋值给消息实例的msg.obj
         * @param msgArg1 赋值给消息实例的msg.arg1
         * @param msgArg2 赋值给消息实例的msg.arg2
         *
         * @return 发送结果
         * */
        fun sendMessageAtFirst(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Boolean = sendAtFirst(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2))

    }
}