/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.base

import android.os.Bundle
import android.view.Menu
import com.chansos.libs.rxkotlin.AppManager
import com.chansos.libs.rxkotlin.handler.HandlerHelper
import com.chansos.libs.rxkotlin.log.LogUtils
import com.chansos.libs.rxkotlin.obj.ObjectUtils
import com.chansos.libs.rxkotlin.ui.UIHelper
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Activity的基类
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
abstract class BaseActivity : RxAppCompatActivity(), Clickable, Initializable, Autowire {
    lateinit var self: BaseActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        self = this
        autowire()
        setContentView(getLayoutResId())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        AppManager.add(self)
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.remove(self)
        UIHelper.removeLoadingDialog(self)
        HandlerHelper.destory(self)
        ObjectUtils.destory(self)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        if (menu != null) {
            if (menu.javaClass.simpleName == "MenuBuilder") {
                try {
                    val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", Boolean::class.java)
                    method.isAccessible = true
                    method.invoke(menu, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return super.onMenuOpened(featureId, menu)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        LogUtils.d("${this.javaClass.simpleName}:onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.d("${this.javaClass.simpleName}:onRestoreInstanceState")
    }
}