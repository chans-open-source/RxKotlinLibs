package com.chansos.libs.rxkotlin.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Message
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.chansos.libs.rxkotlin.AppManager
import com.chansos.libs.rxkotlin.anno.LayoutResId
import com.chansos.libs.rxkotlin.handler.HandlerHelper
import com.chansos.libs.rxkotlin.handler.OnHandlerMessage
import java.util.concurrent.ConcurrentHashMap

@SuppressLint("ShowToast")
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class UISupport : OnHandlerMessage {
    companion object {
        private const val HIDE_LOADING_DELAY = 300L
        private const val HIDE_LOADING = 0x1
        internal const val DEFAULT_LOADING_MESSAGE = "请稍等..."
    }

    init {
        HandlerHelper.create(this, this)
    }

    private val toast: Toast by lazy {
        Toast.makeText(UIHelper.getContext(), "", Toast.LENGTH_SHORT)
    }
    private val loadingDialogMapper: ConcurrentHashMap<Int, DialogConfig> by lazy {
        ConcurrentHashMap<Int, DialogConfig>()
    }

    fun showToast(message: CharSequence): Toast? {
        toast.run {
            setText(message)
            show()
        }
        return toast
    }

    fun showToast(@StringRes strResId: Int): Toast? = showToast(AppManager.getResources().getString(strResId))

    fun showLoading(activity: Activity, message: String = DEFAULT_LOADING_MESSAGE): MaterialDialog = showLoadingByContext(activity, message)

    fun showLoading(fragment: Fragment, message: String = DEFAULT_LOADING_MESSAGE): MaterialDialog = showLoading(fragment.activity!!, message)

    fun removeLoadingDialog(activity: Activity) {
        val hash = activity.hashCode()
        hideLoading(activity)
        loadingDialogMapper.remove(hash)
    }

    fun removeLoadingDialog(fragment: Fragment) = removeLoadingDialog(fragment.activity!!)

    private fun getDialogByContext(ctx: Context): DialogConfig? {
        return loadingDialogMapper[ctx.hashCode()]
    }

    private fun showLoadingByContext(ctx: Context, message: String): MaterialDialog {
        val config = getDialogByContext(ctx) ?: DialogConfig(this, ctx)
        loadingDialogMapper[ctx.hashCode()] = config.show(message)
        return config.dialog
    }

    fun hideLoading(ctx: Context) = getDialogByContext(ctx)?.dismiss()

    fun hideLoading(dialog: MaterialDialog) = HandlerHelper.sendMessage(this, HIDE_LOADING, dialog)

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

    fun bindClick(listener: View.OnClickListener, vararg views: View) = bindEvent(listener, null, *views)

    fun bindLongClick(listener: View.OnLongClickListener, vararg views: View) = bindEvent(null, listener, *views)

    fun getLayoutResId(clazz: Class<*>): Int = clazz.getAnnotation(LayoutResId::class.java)?.id
            ?: 0x0

    fun quickTo(c: Class<*>, a: Activity = AppManager.last()) = a.startActivity(normalIntent(c))

    fun quickTo(c: Class<*>, f: Fragment) = f.startActivity(normalIntent(c))

    fun normalIntent(c: Class<*>): Intent = Intent().setClass(UIHelper.getContext(), c)

    fun quickToForResult(c: Class<*>, requestCode: Int, a: Activity = AppManager.last()) = a.startActivityForResult(normalIntent(c), requestCode)

    fun quickToForResult(c: Class<*>, requestCode: Int, f: Fragment) = f.startActivityForResult(normalIntent(c), requestCode)

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
            dialog.setContent(message)
            dialog.show()
            time = now()
            return this
        }

        fun dismiss(): DialogConfig {
            if ((now() - time) >= HIDE_LOADING_DELAY) {
                HandlerHelper.sendMessage(obj, HIDE_LOADING, dialog)
            } else {
                HandlerHelper.sendMessageDelayed(obj, HIDE_LOADING, dialog, HIDE_LOADING_DELAY)
            }
            return this
        }
    }
}
