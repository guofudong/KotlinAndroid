package com.gfd.home.service.impl

import android.content.Context
import com.gfd.common.common.BaseConstant
import com.gfd.home.db.SearHistoryTable
import com.gfd.home.db.ext.database
import com.gfd.home.entity.SearchItemData
import com.gfd.home.service.SearchService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jsoup.Jsoup
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:05
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchServideImpl @Inject constructor() : SearchService {

    override fun deleteHistory(context: Context, callback: SearchService.SearchCallBack) {
        context.database.use {
            delete(SearHistoryTable.TABLE_NAME)
            callback.onHistory(ArrayList())
        }

    }

    override fun getSearchHistory(context: Context, callback: SearchService.SearchCallBack) {
        context.database.use {
            val historyDatas = select(SearHistoryTable.TABLE_NAME, SearHistoryTable.NAME).parseList(StringParser)
            Collections.reverse(historyDatas)
            callback.onHistory(historyDatas)
        }

    }

    override fun search(context: Context, keyWord: String, callback: SearchService.SearchCallBack) {
        //将搜索关键字保存到数据库
        context.database.use {
            insert(SearHistoryTable.TABLE_NAME, SearHistoryTable.NAME to keyWord)

        }
        OkGo.post<String>(BaseConstant.URL_SEARCH)
                .tag(this)
                .params("wd", keyWord)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>?) {
                        var datas = ArrayList<SearchItemData>()
                        val html = response!!.body().toString()
                        val document = Jsoup.parse(html)
                        document.select("div[class=item clearfix]").forEach {
                            //图片
                            val a = it.selectFirst("a")
                            var link = a.attr("href")
                            var tag: Int
                            if (link.contains("/vod")) {//电视剧
                                tag = SearchItemData.TAG_EPISODE
                                link = "/vod" + link.split("/vod")[1]
                            } else {//电影
                                tag = SearchItemData.TAG_MOVIE
                                link = link.substring(1, link.length)
                            }
                            val style = a.attr("style")
                            val imgUrl = style.split(")")[0].split("url(")[1]
                            //名字
                            val videoName = it.selectFirst("div[class=head]").text()
                            val ul = it.select("ul > li")
                            val actor = ul[0].text()//演员
                            val videoType = ul[1].text()//类型
                            val plot = ul[2].text()//简介
                            datas.add(SearchItemData(videoName, imgUrl, link, actor, plot, videoType, tag))
                        }
                        val json = Gson().toJson(datas)
                        Logger.e("搜索结果解析：$json")
                        callback.onSearch(datas)
                    }
                })
    }

}
