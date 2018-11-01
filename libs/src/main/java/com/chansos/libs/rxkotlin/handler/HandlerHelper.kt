package com.chansos.libs.rxkotlin.handler

import android.os.Handler
import android.os.Message
import java.util.concurrent.ConcurrentHashMap

/**
 * Handler操作工具
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class HandlerHelper internal constructor() {
    private val handlerMapper: ConcurrentHashMap<Int, Handler> by lazy {
        ConcurrentHashMap<Int, Handler>()
    }

    /**
     * 为某个对象创建Handler实例
     *
     * @param obj 操作对象
     * @param onHandlerMessage OnHandlerMessage事件实例
     *
     * @return Handler实例
     * */
    fun create(obj: Any, onHandlerMessage: OnHandlerMessage?): Handler {
        val hash = obj.hashCode()
        var handler = handlerMapper[hash]
        if (handler == null && onHandlerMessage != null) {
            handler = Handler(Handler.Callback { msg: Message ->
                onHandlerMessage.onMessage(msg)
            })
            handlerMapper[hash] = handler
        }
        return handler!!
    }

    /**
     * 发送一个空消息
     *
     * @param obj 操作对象
     * @param what 消息类型
     *
     * @return 是否发送成功
     * */
    fun what(obj: Any, what: Int): Boolean = create(obj, null).sendEmptyMessage(what)

    /**
     * 延时发送一个空消息
     *
     * @param obj 操作对象
     * @param what 消息类型
     * @param delay 延时（毫秒）
     *
     * @return 是否发送成功
     * */
    fun whatDelayed(obj: Any, what: Int, delay: Long): Boolean = create(obj, null).sendEmptyMessageDelayed(what, delay)

    /**
     * 数毫秒后发送一个空消息
     *
     * @param obj 操作对象
     * @param what 消息类型
     * @param uptime（毫秒）
     *
     * @return 是否发送成功
     * */
    fun whatAtTime(obj: Any, what: Int, uptime: Long): Boolean = create(obj, null).sendEmptyMessageAtTime(what, uptime)

    /**
     * 发送一个消息
     *
     * @param obj 操作对象
     * @param msg 消息
     *
     * @return 是否发送成功
     * */
    fun send(obj: Any, msg: Message): Boolean = create(obj, null).sendMessage(msg)

    /**
     * 延时发送一个消息
     *
     * @param obj 操作对象
     * @param msg 消息
     * @param delay 延时（毫秒）
     *
     * @return 是否发送成功
     * */
    fun sendDelayed(obj: Any, msg: Message, delay: Long): Boolean = create(obj, null).sendMessageDelayed(msg, delay)

    /**
     * 数毫秒后发送一个消息
     *
     * @param obj 操作对象
     * @param msg 消息
     * @param uptime（毫秒）
     *
     * @return 是否发送成功
     * */
    fun sendAtTime(obj: Any, msg: Message, uptime: Long): Boolean = create(obj, null).sendMessageAtTime(msg, uptime)

    /**
     * 最高优先级发送一个消息
     *
     * @param obj 操作对象
     * @param msg 消息
     *
     * @return 是否发送成功
     * */
    fun sendAtFirst(obj: Any, msg: Message): Boolean = create(obj, null).sendMessageAtFrontOfQueue(msg)

    /**
     * 移除某类消息
     *
     * @param obj 操作对象
     * @param what 消息类型
     * */
    fun remove(obj: Any, what: Int) = create(obj, null).removeMessages(what)

    /**
     * 摧毁Handler
     *
     * @param obj 操作对象
     * */
    fun destory(obj: Any) = handlerMapper.remove(obj.hashCode())

    /**
     * 从消息池中获取消息实例
     *
     * @param obj 操作对象
     *
     * @return 消息实例
     * */
    fun obtainMessage(obj: Any): Message = create(obj, null).obtainMessage()

    /**
     * 从消息池中获取消息实例
     *
     * @param obj 操作对象
     * @param what 赋值给消息实例的msg.what
     *
     * @return 消息实例
     * */
    fun obtainMessage(obj: Any, what: Int): Message = create(obj, null).obtainMessage(what)

    /**
     * 从消息池中获取消息实例
     *
     * @param obj 操作对象
     * @param what 赋值给消息实例的msg.what
     * @param msgObj 赋值给消息实例的msg.obj
     *
     * @return 消息实例
     * */
    fun obtainMessage(obj: Any, what: Int, msgObj: Any): Message = create(obj, null).obtainMessage(what, msgObj)

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
    fun obtainMessage(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Message =
        create(obj, null).obtainMessage(what, msgArg1, msgArg2)

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
    fun obtainMessage(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Message =
        create(obj, null).obtainMessage(what, msgArg1, msgArg2, msgObj)

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
    fun sendMessage(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Boolean =
        send(obj, obtainMessage(obj, what, msgArg1, msgArg2))

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
    fun sendMessage(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Boolean =
        send(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2))

    /**
     * 从消息池中获取消息实例
     *
     * @param obj 操作对象
     * @param what 赋值给消息实例的msg.what
     * @param delay 延时（毫秒）
     *
     * @return 发送结果
     * */
    fun sendMessageDelayed(obj: Any, what: Int, delay: Long): Boolean =
        sendDelayed(obj, obtainMessage(obj, what), delay)

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
    fun sendMessageDelayed(obj: Any, what: Int, msgObj: Any, delay: Long): Boolean =
        sendDelayed(obj, obtainMessage(obj, what, msgObj), delay)

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
    fun sendMessageDelayed(obj: Any, what: Int, msgArg1: Int, msgArg2: Int, delay: Long): Boolean =
        sendDelayed(obj, obtainMessage(obj, what, msgArg1, msgArg2), delay)

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
    fun sendMessageDelayed(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int, delay: Long): Boolean =
        sendDelayed(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2), delay)

    /**
     * 从消息池中获取消息实例
     *
     * @param obj 操作对象
     * @param what 赋值给消息实例的msg.what
     * @param uptime （毫秒）
     *
     * @return 发送结果
     * */
    fun sendMessageAtTime(obj: Any, what: Int, uptime: Long): Boolean =
        sendAtTime(obj, obtainMessage(obj, what), uptime)

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
    fun sendMessageAtTime(obj: Any, what: Int, msgObj: Any, uptime: Long): Boolean =
        sendAtTime(obj, obtainMessage(obj, what, msgObj), uptime)

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
    fun sendMessageAtTime(obj: Any, what: Int, msgArg1: Int, msgArg2: Int, uptime: Long): Boolean =
        sendAtTime(obj, obtainMessage(obj, what, msgArg1, msgArg2), uptime)

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
    fun sendMessageAtTime(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int, uptime: Long): Boolean =
        sendAtTime(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2), uptime)

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
    fun sendMessageAtFirst(obj: Any, what: Int, msgObj: Any): Boolean =
        sendAtFirst(obj, obtainMessage(obj, what, msgObj))

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
    fun sendMessageAtFirst(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Boolean =
        sendAtFirst(obj, obtainMessage(obj, what, msgArg1, msgArg2))

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
    fun sendMessageAtFirst(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Boolean =
        sendAtFirst(obj, obtainMessage(obj, what, msgObj, msgArg1, msgArg2))

}