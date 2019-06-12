/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.interfaces

import android.app.Activity
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.annotations.Autowire
import com.chansos.libs.rxkotlin.annotations.PageOptions
import com.chansos.libs.rxkotlin.classes.BaseContract
import com.chansos.libs.rxkotlin.utils.LogUtils
import com.chansos.libs.rxkotlin.utils.ObjectUtils
import java.lang.reflect.Field

/**
 * 自动装配
 * */
internal interface Autowire {
    fun autowire() {
        autowirePageDefaultOptions()
        autowireFields()
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
            val title = annotation.title
            if (title.isNotEmpty() && title.isNotBlank()) {
                this.title = annotation.title
            } else if (validOption(annotation.titleResId)) {
                this.title = Kt.App.getContext().getText(annotation.titleResId)
            }
        }
    }

    private fun isInvalidPath(path: String?): Boolean {
        return path == null || path.isEmpty() || path.isBlank()
    }

    /**
     * 自动装配类中添加了Autowire注解的declaredFields
     * */
    fun autowireFields() {
        val fields = this.javaClass.declaredFields
        fields.forEach { field: Field? ->
            run {
                val annotation = field?.getAnnotation(Autowire::class.java)
                if (annotation != null) {
                    try {
                        val constructor = Class.forName(field.type.name).getConstructor()
                        val instance = constructor.newInstance()
                        if (instance is BaseContract.BasePresenter) {
                            instance.bind(this as BaseContract.BaseView)
                        }
                        ObjectUtils.inject(this, field.name, instance)
                    } catch (e: Exception) {
                        LogUtils.e(e)
                    }
                }
            }
        }
    }
}