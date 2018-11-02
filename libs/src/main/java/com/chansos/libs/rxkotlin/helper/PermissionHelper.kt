package com.chansos.libs.rxkotlin.helper

import android.app.Activity
import android.support.v4.app.ActivityCompat
import com.chansos.libs.rxkotlin.Kt

/**
 * 权限管理工具
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class PermissionHelper internal constructor() {
    companion object {
        const val REQUEST_CODE = 0x400
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
    fun request(activity: Activity, permission: String) = request(activity, Array(1) { permission })

    /**
     * 请求授权某个权限
     *
     * @param activity Activity实例
     * @param permissions 权限数组
     * */
    fun request(activity: Activity, permissions: Array<String>) {
        ActivityCompat.requestPermissions(
            activity, permissions,
            REQUEST_CODE
        )
    }
}