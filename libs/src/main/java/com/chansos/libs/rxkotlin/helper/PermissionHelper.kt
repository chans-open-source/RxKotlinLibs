package com.chansos.libs.rxkotlin.helper

import android.support.v4.app.ActivityCompat
import com.chansos.libs.rxkotlin.Kt
import com.chansos.libs.rxkotlin.classes.BaseActivity
import com.chansos.libs.rxkotlin.utils.LogUtils

/**
 * 权限管理工具
 * */
@Suppress("UNCHECKED_CAST")
class PermissionHelper internal constructor() {
    companion object {
        const val REQUEST_CODE = 0x400
    }

    private fun checkCallback(c: Class<out BaseActivity>): Boolean {
        return try {
            c.getDeclaredMethod(
                "onRequestPermissionsResult",
                Int::class.javaPrimitiveType,
                Array<String>::class.java,
                IntArray::class.java
            )
            true
        } catch (e: Exception) {
            if (c == BaseActivity::class.java) {
                false
            } else {
                checkCallback(c.superclass as Class<out BaseActivity>)
            }
        }
    }

    /**
     * 检测某个权限是否已授权
     * @param permission 权限
     *
     * @return 检测结果
     * */
    fun check(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            Kt.App.getContext(),
            permission
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    /**
     * 请求授权某个权限
     *
     * @param activity Activity实例
     * @param permission 权限
     * */
    fun request(activity: BaseActivity, permission: String) = request(activity, Array(1) { permission })

    /**
     * 请求授权某个权限
     *
     * @param activity Activity实例
     * @param permissions 权限数组
     * */
    fun request(activity: BaseActivity, permissions: Array<String>) {
        val c = activity::class.java
        if (checkCallback(c)) {
            ActivityCompat.requestPermissions(
                activity, permissions,
                REQUEST_CODE
            )
        } else {
            LogUtils.e("PermissionHelper.request: 请先重写Activity.onRequestPermissionsResult方法。 (${c.name})")
        }
    }
}