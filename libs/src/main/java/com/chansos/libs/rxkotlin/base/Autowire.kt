/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.base

import android.app.Activity
import android.text.TextUtils
import com.chansos.libs.rxkotlin.AppManager
import com.chansos.libs.rxkotlin.anno.ModulePresenter
import com.chansos.libs.rxkotlin.anno.PageOptions
import com.chansos.libs.rxkotlin.log.LogUtils
import com.chansos.libs.rxkotlin.obj.ObjectUtils

/**
 * 自动装配
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
internal interface Autowire {
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
        val annotation = clazz.getAnnotation(PageOptions::class.java)
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
        val annotation = this.javaClass.getAnnotation(ModulePresenter::class.java)
        if (annotation != null) {
            var path: String = annotation.path
            val clazz = annotation.clazz
            if (TextUtils.isEmpty(path) && clazz != BaseContract.BasePresenter::class) {
                path = clazz.qualifiedName ?: ""
            }
            if (!TextUtils.isEmpty(path)) {
                try {
                    val constructor = Class.forName(path).getConstructor()
                    val presenter = constructor.newInstance() as BaseContract.BasePresenter?
                    presenter!!.bind(this as BaseContract.BaseView)
                    ObjectUtils.inject(this, "presenter", presenter)
                } catch (e: Exception) {
                    LogUtils.e(e)
                }
            }
        }
    }
}