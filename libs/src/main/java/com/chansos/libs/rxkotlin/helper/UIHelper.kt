/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Message
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.annotations.PageLayoutId
import com.chansos.libs.rxkotlin.interfaces.OnHandlerMessage
import java.util.concurrent.ConcurrentHashMap

/**
 * UI操作工具
 * 用于控制Layout中的UI，以及Toast、LoadingDialog、Dialog等
 * */
class UIHelper internal constructor() : OnHandlerMessage {
    companion object {
        private const val HIDE_LOADING_DELAY = 300L
        private const val HIDE_LOADING = 0x1
        internal const val DEFAULT_LOADING_MESSAGE = "请稍等..."
    }

    init {
        Kt.Handler.create(this, this)
    }

    private val toast: Toast by lazy {
        Toast.makeText(Kt.App.getContext(), "", Toast.LENGTH_SHORT)
    }
    private val loadingDialogMapper: ConcurrentHashMap<Int, DialogConfig> by lazy {
        ConcurrentHashMap<Int, DialogConfig>()
    }

    private fun getDialogByContext(ctx: Context): DialogConfig? {
        return loadingDialogMapper[ctx.hashCode()]
    }

    private fun showLoadingByContext(ctx: Context, message: String): MaterialDialog {
        val config = getDialogByContext(ctx) ?: DialogConfig(this, ctx)
        loadingDialogMapper[ctx.hashCode()] = config.show(message)
        return config.dialog
    }

    private fun bindEvent(onClick: View.OnClickListener?, onLongClick: View.OnLongClickListener?, vararg views: View) {
        views.forEach { view ->
            if (onClick != null) {
                view.setOnClickListener { onClick.onClick(view) }
            }
            if (onLongClick != null) {
                view.setOnLongClickListener { onLongClick.onLongClick(view) }
            }
        }
    }

    /**
     * 显示Toast
     *
     * @param message 要显示的内容
     *
     * @return Toast实例
     * */
    fun showToast(message: CharSequence): Toast? {
        toast.run {
            setText(message)
            show()
        }
        return toast
    }

    /**
     * 显示Toast
     *
     * @param strResId 要显示的内容
     *
     * @return Toast实例
     * */
    fun showToast(@StringRes strResId: Int): Toast? = showToast(Kt.App.getResources().getString(strResId))

    /**
     * 显示加载对话框
     *
     * @param activity Activity实例
     * @param message 加载提示
     *
     * @return 加载对话框实例
     * */
    fun showLoading(activity: Activity, message: String = DEFAULT_LOADING_MESSAGE): MaterialDialog? =
        showLoadingByContext(activity, message)

    /**
     * 显示加载对话框
     *
     * @param fragment Fragment实例
     * @param message 加载提示
     *
     * @return 加载对话框实例
     * */
    fun showLoading(fragment: Fragment, message: String = DEFAULT_LOADING_MESSAGE): MaterialDialog? =
        showLoading(fragment.activity!!, message)

    /**
     * 移除某个Activity实例中的加载对话框实例
     *
     * @param activity Activity实例
     * */
    fun removeLoadingDialog(activity: Activity) {
        val hash = activity.hashCode()
        hideLoading(activity)
        loadingDialogMapper.remove(hash)
    }

    /**
     * 移除某个Fragment实例中的加载对话框实例
     *
     * @param fragment Fragment实例
     * */
    fun removeLoadingDialog(fragment: Fragment) = removeLoadingDialog(fragment.activity!!)

    /**
     * 隐藏加载对话框
     *
     * @param ctx 上下文实例
     * */
    fun hideLoading(ctx: Context) = getDialogByContext(ctx)?.dismiss()

    /**
     * 隐藏加载对话框
     *
     * @param dialog 对话框实例
     * */
    fun hideLoading(dialog: MaterialDialog) = Kt.Handler.sendMessage(
        this,
        HIDE_LOADING, dialog
    )

    /**
     * 绑定点击事件
     *
     * @param listener View.OnClickListener实例
     * @param views
     * */
    fun bindClick(listener: View.OnClickListener, vararg views: View) = bindEvent(listener, null, *views)

    /**
     * 绑定长按事件
     *
     * @param listener View.OnLongClickListener实例
     * @param views
     * */
    fun bindLongClick(listener: View.OnLongClickListener, vararg views: View) = bindEvent(null, listener, *views)

    /**
     * 获取类中LayoutResId注解的值
     *
     * @param clazz 添加了LayoutResId注解的类
     *
     * @return LayoutResId注解的值
     * */
    fun getLayoutResId(clazz: Class<*>): Int = clazz.getAnnotation(PageLayoutId::class.java)?.id
        ?: 0x0

    /**
     * 导航至某个Activity
     *
     * @param c 目标Activity
     * */
    fun quickTo(c: Class<*>, a: Activity = Kt.App.last()) = a.startActivity(normalIntent(c))

    /**
     * 导航至某个Activity
     *
     * @param c 目标Activity
     * */
    fun quickTo(c: Class<*>, f: Fragment) = f.startActivity(normalIntent(c))

    /**
     * 快速创建Intent实例
     *
     * @param c 目标Activity
     * */
    fun normalIntent(c: Class<*>): Intent = Intent().setClass(Kt.App.getContext(), c)

    /**
     * 使用ForResult方式导航至某个Activity
     *
     * @param c 目标Activity
     * @param requestCode 请求码
     * */
    fun quickToForResult(c: Class<*>, requestCode: Int, a: Activity = Kt.App.last()) =
        a.startActivityForResult(normalIntent(c), requestCode)

    /**
     * 使用ForResult方式导航至某个Activity
     *
     * @param c 目标Activity
     * @param requestCode 请求码
     * */
    fun quickToForResult(c: Class<*>, requestCode: Int, f: Fragment) =
        f.startActivityForResult(normalIntent(c), requestCode)

    /**
     * 在某个ViewGroup中获取View实例
     *
     * @param layout ViewGroup实例
     * @param viewId viewId
     *
     * @return View实例
     * */
    fun <T : View?> get(layout: View, viewId: Int): T = layout.findViewById<T>(viewId)


    override fun onMessage(msg: Message): Boolean {
        return when (msg.what) {
            HIDE_LOADING -> {
                try {
                    (msg.obj as MaterialDialog).dismiss()
                } catch (e: Exception) {
                }
                true
            }
            else -> {
                false
            }
        }
    }


    inner class DialogConfig(private val obj: Any, private val ctx: Context) {
        internal val dialog: MaterialDialog by lazy {
            MaterialDialog
                .Builder(ctx)
                .cancelable(false)
                .progress(true, 0)
                .show()
        }
        private var time: Long = now()

        private fun now() = System.currentTimeMillis()

        fun show(message: String): DialogConfig {
            dialog.run {
                setContent(message)
                show()
            }
            time = now()
            return this
        }

        fun dismiss(): DialogConfig {
            if ((now() - time) >= HIDE_LOADING_DELAY) {
                Kt.Handler.sendMessage(
                    obj,
                    HIDE_LOADING, dialog
                )
            } else {
                Kt.Handler.sendMessageDelayed(
                    obj,
                    HIDE_LOADING, dialog,
                    HIDE_LOADING_DELAY
                )
            }
            return this
        }
    }
}
