package com.gfd.music.api

import com.gfd.common.utils.AESTools
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * @Author : 郭富东
 * @Date ：2018/8/9 - 16:58
 * @Email：878749089@qq.com
 * @descriptio：
 */
object Api {
    var URL_BASE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.6&format=json&method="
    var URL_MV_BASE = "https://netease.api.zzsun.cc/"
    /** 推荐歌曲 */
    var URL_RECOMMEND = "baidu.ting.song.getEditorRecommend&num="
    /** 新曲轮播图 */
    var URL_BANNER = "baidu.ting.plaza.getFocusPic&num="
    /** 推荐电台 */
    var URL_RADIO = "baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14"
    /** 歌曲信息和下载地址 */
    var URL_SONG_INFO = "baidu.ting.song.getInfos"
    /** 排行榜音乐榜类别 */
    var URL_BILL_CATEGORY = "baidu.ting.billboard.billCategory&kflag=1"
    /** 排行榜歌曲列表 */
    var URL_BILL_SONG_LIST = "baidu.ting.billboard.billList"
    /** 歌单类别*/
    var URL_BILL_SONG_LIST_CATEGORY = "baidu.ting.diy.gedanCategory"
    /** 获取热门歌曲*/
    var URL_BILL_HOT_SONG_LIST = "baidu.ting.diy.getHotGeDanAndOfficial&num="
    /** 通过标签获取歌单列表*/
    var URL_BILL_TAG_SONG_LIST = "baidu.ting.diy.search"
    /** 通过id获取歌单信息和歌曲*/
    var URL_ID_SONG_LIST = "baidu.ting.diy.gedanInfo"
    /** 热门关键字*/
    var URL_HOT_WORD = "baidu.ting.search.hot"
    /** 搜索*/
    var URL_SEARCH = "baidu.ting.search.merge"
    /** 频道 */
    var URL_CHANNELTA = "baidu.ting.lebo.getChannelTag"
    /** 频道下的歌曲*/
    var URL_CHANNEL_SONG_LIST = "baidu.ting.lebo.channelSongList"
    /** 热门标签 */
    var URL_HOT_TAG = "baidu.ting.tag.getHotTag&nums="
    /** 标签对应的歌曲列表*/
    var URL_TAG_SONG_LIST = "baidu.ting.tag.songlist"
    /** 歌曲基本信息*/
    var URL_SONG_BASE_INFO = "baidu.ting.song.baseInfos"
    var ENCODE = "song_id,title,author,album_title,pic_big,pic_small,havehigh,all_rate,charge,has_mv_mobile,learn,song_source,korean_bb_song"

    /**
     * 获取推荐歌曲API
     * @param count Int : 要获取歌曲的数量
     * @return String
     */
    fun getRecommend(count: Int): String {
        return URL_BASE + URL_RECOMMEND + count
    }

    /**
     * 获取新曲轮播图API
     * @param count Int ：要获取的数量
     * @return String
     */
    fun getBanner(count: Int = 4): String {
        return URL_BASE + URL_BANNER + count
    }

    /**
     * 获取推荐电台API
     * @return String
     */
    fun getRadio(): String {
        return URL_BASE + URL_RADIO
    }

    /**
     * 获取歌曲的信息和下载地址
     * @param songid String ： 歌曲id
     * @return String
     */
    fun getSongInfo(songid: String): String {
        var params = "songid=$songid&ts=${System.currentTimeMillis()}"
        return URL_BASE + URL_SONG_INFO + "&$params&e=${AESTools.encrpty(params)}"
    }

    /**
     * 获取排行榜分类
     * @return String
     */
    fun getBillCategory(): String {
        return URL_BASE + URL_BILL_CATEGORY
    }

    /**
     * 获取排行榜歌曲列表
     * @param type Int ： 排行榜的类别
     * @param size Int ： 获取的数量
     * @param offset Int ： 偏移量 （分页）
     * @return String
     */
    fun getBillSongList(type: Int, size: Int, offset: Int): String {
        return URL_BASE + URL_BILL_SONG_LIST + "&type=$type&offset=$offset&size=$size&fields=${encode(ENCODE)}"
    }

    /**
     * 获取歌单类别
     * @return String
     */
    fun getSongListCategory(): String {
        return URL_BASE + URL_BILL_SONG_LIST_CATEGORY
    }

    /**
     * 获取热门歌单
     * @param count Int ： 获取的数量
     * @return String
     */
    fun getHotSongList(count: Int): String {
        return URL_BASE + URL_BILL_HOT_SONG_LIST + count
    }

