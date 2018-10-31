/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.anno

/**
 * 配置Activity,Fragment的ContentViewLayoutId
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class LayoutResId(val id: Int)
