/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.request.okhttp

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * OkHttp客户端实例
 * */
class OkHttpClient {
    companion object {
         val instance: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(RequestFixInterceptor())
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build()
    }
}