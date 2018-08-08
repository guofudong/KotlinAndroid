package com.gfd.home.db.ext

import android.content.Context
import com.gfd.home.db.DatabaseOpenHelper

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 11:09
 * @Email：878749089@qq.com
 * @descriptio：
 */
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.instance
