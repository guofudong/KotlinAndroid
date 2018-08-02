package com.gfd.common.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gfd.common.common.AppManager

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:11
 * @Email：878749089@qq.com
 * @descriptio：所有Activity的基类
 */
open abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.instance.addActivity(this)
        initOperate()
    }

    /**
     * 初始化操作，在onCreate中调用
     */
    open fun initOperate() {

    }

    /**
     * 设置布局id
     */
    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

}