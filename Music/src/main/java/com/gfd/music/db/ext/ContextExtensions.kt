package com.gfd.music.db.ext

import android.content.Context
import com.gfd.music.db.DatabaseOpenHelper

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 11:09
 * @Email：878749089@qq.com
 * @description：
 */
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.instance
