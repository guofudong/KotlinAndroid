package com.gfd.music.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.graphics.Palette
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.gfd.music.common.Concant
import com.gfd.music.common.Concant.Companion.ITEM_TYPE_IMG
import com.gfd.music.common.Concant.Companion.ITEM_TYPE_LIST
import com.gfd.music.common.Concant.Companion.ITEM_TYPE_TITLE
import com.gfd.music.entity.RadioData
import de.hdodenhof.circleimageview.CircleImageView


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:09
 * @Email：878749089@qq.com
 * @descriptio：
 */
class RadioAdapter(val context: Context) : BaseMultiAdapter<RadioData>(context) {


    init {
        addItemType(Concant.ITEM_TYPE_TITLE, R.layout.music_item_music_song_title)
        addItemType(Concant.ITEM_TYPE_IMG, R.layout.music_item_music_song_img)
        addItemType(Concant.ITEM_TYPE_LIST, R.layout.music_item_music_radio_list)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        val itemType = itemData.getItemType()
        if (itemType == ITEM_TYPE_TITLE) {
            bindTitleItem(holder, itemData)
        } else if (itemType == ITEM_TYPE_IMG) {
            bindImgItem(holder, itemData)
        } else if (itemType == ITEM_TYPE_LIST) {
            bindListItem(holder, itemData)
        }
    }

    /**
     * 设置图片布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindImgItem(holder: BaseViewHolder, itemData: RadioData) {
        val title = holder.getView(R.id.tv_music_img_title) as TextView
        val tagText = holder.getView(R.id.tvSongTag) as TextView
        title.text = itemData.title
        tagText.text = (itemData.count)
        val img = holder.getView(R.id.img_music_item) as ImageView
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
                   // itemData.color = vibrantColor
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
    private fun bindTitleItem(holder: BaseViewHolder, itemData: RadioData) {
        val title = holder.getView<TextView>(R.id.tv_item_music_title)
        title.text = itemData.title
    }

    /**
     * 设置List item布局数据
     * @param holder BaseViewHolder
     * @param itemData SongData
     */
    private fun bindListItem(holder: BaseViewHolder, itemData: RadioData) {
        val viewCount = holder.getView(R.id.tv_radio_view_count) as TextView
        val userName = holder.getView(R.id.tv_radio_user_name) as TextView
        val tvTitle = holder.getView(R.id.tv_title_list) as TextView
        val logo = holder.getView(R.id.img_raido_list_item) as ImageView
        val avatar = holder.getView(R.id.img_raido_user_avatar) as CircleImageView
        viewCount.text = itemData.count
        userName.text = itemData.name
        tvTitle.text = itemData.title
        Glide.with(context).load(itemData.logo).asBitmap().error(R.drawable.ic_songlist_error).into(logo)
        Glide.with(context).load(itemData.avatar).asBitmap().error(R.drawable.ic_songlist_error).into(avatar)

    }

}