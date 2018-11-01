package com.chansos.libs.rxkotlin

import com.chansos.libs.rxkotlin.broadcast.BroadcastHelper
import com.chansos.libs.rxkotlin.handler.HandlerHelper
import com.chansos.libs.rxkotlin.image.ImageLoader
import com.chansos.libs.rxkotlin.permission.PermissionHelper
import com.chansos.libs.rxkotlin.sp.SharedPreferencesHelper
import com.chansos.libs.rxkotlin.ui.UIHelper

/**
 * 公用工具类
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class AppHelper {
    companion object {
        /**
         * UI操作
         * */
        val UI: UIHelper by lazy {
            UIHelper()
        }
        /**
         * SharedPreferences操作
         * */
        val SharedPreferences: SharedPreferencesHelper by lazy {
            SharedPreferencesHelper()
        }
        /**
         * 权限操作
         * */
        val Permission: PermissionHelper by lazy {
            PermissionHelper()
        }
        /**
         * 广播操作
         * */
        val Broadcast: BroadcastHelper  by lazy {
            BroadcastHelper()
        }
        /**
         * Handler操作
         * */
        val Handler: HandlerHelper  by lazy {
            HandlerHelper()
        }
        /**
         * 图片操作
         * */
        val Image: ImageLoader by lazy {
            ImageLoader()
        }
    }
}