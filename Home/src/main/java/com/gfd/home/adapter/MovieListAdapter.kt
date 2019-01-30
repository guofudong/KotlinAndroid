package com.gfd.home.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.home.R
import com.gfd.home.entity.MovieData
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MovieListAdapter(var context: Context) : BaseAdapter<MovieData>(context) {

    override fun getItemLayoutId(): Int {
        return R.layout.home_item_movie_list
    }


    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val data = mDatas[position]
        val name = holder.getView<TextView>(R.id.home_item_movie_list_title)
        val fraction = holder.getView<TextView>(R.id.home_item_movie_list_fraction)
        val director = holder.getView<TextView>(R.id.home_item_movie_list_director)
        val starring = holder.getView<TextView>(R.id.home_item_movie_list_Starring)
        val img = holder.getView<ImageView>(R.id.home_item_movie_list_pic)
        ImageLoader.loadUrlImage(context, data.img, img)
        name.text = data.movieName
        fraction.text = data.fraction.toString()
        director.text = "导演：${data.director}"
        starring.text = "主演：${data.starring}"
    }

}