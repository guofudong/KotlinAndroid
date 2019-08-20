package com.gfd.music.entity

/**
 * mv信息实体类
 * @property id String
 * @property name String
 * @property singer String
 * @property playCount String
 * @property publishTime String
 * @property url String
 * @property pic String
 * @constructor
 */
data class MvDetailData(var id: String, var name: String, var singer: String,
                        var playCount: Int, var publishTime: String, var url: String = "",
                        var pic: String = "")