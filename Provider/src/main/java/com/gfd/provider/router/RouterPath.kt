package com.gfd.provider.router

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:09
 * @Email：878749089@qq.com
 * @descriptio：
 */

object RouterPath {

    /** 播放模块*/
    class Player {
        companion object {
            /** 视频播放页面 */
            const val PATH_PLAYER = "/player/playActivity"
            const val PATH_PLAYER_WEB = "/playerWeb/playwebActivity"
            const val PATH_PLAYER_MV = "/playerWeb/MV"
            const val KEY_PLAYER = "videoPlayer"
            const val KEY_IMAGE = "videoImage"
            const val KEY_NAME = "videoName"
        }
    }

    /** 首页模块*/
    class Home {
        companion object {
            /** 视频搜索页面 */
            const val PATH_SERACH = "/home/searchActivity"
        }
    }
}