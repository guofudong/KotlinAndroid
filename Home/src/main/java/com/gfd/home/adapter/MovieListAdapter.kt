package com.gfd.home.adapter

import android.content.Context
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.home.R
import com.gfd.home.entity.MovieData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:49
 * @Email：878749089@qq.com
 * @description：首页-top分类-电影列表页面列表适配器
 */
class MovieListAdapter(var context: Context) : BaseAdapter<MovieData>(context) {

    override fun getItemLayoutId(): Int = R.layout.home_item_movie_list


    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val data = mData[position]
        holder.apply {
            //设置电影信息
            setText(R.id.home_item_movie_list_title, data.movieName)
            setText(R.id.home_item_movie_list_fraction, data.fraction.toString())
            setText(R.id.home_item_movie_list_director, "导演：${data.director}")
            setText(R.id.home_item_movie_list_Starring, "主演：${data.starring}")
            setImageUrl(R.id.home_item_movie_list_pic, data.img)
        }
    }

}