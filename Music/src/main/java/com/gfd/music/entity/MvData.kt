package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:05
 * @Email：878749089@qq.com
 * @description：
 */
data class MvData(var name: String = "慕涵盛华", var id: String, var pic: String, var des: String, var autor: String,
                  var playCount: Int, var artistId: Int = 0, var score: Int = 0, var artistName:String = "",
                  var duration:Int = 0,var videoUrl: String = "")