package com.chansos.libs.rxkotlin.annotations

/**
 * 添加域名
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Domain(val value: String = "/")