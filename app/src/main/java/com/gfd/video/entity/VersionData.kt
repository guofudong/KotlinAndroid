package com.gfd.video.entity

/**
 * @Author：郭富东
 * @Date：2019/8/19 : 15:37
 * @Email：878749089@qq.com
 * @description：app版本信息实体类
 */
data class VersionData(var code: Int, var data: InfoData)

data class InfoData(var versionCode: Int, var versionName: String, var url: String)