/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.classes

import android.os.Bundle

/**
 * 用于ViewPager的Fragment
 * 加入了懒加载功能
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
abstract class BaseViewPagerFragment : BaseFragment() {
    private var isPrepared = false
    private var isRequested = false
    private var visible = false
    private var pause = true
    override fun initialize() {
        isPrepared = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onInitialize()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        visible = userVisibleHint
        if (isPrepared) {
            if (visible) {
                if (pause) {
                    pause = false
                    onResume()
                }
            } else {
                pause = true
                onPause()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isPrepared && visible) {
            if (isRequested) {
                onSecondTime()
            } else {
                isRequested = true
                onFirstTime()
            }
        }
    }

    /**
     * 初始化事件
     * */
    protected abstract fun onInitialize()

    /**
     * 首次加载事件
     * */
    protected open fun onFirstTime() {}

    /**
     * 二次加载事件
     * */
    protected open fun onSecondTime() {}
}