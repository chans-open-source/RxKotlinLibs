/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.interfaces

import android.view.View
import com.chansos.libs.rxkotlin.Kt

/**
 * 可点击
 * */
internal interface Clickable : View.OnClickListener, View.OnLongClickListener {
    /**
     * 绑定点击事件
     * */
    fun bindClick(vararg views: View) = Kt.UI.bindClick(this, *views)

    /**
     * 绑定长按事件
     * */
    fun bindLongClick(vararg views: View) = Kt.UI.bindLongClick(this, *views)

    override fun onClick(view: View?) {
    }

    override fun onLongClick(view: View?): Boolean {
        return false
    }
}