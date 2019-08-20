package com.gfd.music.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.gfd.music.common.Constant.Companion.ITEM_TYPE_IMG
import com.gfd.music.common.Constant.Companion.ITEM_TYPE_LIST
import com.gfd.music.common.Constant.Companion.ITEM_TYPE_TITLE
import com.gfd.music.entity.RadioData


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:09
 * @Email：878749089@qq.com
 * @description：FM页面列表适配器
 */
class RadioAdapter(val context: Context) : BaseMultiAdapter<RadioData>(context) {

    init {
        addItemType(ITEM_TYPE_TITLE, R.layout.music_item_music_song_title)
        addItemType(ITEM_TYPE_IMG, R.layout.music_item_music_song_img)
        addItemType(ITEM_TYPE_LIST, R.layout.music_item_music_radio_list)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        when (itemData.getItemType()) {
            ITEM_TYPE_TITLE -> bindTitleItem(holder, itemData)
            ITEM_TYPE_IMG -> bindImgItem(holder, itemData)
            ITEM_TYPE_LIST -> bindListItem(holder, itemData)
        }
    }

    /**
     * 设置图片布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindImgItem(holder: BaseViewHolder, itemData: RadioData) {
        holder.apply {
            setText(R.id.tv_music_img_title, itemData.title)
            setText(R.id.tvSongTag, itemData.count)
            val img = getView<ImageView>(R.id.img_music_item)
            setPaletteColor(itemData, img)
        }
    }

    /**
     * 设置title布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindTitleItem(holder: BaseViewHolder, itemData: RadioData) {
        holder.setText(R.id.tv_item_music_title, itemData.title)
    }

    /**
     * 设置List item布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindListItem(holder: BaseViewHolder, itemData: RadioData) {
        holder.apply {
            setText(R.id.tv_radio_view_count, itemData.count)
            setText(R.id.tv_radio_user_name, itemData.name)
            setText(R.id.tv_title_list, itemData.title)
            setImageUrl(R.id.img_raido_list_item, itemData.logo, R.drawable.ic_songlist_error)
            setImageUrl(R.id.img_raido_user_avatar, itemData.avatar, R.drawable.ic_songlist_error)
        }
    }

    /**
     * 取色设置图片背景颜色
     * @param itemData RadioData
     * @param img ImageView
     */
    private fun setPaletteColor(itemData: RadioData, img: ImageView) {
        Glide.with(context).load(itemData.logo).asBitmap().error(R.drawable.ic_songlist_error).into(object : SimpleTarget<Bitmap>() {
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

}