/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.request

import android.app.Activity
import android.support.v4.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.chansos.libs.rxkotlin.Kt
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 接口观察者
 * */
open class RxObserver<T> : Observer<T> {
    private var activity: Activity
    private var isShowLoading: Boolean
    private var loadingDialog: MaterialDialog? = null

    constructor(activity: Activity?, isShowLoading: Boolean) {
        this.activity = activity!!
        this.isShowLoading = isShowLoading
    }

    constructor(fragment: Fragment?, isShowLoading: Boolean) : this(fragment!!.activity!!, isShowLoading)

    constructor(activity: Activity?) : this(activity, true)

    constructor(fragment: Fragment?) : this(fragment, true)

    /**
     * 接口调用时
     * */
    override fun onSubscribe(d: Disposable) {
        if (isShowLoading) {
            showLoading()
        }
    }

    /**
     * 接口调用完成
     * */
    override fun onComplete() {
        if (isShowLoading) {
            hideLoading()
        }
    }

    /**
     * 显示加载提示
     * */
    open fun showLoading() {
        this.loadingDialog = Kt.UI.showLoading(activity)
    }

    /**
     * 隐藏加载提示
     * */
    open fun hideLoading() {
        Kt.UI.hideLoading(activity)
    }

    /**
     * 接口调用异常
     * */
    override fun onError(e: Throwable) {
        hideLoading()
    }

    /**
     * 接口调用成功
     * */
    override fun onNext(t: T) {
    }
}