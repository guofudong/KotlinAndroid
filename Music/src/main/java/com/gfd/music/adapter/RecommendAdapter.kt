package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.common.Concant
import com.gfd.music.common.Concant.Companion.ITEM_TYPE_IMG
import com.gfd.music.common.Concant.Companion.ITEM_TYPE_TITLE
import com.gfd.music.entity.SongData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:09
 * @Email：878749089@qq.com
 * @descriptio：
 */
class RecommendAdapter(val context: Context) : BaseMultiAdapter<SongData>(context) {


    init {
        addItemType(Concant.ITEM_TYPE_TITLE, R.layout.item_music_song_title)
        addItemType(Concant.ITEM_TYPE_IMG, R.layout.item_music_song_img)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas.get(position)
        val itemType = itemData.getItemType()
        if (itemType == ITEM_TYPE_TITLE) {
            bindTitleItem(holder, itemData)
        } else if (itemType == ITEM_TYPE_IMG) {
            bindImgItem(holder, itemData)
        }
    }

    /**
     * 设置图片布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindImgItem(holder: BaseViewHolder, itemData: SongData) {
        val title = holder.getView(R.id.tv_music_img_title) as TextView
        val tagText = holder.getView(R.id.tvSongTag) as TextView
        title.text = itemData.recommend_reason
        tagText.text = (itemData.file_duration + "万")
        ImageLoader.loadUrlImage(context, itemData.pic_big, holder.getView(R.id.img_music_item))
    }

    /**
     * 设置title布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindTitleItem(holder: BaseViewHolder, itemData: SongData) {
        val title = holder.getView<TextView>(R.id.tv_item_music_title)
        title.text = itemData.itemTitle
    }

}