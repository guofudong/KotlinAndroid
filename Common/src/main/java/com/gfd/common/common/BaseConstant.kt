package com.gfd.common.common

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 10:22
 * @Email：878749089@qq.com
 * @descriptio：
 */
open class BaseConstant {
    companion object {
        const val BASE_URL = "http://www.vip-free.com"
        const val BUGLY_APPID = "4e8d7c7498"
        /** 相声视频解析来源*/
        const val CROSSTRALK_BASE_URL = "https://www.toutiao.com"
        /** 相声视频解析地址 */
        const val CROSSTRALK_URL_ANALYSIS = "http://service.iiilab.com/video/web/toutiao"
        const val URL_SEARCH = "http://www.vip-free.com/seacher.php"
        const val URL_ANALYZE = "http://api.zuilingxian.com/jiexi.php?url="
        /**频道-电影*/
        const val URL_CHANNEL_MOVIE = "http://www.vip-free.com/movie.php?m=/dianying/list.php?cat=all&page="
        /** 频道-抢先看 */
        const val URL_CHANNEL_NEW = "http://www.vip-free.com/vlist.php?cid=0&page="
        /** 频道-电视剧 */
        const val URL_CHANNEL_DIANSHI = "http://www.vip-free.com/tv.php?u=/dianshi/list.php?cat=all&page="
        /** 频道-综艺 */
        const val URL_CHANNEL_ZONGYI = "http://www.vip-free.com/zongyi.php?m=/zongyi/list.php?cat=all&pageno="
    }
}