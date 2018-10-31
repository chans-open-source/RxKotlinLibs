package com.chansos.libs.rxkotlin.handler

import android.os.Message

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
interface OnHandlerMessage {
    fun onMessage(msg: Message): Boolean
}