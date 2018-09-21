package com.gfd.crosstalk.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import com.gfd.crosstalk.ui.fragment.CrosstalkFragment

/**
 * @Author : 郭富东
 * @Date ：2018/9/21 - 17:10
 * @Email：878749089@qq.com
 * @descriptio：测试Activit，单独运行模块中的内容
 */
class TextActivity : AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fl = FrameLayout(this)
        //生成id
        fl.id = View.generateViewId()
        setContentView(fl)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fl.id,CrosstalkFragment())
        transaction.commit()
    }

}