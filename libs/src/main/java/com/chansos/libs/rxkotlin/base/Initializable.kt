/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.base

import com.chansos.libs.rxkotlin.AppHelper

/**
 * 可初始化的
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
internal interface Initializable {
    /**
     * 初始化
     * */
    fun initialize()

    /**
     * 获取LayoutId
     * */
    fun getLayoutResId(): Int = AppHelper.UI.getLayoutResId(this.javaClass)
}