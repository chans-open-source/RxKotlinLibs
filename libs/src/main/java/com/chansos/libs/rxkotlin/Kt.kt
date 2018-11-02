package com.chansos.libs.rxkotlin

import com.chansos.libs.rxkotlin.classes.BaseActivity
import com.chansos.libs.rxkotlin.classes.BaseFragment
import com.chansos.libs.rxkotlin.helper.*
import com.chansos.libs.rxkotlin.support.AppHelper
import com.chansos.libs.rxkotlin.support.AppManager
import com.chansos.libs.rxkotlin.support.RxRequest

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class Kt {
    companion object {
        /**
         * 接口操作
         * */
        val Request: RxRequest by lazy {
            RxRequest()
        }
        /**
         * 应用管理
         * */
        val App: AppManager by lazy {
            AppManager()
        }
        /**
         * 公用工具类
         * */
        private val Helper: AppHelper by lazy {
            AppHelper()
        }
        /**
         * UI操作
         * */
        val UI: UIHelper by lazy { Helper.uiHelper }
        /**
         * SharedPreferences操作
         * */
        val SharedPreferences: SharedPreferencesHelper by lazy { Helper.sharedPreferencesHelper }
        /**
         * 权限操作
         * */
        val Permission: PermissionHelper by lazy { Helper.permissionHelper }
        /**
         * 广播操作
         * */
        val Broadcast: BroadcastHelper by lazy { Helper.broadcastHelper }
        /**
         * Handler操作
         * */
        val Handler: HandlerHelper by lazy { Helper.handlerHelper }
        /**
         * 图片操作
         * */
        val Image: ImageLoader by lazy { Helper.imageLoader }
    }

    /**
     * 接口观察者
     * */
    open class Observer<T> : com.chansos.libs.rxkotlin.request.RxObserver<T> {
        constructor(self: BaseActivity?) : super(self)
        constructor(self: BaseFragment?) : super(self)
        constructor(self: BaseActivity?, isShowLoading: Boolean) : super(self, isShowLoading)
        constructor(self: BaseFragment?, isShowLoading: Boolean) : super(self, isShowLoading)
    }

}
