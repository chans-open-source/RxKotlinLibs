package com.chansos.rx.kotlin.libs

import com.chansos.libs.rxkotlin.annotations.Autowire
import com.chansos.libs.rxkotlin.annotations.PageLayoutId
import com.chansos.libs.rxkotlin.classes.BaseActivity
import com.chansos.libs.rxkotlin.utils.LogUtils

@PageLayoutId(R.layout.activity_main)
class MainActivity : BaseActivity() {

    @Autowire
    private lateinit var presenter: MainPresenter

    override fun initialize() {
        LogUtils.i(presenter.toString())
    }


}
