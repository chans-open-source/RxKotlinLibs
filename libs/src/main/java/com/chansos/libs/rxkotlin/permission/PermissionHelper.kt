package com.chansos.libs.rxkotlin.permission

import android.app.Activity

/**
 * 权限管理工具
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class PermissionHelper {
    companion object {
        private val instance: PermissionSupport by lazy {
            PermissionSupport()
        }

        /**
         * 检测某个权限是否已授权
         * @param permission 权限
         *
         * @return 检测结果
         * */
        fun check(permission: String): Boolean = instance.check(permission)

        /**
         * 请求授权某个权限
         *
         * @param activity Activity实例
         * @param permission 权限
         * */
        fun request(activity: Activity, permission: String) = instance.request(activity, permission)

        /**
         * 请求授权某个权限
         *
         * @param activity Activity实例
         * @param permissions 权限数组
         * */
        fun request(activity: Activity, permissions: Array<String>) = instance.request(activity, permissions)
    }
}