package com.gfd.common.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gfd.common.R
import com.gfd.common.common.AppManager
import com.gfd.common.widgets.StateView
import com.gyf.immersionbar.ImmersionBar
import org.jetbrains.anko.find

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:11
 * @Email：878749089@qq.com
 * @description：Activity的基类
 */
@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    /** 多状态布局View*/
    private var statusView: StateView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId()
        if (layoutId != -1) {
            val rootView = LayoutInflater.from(this).inflate(layoutId, null)
            setContentView(rootView)
        }
        AppManager.instance.addActivity(this)
        if (isSetStateBar()) {
            setStatusBar()
        }
        initOperate()
        if (isSetStateView()) {
            setStatusLayout()
        }
        initView()
        initData()
        setListener()
    }

    /** 是都设置沉浸式状态来，true：设置，默认为设置*/
    open fun isSetStateBar() = true

    /** 配置多状态布局*/
    private fun setStatusLayout() {
        statusView = StateView.inject(find(R.id.content_id))
    }

    /**
     * 是否设置多状态View 默认为false
     * @return Boolean true:设置
     */
    open fun isSetStateView(): Boolean = false

    /** 初始化操作，在onCreate中调用*/
    open fun initOperate() {}

    /** 设置布局id*/
    abstract fun getLayoutId(): Int

    /**初始化视图*/
    abstract fun initView()

    /** 初始化数据*/
    abstract fun initData()

    /** 设置监听 */
    open fun setListener() {}

    /** 显示Loading*/
    fun showLoading() {
        statusView?.showLoading()
    }

    /** 显示内容*/
    fun showContent() {
        statusView?.showContent()
    }

    /**
     * 设置透明状态栏
     */
    fun setStatusBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.common_app_Gray)
                .keyboardEnable(true)
                .init()
        //添加内容布局距离屏幕的距离
        if (isSetPaddingTop()) {
            val rootView = this.window.decorView.findViewById(android.R.id.content) as ViewGroup
            rootView.setPadding(
                    0,
                    ImmersionBar.getStatusBarHeight(this),
                    0,
                    0
            )
            rootView.setBackgroundColor(resources.getColor(R.color.common_white))
        }
    }

    /**
     * 是否设置布局与状态栏之间的paddingTop,默认值为true
     * @return Boolean
     */
    open fun isSetPaddingTop(): Boolean = true

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

}
