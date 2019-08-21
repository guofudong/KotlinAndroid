package com.gfd.music.entity

/**
 * @Author ：郭富东
 * @Date：2019/8/21:14:04
 * @Email：878749089@qq.com
 * @description：MV评论数据实体类
 */
data class MvComment(
        val `data`: MvCommentData,
        val code: Int,
        val msg: String,
        val timestamp: Long
)

data class MvCommentData(
        val commentlist: List<Commentlist>,
        val commenttotal: Int
)

data class Commentlist(
        val avatarurl: String,
        val commentid: String,
        val commit_state: Int,
        val enable_delete: Int,
        val encrypt_rootcommentuin: String,
        val encrypt_uin: String,
        val identity_pic: String,
        val identity_type: Int,
        val is_hot: Int,
        val is_hot_cmt: Int,
        val is_medal: Int,
        val is_stick: Int,
        val ispraise: Int,
        val middlecommentcontent: List<Middlecommentcontent>,
        val nick: String,
        val permission: Int,
        val praisenum: Int,
        val root_enable_delete: Int,
        val root_identity_pic: String,
        val root_identity_type: Int,
        val root_is_stick: Int,
        val rootcommentcontent: String,
        val rootcommentid: String,
        val rootcommentnick: String,
        val rootcommentuin: String,
        val score: Int,
        val taoge_topic: String,
        val taoge_url: String,
        val time: Int,
        val uin: String,
        val user_type: String,
        val vipicon: String
)

data class Middlecommentcontent(
        val encrypt_replyeduin: String,
        val encrypt_replyuin: String,
        val reply_identity_pic: String,
        val reply_identity_type: Int,
        val replyed_identity_pic: String,
        val replyed_identity_type: Int,
        val replyednick: String,
        val replyeduin: String,
        val replynick: String,
        val replyuin: String,
        val subcommentcontent: String,
        val subcommentid: String
)