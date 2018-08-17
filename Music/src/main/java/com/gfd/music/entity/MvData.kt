package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:05
 * @Email：878749089@qq.com
 * @descriptio：
 */
data class MvData(var name: String, var id: Int, var pic: String, var des: String, var autor: String,
                  var playCount: Int, var artistId: Int, var score: Int, var artistName:String = "",var videoUrl: String = "") {

}