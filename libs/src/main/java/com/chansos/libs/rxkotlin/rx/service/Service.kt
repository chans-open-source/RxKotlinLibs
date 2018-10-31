/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.rx.service

import com.chansos.libs.rxkotlin.rx.support.RxObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.annotations.NonNull

/**
 * 网络服务
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class Service<T> private constructor(private var lifecycleView: Any) {
    private var observable: Observable<T>? = null

    companion object {
        fun <T> create(lifecycleView: Any): Service<T> {
            return Service(lifecycleView)
        }
    }

    /**
     * 配置接口信息
     * */
    fun api(@NonNull observable: Observable<T>): Obs<T> {
        this.observable = observable
        return Obs(this)
    }

    class Obs<T>(private var service: Service<T>) {
        /**
         * 配置观察者回调
         * */
        fun obs(@NonNull observer: RxObserver<T>): Observable<T>? {
            val observable = this.service.observable
            observable!!.compose(ServiceHelper.thread())
                    .compose(ServiceHelper.bindToLifecycle(this.service.lifecycleView))
                    .subscribe(observer as Observer<in T>)
            return observable
        }
    }

}
