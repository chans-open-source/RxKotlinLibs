package com.chansos.libs.rxkotlin.interfaces

import android.os.Message

interface OnHandlerMessage {
    fun onMessage(msg: Message): Boolean
}