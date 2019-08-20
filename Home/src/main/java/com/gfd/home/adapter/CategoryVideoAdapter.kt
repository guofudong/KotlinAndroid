package com.gfd.home.adapter

import android.content.Context
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.home.R
import com.gfd.home.common.Constant
import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 10:11
 * @Email：878749089@qq.com
 * @description：首页-更多页面-分类Fragment-列表适配器
 */
class CategoryVideoAdapter(private var context: Context) : BaseMultiAdapter<VideoItemData>(context) {

    init {
        addItemType(Constant.ITEM_TYPE_IMG, R.layout.home_item_home_video_list_img)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        bindImgItem(holder, itemData)
    }

    /**
     * 设置图片布局数据
     * @param holder BaseViewHolder
     * @param itemData VideoItemData
     */
    private fun bindImgItem(holder: BaseViewHolder, itemData: VideoItemData) {
        holder.apply {
            setText(R.id.tv_home_img_title, itemData.videoName)
            setText(R.id.tvVideoTag, itemData.tagName)
            setImageUrl(R.id.img_home_item_video, itemData.videoImg)
        }
    }
}