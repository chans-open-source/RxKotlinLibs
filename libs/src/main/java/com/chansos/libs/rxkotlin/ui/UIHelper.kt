/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.chansos.libs.rxkotlin.AppManager

/**
 * UI操作工具
 * 用于控制Layout中的UI，以及Toast、LoadingDialog、Dialog等
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
open class UIHelper {
    companion object {
        private val instance: UISupport by lazy {
            UISupport()
        }

        /**
         * 显示Toast
         *
         * @param message 要显示的内容
         *
         * @return Toast实例
         * */
        fun showToast(message: CharSequence): Toast? = instance.showToast(message)

        /**
         * 显示Toast
         *
         * @param strResId 要显示的内容
         *
         * @return Toast实例
         * */
        fun showToast(@StringRes strResId: Int): Toast? = instance.showToast(strResId)

        /**
         * 显示加载对话框
         *
         * @param activity Activity实例
         * @param message 加载提示
         *
         * @return 加载对话框实例
         * */
        fun showLoading(activity: Activity, message: String = UISupport.DEFAULT_LOADING_MESSAGE): MaterialDialog? = instance.showLoading(activity, message)

        /**
         * 显示加载对话框
         *
         * @param fragment Fragment实例
         * @param message 加载提示
         *
         * @return 加载对话框实例
         * */
        fun showLoading(fragment: Fragment, message: String = UISupport.DEFAULT_LOADING_MESSAGE): MaterialDialog? = instance.showLoading(fragment, message)

        /**
         * 移除某个Activity实例中的加载对话框实例
         *
         * @param activity Activity实例
         * */
        fun removeLoadingDialog(activity: Activity) = instance.removeLoadingDialog(activity)

        /**
         * 移除某个Fragment实例中的加载对话框实例
         *
         * @param fragment Fragment实例
         * */
        fun removeLoadingDialog(fragment: Fragment) = instance.removeLoadingDialog(fragment)

        /**
         * 隐藏加载对话框
         *
         * @param ctx 上下文实例
         * */
        fun hideLoading(ctx: Context) = instance.hideLoading(ctx)

        /**
         * 隐藏加载对话框
         *
         * @param dialog 对话框实例
         * */
        fun hideLoading(dialog: MaterialDialog) = instance.hideLoading(dialog)

        /**
         * 绑定点击事件
         *
         * @param listener View.OnClickListener实例
         * @param views
         * */
        fun bindClick(listener: View.OnClickListener, vararg views: View) = instance.bindClick(listener, * views)

        /**
         * 绑定长按事件
         *
         * @param listener View.OnLongClickListener实例
         * @param views
         * */
        fun bindLongClick(listener: View.OnLongClickListener, vararg views: View) = instance.bindLongClick(listener, *views)

        /**
         * 获取类中LayoutResId注解的值
         *
         * @param clazz 添加了LayoutResId注解的类
         *
         * @return LayoutResId注解的值
         * */
        fun getLayoutResId(clazz: Class<*>): Int = instance.getLayoutResId(clazz)

        /**
         * 导航至某个Activity
         *
         * @param c 目标Activity
         * */
        fun quickTo(c: Class<*>, a: Activity = AppManager.last()) = instance.quickTo(c, a)

        /**
         * 导航至某个Activity
         *
         * @param c 目标Activity
         * */
        fun quickTo(c: Class<*>, f: Fragment) = instance.quickTo(c, f)

        /**
         * 快速创建Intent实例
         *
         * @param c 目标Activity
         * */
        fun normalIntent(c: Class<*>): Intent = instance.normalIntent(c)

        /**
         * 使用ForResult方式导航至某个Activity
         *
         * @param c 目标Activity
         * @param requestCode 请求码
         * */
        fun quickToForResult(c: Class<*>, requestCode: Int, a: Activity = AppManager.last()) = instance.quickToForResult(c, requestCode, a)

        /**
         * 使用ForResult方式导航至某个Activity
         *
         * @param c 目标Activity
         * @param requestCode 请求码
         * */
        fun quickToForResult(c: Class<*>, requestCode: Int, f: Fragment) = instance.quickToForResult(c, requestCode, f)

        /**
         * 在某个ViewGroup中获取View实例
         *
         * @param layout ViewGroup实例
         * @param viewId viewId
         *
         * @return View实例
         * */
        fun <T : View?> get(layout: View, viewId: Int): T = instance.get<T>(layout, viewId)
    }
}
