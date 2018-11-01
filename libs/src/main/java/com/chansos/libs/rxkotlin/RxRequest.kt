/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin

import com.chansos.libs.rxkotlin.base.BaseActivity
import com.chansos.libs.rxkotlin.base.BaseFragment
import com.chansos.libs.rxkotlin.rx.service.Service
import com.chansos.libs.rxkotlin.rx.service.ServiceHelper

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class RxRequest {
    companion object {
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

    /**
     * 接口观察者
     * */
    open class RxObserver<T> : com.chansos.libs.rxkotlin.rx.support.RxObserver<T> {
        constructor(self: BaseActivity?) : super(self)
        constructor(self: BaseFragment?) : super(self)
        constructor(self: BaseActivity?, isShowLoading: Boolean) : super(self, isShowLoading)
        constructor(self: BaseFragment?, isShowLoading: Boolean) : super(self, isShowLoading)
    }

}