package com.chansos.libs.rxkotlin.handler

import android.os.Handler
import android.os.Message
import java.util.concurrent.ConcurrentHashMap

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class HandlerSupport {
    private val handlerMapper: ConcurrentHashMap<Int, Handler> by lazy {
        ConcurrentHashMap<Int, Handler>()
    }

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

    fun what(obj: Any, what: Int): Boolean = create(obj, null).sendEmptyMessage(what)

    fun whatDelayed(obj: Any, what: Int, delay: Long): Boolean = create(obj, null).sendEmptyMessageDelayed(what, delay)

    fun whatAtTime(obj: Any, what: Int, uptime: Long): Boolean = create(obj, null).sendEmptyMessageAtTime(what, uptime)

    fun send(obj: Any, msg: Message): Boolean = create(obj, null).sendMessage(msg)

    fun sendDelayed(obj: Any, msg: Message, delay: Long): Boolean = create(obj, null).sendMessageDelayed(msg, delay)

    fun sendAtTime(obj: Any, msg: Message, uptime: Long): Boolean = create(obj, null).sendMessageAtTime(msg, uptime)

    fun sendAtFirst(obj: Any, msg: Message): Boolean = create(obj, null).sendMessageAtFrontOfQueue(msg)

    fun obtainMessage(obj: Any): Message = create(obj, null).obtainMessage()

    fun obtainMessage(obj: Any, what: Int): Message = create(obj, null).obtainMessage(what)

    fun obtainMessage(obj: Any, what: Int, msgObj: Any): Message = create(obj, null).obtainMessage(what, msgObj)

    fun obtainMessage(obj: Any, what: Int, msgArg1: Int, msgArg2: Int): Message = create(obj, null).obtainMessage(what, msgArg1, msgArg2)

    fun obtainMessage(obj: Any, what: Int, msgObj: Any, msgArg1: Int, msgArg2: Int): Message = create(obj, null).obtainMessage(what, msgArg1, msgArg2, msgObj)

    fun remove(obj: Any, what: Int) = create(obj, null).removeMessages(what)

    fun destory(obj: Any) = handlerMapper.remove(obj.hashCode())
}