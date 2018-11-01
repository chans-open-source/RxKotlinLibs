/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.rx.okhttp

import com.chansos.libs.rxkotlin.log.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer

/**
 * OkHttp拦截器
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class RequestFixInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request: Request
        val url = original.url().toString()
        request = setHeader(original)
        //打印请求日志
        LogUtils.d("请求Url:$url")
        if ("POST".equals(original.method(), ignoreCase = true)) {
            val body = getBody(request)
            LogUtils.d("请求参数:$body")
        }
        val method = request.method()
        LogUtils.d("请求方法:$method")
        val headers = request.headers().toString()
        LogUtils.d("请求Header:$headers")
        return chain.proceed(request)
    }

    /**
     * 获取请求body
     * */
    private fun getBody(original: Request): String {
        val bs = Buffer()
        try {
            original.body()!!.writeTo(bs)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bs.readUtf8()
    }

    /**
     * 设置请求头
     * */
    private fun setHeader(original: Request): Request {
        val requestBuilder = original.newBuilder()
//        val reqUrl = original.url().toString()
        return requestBuilder.build()
    }
}