    /**
     * 获取标签对应的歌单
     * @param tag String ： 歌单标签
     * @param pageSize Int : 每页的数量 ，默认为30
     * @param page Int ：页码
     * @return String
     */
    fun getTagSongList(tag: String, pageSize: Int = 30, page: Int): String {
        return URL_BASE + URL_BILL_TAG_SONG_LIST + "&page_size=$pageSize&page_no=$page&query=${encode(tag)}"
    }

    /**
     * 根据id获取歌单信息和歌曲
     * @param id Int ： 歌单id
     * @return String
     */
    fun getIdSongList(id: String): String {
        return "$URL_BASE$URL_ID_SONG_LIST&listid=$id"
    }

    /**
     * 搜索歌曲
     * @param word String ：关键字
     * @param page Int ：页码
     * @param pageSize Int ： 每页大小
     * @return String
     */
    fun getSearch(word: String, page: Int = 1, pageSize: Int = 12): String {
        return "$URL_BASE$URL_SEARCH&query=$word&page_no=$page&page_size=$pageSize&type=-1&data_source=0"
    }

    /**
     * 获取热门关键字
     * @return String
     */
    fun getHotWord(): String {
        return URL_BASE + URL_HOT_WORD
    }

    /**
     * 获取频道
     * @param page Int：页码
     * @param pageSize Int： 每页大小
     * @return String
     */
    fun getChannelTag(page: Int, pageSize: Int): String {
        return "$URL_BASE$URL_CHANNELTA&page_no=$page&page_size$pageSize"
    }

    /**
     * 获取频道下的歌曲列表
     * @param tag Int ： 频道id
     * @param count Int ： 获取的数量
     * @return String
     */
    fun getChannelSongList(tag: Int, count: Int): String {
        return "$URL_BASE$URL_CHANNEL_SONG_LIST&tag_id=tag&num=$count"
    }

    /**
     * 获取热门标签
     * @param count Int ：获取的数量
     * @return String
     */
    fun getHotTag(count: Int = 10): String {
        return URL_BASE + URL_HOT_TAG + count
    }

    /**
     * 获取标签对应的歌曲列表
     * @param tagName String ：标签名
     * @param count Int ：获取的数量
     * @return String
     */
    fun getTagSongList(tagName: String, count: Int): String {
        return URL_BASE + URL_TAG_SONG_LIST + "&tagname=${encode(tagName)}&limit=$count"
    }

    /**
     * 获取歌曲的基本信息
     * @param songid String ：歌曲id
     * @return String
     */
    fun getSongBaseInfo(songid: String): String {
        return "$URL_BASE$URL_SONG_BASE_INFO&song_id=$songid"
    }

    fun encode(str: String?): String {
        if (str == null) return ""
        try {
            return URLEncoder.encode(str, "utf-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return str
    }

    /**
     * 获取推荐MV
     * @param offset Int
     * @param size Int
     * @return String
     */
    fun getRecommendMV(offset: Int = 0, size: Int = 12): String {
        return "https://api.bzqll.com/music/netease/topMvList?key=579621905&limit=$size&offset=$offset"
    }

    fun getMVDetail(mvId: Int): String {
        return URL_MV_BASE + "mv?mvid=$mvId"
    }

    /**
     * 获取相似MV
     * @param mvId String
     * @return String
     */
    fun getSimilarMv(mvId: String): String {
        //return URL_MV_BASE + "simi/mv?mvid=$mvId"改API已不能使用
        return "https://api.bzqll.com/music/netease/search?key=579621905&type=video&limit=8&offset=0&s=$mvId"
    }

    /**
     * 获取MV的评论
     * @param mvId String
     * @return String
     */
    fun getMvComment(mvId: String): String {
        return URL_MV_BASE + "comment/mv?id=$mvId"
    }

    /**
     * 获取MV的信息
     * @param mvId String
     * @return String
     */
    fun getMvDetail(mvId: String): String {
        return "https://api.bzqll.com/music/netease/mv?key=579621905&id=$mvId"
    }

    /**
     * 获取搜索音乐url，支持全网搜索
     * @param name String
     * @return String
     */
    fun getSearchSongApi(name: String): String {
        return "https://tool.liumingye.cn/qqws/?name=$name"
    }

    class AnalysisMusic {
        companion object {
            val url = "http://tool.liumingye.cn/music/ajax.php"
            val cookie = "PHPSESSID=k0lglound3be0hgf6l44n66c56;"
            val host = "tool.liumingye.cn"
            val origin = "http://tool.liumingye.cn"
            val request_with = "XMLHttpRequest"
            val referer = "http://tool.liumingye.cn/music/?name="
            val token = "klotuyM568"
            val key = "&vkey=E3E2AEB8F82039EEDE2D7628423639DEF08ADD1CDF7BC9B290E4C263664F87F4E9CA124C300F8FA671F3112D38A4C47FE4059329F93139F8"
        }
    }

}
