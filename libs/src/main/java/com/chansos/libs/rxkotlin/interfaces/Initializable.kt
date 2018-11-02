/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.interfaces

import com.chansos.libs.rxkotlin.Kt

/**
 * 可初始化的
 * */
internal interface Initializable {
    /**
     * 初始化
     * */
    fun initialize()

    /**
     * 获取LayoutId
     * */
    fun getLayoutResId(): Int = Kt.UI.getLayoutResId(this.javaClass)
}