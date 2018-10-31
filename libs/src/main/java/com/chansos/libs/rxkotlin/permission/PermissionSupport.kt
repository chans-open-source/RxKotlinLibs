package com.chansos.libs.rxkotlin.permission

import android.app.Activity
import android.support.v4.app.ActivityCompat
import com.chansos.libs.rxkotlin.AppManager

@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class PermissionSupport {
  companion object {
    const val REQUEST_CODE = 0x400
  }

  fun check(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(AppManager.getContext(), permission) == android.content.pm.PackageManager.PERMISSION_GRANTED
  }

  fun request(activity: Activity, permission: String) = request(activity, Array(1) { permission })

  fun request(activity: Activity, permissions: Array<String>) {
    ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
  }

}