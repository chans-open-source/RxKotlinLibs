package com.chansos.libs.rxkotlin.annotations

/**
 * 配置Activity的基础属性
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class PageOptions(val orientation: Int = -0x4, val theme: Int = -0x4, val title: String = "", val titleResId: Int = -0x4)
