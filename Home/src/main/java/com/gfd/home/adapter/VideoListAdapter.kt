package com.gfd.home.adapter

import android.content.Context
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.home.R
import com.gfd.home.common.Constant.Companion.ITEM_TYPE_IMG
import com.gfd.home.common.Constant.Companion.ITEM_TYPE_TITLE
import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 10:11
 * @Email：878749089@qq.com
 * @description：首页Fragment-视频列表适配器
 */
class VideoListAdapter(private var context: Context) : BaseMultiAdapter<VideoItemData>(context) {

    init {
        addItemType(ITEM_TYPE_TITLE, R.layout.home_item_home_video_list_title)
        addItemType(ITEM_TYPE_IMG, R.layout.home_item_home_video_list_img)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        val itemType = itemData.getItemType()
        if (itemType == ITEM_TYPE_TITLE) {
            //标题类型
            bindTitleItem(holder, itemData)
        } else if (itemType == ITEM_TYPE_IMG) {
            //内容类型
            bindImgItem(holder, itemData)
        }

    }

    /**
     * 设置图片布局数据
     * @param holder BaseViewHolder
     * @param itemData VideoItemData
     */
    private fun bindImgItem(holder: BaseViewHolder, itemData: VideoItemData) {
        holder.apply {
            holder.setImageUrl(R.id.img_home_item_video, itemData.videoImg)
            holder.setText(R.id.tv_home_img_title, itemData.videoName)
            holder.setText(R.id.tvVideoTag, itemData.tagName)
        }
    }

    /**
     * 设置title布局数据
     * @param holder BaseViewHolder
     * @param itemData VideoItemData
     */
    private fun bindTitleItem(holder: BaseViewHolder, itemData: VideoItemData) {
        holder.setText(R.id.tv_home_item_title, itemData.title)
    }

}