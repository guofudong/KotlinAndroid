package com.gfd.common.utils

import android.widget.Toast
import com.gfd.common.common.BaseApplication

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 10:52
 * @Email：878749089@qq.com
 * @descriptio：Toast工具类
 */
class ToastUtils {

    private var mToast: Toast

    private constructor() {
        mToast = Toast.makeText(BaseApplication.context, "", Toast.LENGTH_SHORT)
    }

    companion object {
        val instance: ToastUtils by lazy { ToastUtils() }
    }

    /**
     * 显示Toast
     */
    fun showToast(resId: Int) {
        mToast.setText(resId)
        mToast.show()
    }

    /**
     * 显示Toast
     */
    fun showToast(tostMsg:String){
        mToast.setText(tostMsg)
        mToast.show()
    }

    fun destroy(){
        mToast.cancel()
    }

}