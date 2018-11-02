/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.classes

import com.chansos.libs.rxkotlin.utils.ObjectUtils

/**
 * MVP项目结构的合约接口
 * */
interface BaseContract {
    interface BaseView

    interface BasePresenter {
        /**
         * 自动绑定BaseView
         * */
        fun bind(view: BaseView) = ObjectUtils.inject(this, "view", view)
    }
}