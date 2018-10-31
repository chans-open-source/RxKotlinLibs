/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.rx.service

import com.chansos.libs.rxkotlin.rx.okhttp.OkHttpClient
import com.chansos.libs.rxkotlin.rx.support.BaseUrl
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.RxActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory

/**
 * Api创建器
 */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class ServiceHelper {
    companion object {
        private var mDomain = ""

        fun setDomain(domain: String) {
            mDomain = domain
        }

        /**
         * 创建代理类
         */
        fun <A> create(api: Class<A>): A {
            return Retrofit.Builder()
                .client(OkHttpClient.instance)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(getBaseUrl(api))
                .build()
                .create(api)
        }

        /**
         * 获取根Url
         */
        private fun getBaseUrl(api: Class<*>): String {
            val present = api.isAnnotationPresent(BaseUrl::class.java)
            val annotation = if (present) api.getAnnotation(BaseUrl::class.java) else null
            return "$mDomain${annotation?.value ?: ""}"
        }

        /**
         * io线程
         * */
        fun <T> thread(): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        /**
         * 绑定生命周期
         * */
        fun <T> bindToLifecycle(view: Any): LifecycleTransformer<T> {
            return (view as? com.trello.rxlifecycle2.components.support.RxFragment)?.bindUntilEvent(FragmentEvent.DESTROY)
                ?: (view as? com.trello.rxlifecycle2.components.support.RxDialogFragment)?.bindUntilEvent(FragmentEvent.DESTROY)
                ?: (view as? com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment)?.bindUntilEvent(
                    FragmentEvent.DESTROY
                )
                ?: (view as? com.trello.rxlifecycle2.components.RxFragment)?.bindUntilEvent(FragmentEvent.DESTROY)
                ?: (view as? com.trello.rxlifecycle2.components.RxDialogFragment)?.bindUntilEvent(FragmentEvent.DESTROY)
                ?: (view as? com.trello.rxlifecycle2.components.RxPreferenceFragment)?.bindUntilEvent(FragmentEvent.DESTROY)
                ?: (view as? RxAppCompatActivity)?.bindUntilEvent(ActivityEvent.DESTROY)
                ?: (view as? RxActivity)?.bindUntilEvent(ActivityEvent.DESTROY)
                ?: (view as? RxFragmentActivity)?.bindUntilEvent(ActivityEvent.DESTROY)
                ?: throw IllegalArgumentException("view isn't activity or fragment")
        }
    }

}
