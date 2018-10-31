package com.chansos.libs.rxkotlin.rx.support

/**
 * 添加域名
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Domain(val value: String = "/")