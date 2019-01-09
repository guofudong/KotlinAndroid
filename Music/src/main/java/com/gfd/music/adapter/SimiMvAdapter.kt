package com.gfd.music.adapter

import android.content.Context
import android.text.TextUtils
import android.view.TextureView
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.FormatUtil
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.entity.MvData

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 17:35
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SimiMvAdapter(val context: Context) : BaseAdapter<MvData>(context) {
    override fun getItemLayoutId(): Int {
        return R.layout.music_item_mvdetail_simi

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val mvData = mDatas[position]
        val img = holder.getView<ImageView>(R.id.iv_item_mvdetail_simi)
        val title = holder.getView<TextView>(R.id.tv_item_mvdetail_simi_name)
        val author = holder.getView<TextView>(R.id.tv_item_mvdetail_simi_author)
        ImageLoader.loadUrlImage(context, mvData.pic, img)
        title.text = if (TextUtils.isEmpty(mvData.des)) {
            mvData.name
        } else {
            mvData.des
        }
        if (title.text == "null") {
            title.text = "慕涵盛华"
        }
        mvData.artistName = if (TextUtils.isEmpty(mvData.artistName)) "慕涵盛华" else mvData.artistName
        author.text = "${FormatUtil.formatTime(mvData.duration.toLong())} by ${mvData.artistName}"
    }

}