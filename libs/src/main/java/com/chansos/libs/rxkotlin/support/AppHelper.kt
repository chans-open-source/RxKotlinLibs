package com.chansos.libs.rxkotlin.support

import com.chansos.libs.rxkotlin.helper.BroadcastHelper
import com.chansos.libs.rxkotlin.helper.HandlerHelper
import com.chansos.libs.rxkotlin.helper.ImageLoader
import com.chansos.libs.rxkotlin.helper.PermissionHelper
import com.chansos.libs.rxkotlin.helper.SharedPreferencesHelper
import com.chansos.libs.rxkotlin.helper.UIHelper

/**
 * 公用工具类
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class AppHelper internal constructor() {
    /**
     * UI操作
     * */
    val uiHelper: UIHelper by lazy {
        UIHelper()
    }
    /**
     * SharedPreferences操作
     * */
    val sharedPreferencesHelper: SharedPreferencesHelper by lazy {
        SharedPreferencesHelper()
    }
    /**
     * 权限操作
     * */
    val permissionHelper: PermissionHelper by lazy {
        PermissionHelper()
    }
    /**
     * 广播操作
     * */
    val broadcastHelper: BroadcastHelper  by lazy {
        BroadcastHelper()
    }
    /**
     * Handler操作
     * */
    val handlerHelper: HandlerHelper  by lazy {
        HandlerHelper()
    }
    /**
     * 图片操作
     * */
    val imageLoader: ImageLoader by lazy {
        ImageLoader()
    }
}