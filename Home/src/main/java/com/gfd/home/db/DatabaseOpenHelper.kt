package com.gfd.home.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.gfd.common.common.BaseApplication
import org.jetbrains.anko.db.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 10:47
 * @Email：878749089@qq.com
 * @descriptio：
 */
class DatabaseOpenHelper private constructor(context: Context = BaseApplication.context)
    : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "homeData"
        val DB_VERSION = 1
        val instance by lazy { DatabaseOpenHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(SearHistoryTable.TABLE_NAME, true,
                SearHistoryTable.NAME to TEXT + PRIMARY_KEY + UNIQUE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(SearHistoryTable.TABLE_NAME, true)
        onCreate(db)
    }
}