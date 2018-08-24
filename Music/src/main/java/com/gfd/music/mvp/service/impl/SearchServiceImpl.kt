package com.gfd.music.mvp.service.impl

import android.content.Context
import com.gfd.common.common.BaseApplication.Companion.context
import com.gfd.music.api.Api
import com.gfd.music.db.SearHistoryTable
import com.gfd.music.db.ext.database
import com.gfd.music.entity.HotSearchDto
import com.gfd.music.mvp.service.SearchService
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
import org.jsoup.select.Selector.select
import java.util.*
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 16:44
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchServiceImpl @Inject constructor():SearchService{

    override fun getHotSearch(callback: SearchService.IGetSearchCallBack) {
        OkGo.get<String>(Api.getHotWord())
                .tag(this)
                .execute(object:StringCallback(){
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("热门标签：$json")
                        val hotSearchDto = Gson().fromJson(json, HotSearchDto::class.java)
                        val datas = ArrayList<String>()
                        hotSearchDto.result.forEach {
                            datas.add(it.word)
                        }
                        datas.removeAt(datas.size - 1)
                        datas.removeAt(datas.size - 1)
                        datas.removeAt(datas.size - 1)
                        callback.onHotSearch(datas)
                    }
                })

    }

    override fun getSearchHistory(context:Context,callback: SearchService.IGetSearchCallBack) {
        context.database.use {
            val historyDatas = select(SearHistoryTable.TABLE_NAME, SearHistoryTable.NAME).parseList(StringParser)
            Collections.reverse(historyDatas)
            callback.onSearchHistory(historyDatas)
        }
    }

    override fun search(context: Context,keyword: String, callback: SearchService.IGetSearchCallBack) {
        //将搜索关键字保存到数据库
        context.database.use {
            insert(SearHistoryTable.TABLE_NAME, SearHistoryTable.NAME to keyword)
        }
        val searchUrl = Api.getSearch(keyword)
        Logger.e("搜索URL:$searchUrl")
        OkGo.get<String>(searchUrl)
                .tag(this)
                .execute(object:StringCallback(){
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("搜索音乐返回的数据：$json")
                    }
                })
    }

    override fun deleteHistory(context: Context, callback: SearchService.IGetSearchCallBack) {
        context.database.use {
            delete(SearHistoryTable.TABLE_NAME)
        }

    }


}