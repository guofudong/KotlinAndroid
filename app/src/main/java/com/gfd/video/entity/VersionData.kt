package com.gfd.video.entity

data class VersionData(var code: Int, var data: InfoData)

data class InfoData(var versionCode: Int, var versionName: String,var url:String)