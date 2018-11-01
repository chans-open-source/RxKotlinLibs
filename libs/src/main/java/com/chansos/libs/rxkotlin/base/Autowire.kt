/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.base

import android.app.Activity
import android.text.TextUtils
import com.chansos.libs.rxkotlin.AppManager
import com.chansos.libs.rxkotlin.anno.AutowirePresent
import com.chansos.libs.rxkotlin.anno.PageDefaultOptions
import com.chansos.libs.rxkotlin.obj.ObjectUtils

/**
 * 自动装配
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
interface Autowire {
    fun autowire() {
        autowirePageDefaultOptions()
        autowirePresenter()
    }

    /**
     * 判断是否为有效参数
     * */
    private fun validOption(value: Int): Boolean {
        return value != -0x4
    }

    /**
     * 自动装配Activity的基础信息
     * */
    fun autowirePageDefaultOptions() {
        val clazz = this.javaClass
        val annotation = clazz.getAnnotation(PageDefaultOptions::class.java)
        if (annotation != null && (this is Activity)) {
            if (validOption(annotation.theme)) {
                this.setTheme(annotation.theme)
            }
            if (validOption(annotation.orientation)) {
                this.requestedOrientation = annotation.orientation
            }
            if (!TextUtils.isEmpty(annotation.title)) {
                this.title = annotation.title
            } else if (validOption(annotation.titleResId)) {
                this.title = AppManager.getContext().getText(annotation.titleResId)
            }
        }
    }

    /**
     * 自动装配Presenter
     * */
    fun autowirePresenter() {
        val clazz = this.javaClass
        val annotation = clazz.getAnnotation(AutowirePresent::class.java)
        if (annotation != null && !TextUtils.isEmpty(annotation.path)) {
            val presenterClass = Class.forName(annotation.path)
            val constructor = presenterClass.getConstructor()
            val presenter = constructor.newInstance() as BaseContract.BasePresenter?
            presenter!!.bind(this as BaseContract.BaseView)
            ObjectUtils.inject(this, "presenter", presenter)
        }
    }
}