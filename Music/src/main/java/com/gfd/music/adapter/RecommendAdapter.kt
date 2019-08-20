package com.gfd.music.adapter

import android.content.Context
import android.graphics.*
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.gfd.music.common.Constant.Companion.ITEM_TYPE_IMG
import com.gfd.music.common.Constant.Companion.ITEM_TYPE_TITLE
import com.gfd.music.entity.SongData


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:09
 * @Email：878749089@qq.com
 * @description：推荐Fragment-列表适配器
 */
class RecommendAdapter(val context: Context) : BaseMultiAdapter<SongData>(context) {

    init {
        addItemType(ITEM_TYPE_TITLE, R.layout.music_item_music_song_title)
        addItemType(ITEM_TYPE_IMG, R.layout.music_item_music_song_img)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
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
        holder.apply {
            setText(R.id.tv_music_img_title, itemData.recommend_reason)
            setText(R.id.tvSongTag, itemData.file_duration)
            val img = getView<ImageView>(R.id.img_music_item)
            setPaletteColor(itemData, img)
        }
    }

    /**
     * 设取色设置图片背景
     * @param itemData SongData
     * @param img ImageView
     */
    private fun setPaletteColor(itemData: SongData, img: ImageView) {
        Glide.with(context).load(itemData.pic_big).asBitmap().error(R.drawable.ic_songlist_error).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {

                Palette.from(resource).generate { palette ->
                    if (palette == null) return@generate
                    //palette取色不一定取得到某些特定的颜色，这里通过取多种颜色来避免取不到颜色的情况
                    var vibrantColor = palette.getVibrantColor(Color.TRANSPARENT)
                    if (vibrantColor == Color.TRANSPARENT) {
                        vibrantColor = palette.getMutedColor(Color.TRANSPARENT)
                    }
                    if (vibrantColor == Color.TRANSPARENT) {
                        vibrantColor = palette.getMutedColor(Color.TRANSPARENT)
                    }
                    itemData.color = vibrantColor
                    img.setImageBitmap(resource)
                }

            }
        })
    }


    /**
     * 设置title布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindTitleItem(holder: BaseViewHolder, itemData: SongData) {
        holder.setText(R.id.tv_item_music_title, itemData.itemTitle)
    }

}