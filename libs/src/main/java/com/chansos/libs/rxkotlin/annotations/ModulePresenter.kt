/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.annotations

import com.chansos.libs.rxkotlin.classes.BaseContract
import kotlin.reflect.KClass

/**
 * 自动装配Presenter
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class ModulePresenter(val path: String = "", val clazz: KClass<out BaseContract.BasePresenter> = BaseContract.BasePresenter::class)
