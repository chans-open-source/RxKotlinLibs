/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.support

import com.chansos.libs.rxkotlin.classes.BaseActivity
import com.chansos.libs.rxkotlin.classes.BaseFragment
import com.chansos.libs.rxkotlin.request.service.Service
import com.chansos.libs.rxkotlin.request.service.ServiceHelper

/**
 * 接口操作
 * */
class RxRequest internal constructor() {
    /**
     * 创建网络服务实例
     * */
    fun <T> create(view: BaseFragment): Service<T> = Service.create(view)

    /**
     * 创建网络服务实例
     * */
    fun <T> create(view: BaseActivity): Service<T> = Service.create(view)

    /**
     * 创建接口实例
     * */
    fun <T> api(api: Class<T>): T = ServiceHelper.create(api)
}