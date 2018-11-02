package com.chansos.libs.rxkotlin.interfaces

import android.os.Message

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
interface OnHandlerMessage {
    fun onMessage(msg: Message): Boolean
}