package com.gfd.common.utils

import android.os.Environment
import java.io.File

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 10:22
 * @Email：878749089@qq.com
 * @descriptio：文件操作工具类
 */
object FileUtils {


    private fun getFile(fileName: String): File {
        val absolutePath = Environment.getDataDirectory().absolutePath
        val file = File(absolutePath, fileName)
        if(!file.exists()){
            file.createNewFile()
        }
        return file
    }

    fun saveToCache(content: String, fileName: String) {
        val file = getFile(fileName)
        file.writeText(content)
    }

    fun getTextFromCache(fileName: String) : String{
        val file = getFile(fileName)
        return file.readText()
    }
}
