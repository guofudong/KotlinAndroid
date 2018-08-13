package com.gfd.music.ui.activity

import android.graphics.Color
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.adapter.SongListAdapter
import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongTitleData
import com.gfd.music.injection.component.DaggerSongListComponent
import com.gfd.music.injection.module.SongListMoudle
import com.gfd.music.mvp.contract.SongListContract
import com.gfd.music.mvp.preesnter.SongListPresenter
import kotlinx.android.synthetic.main.activity_song_list.*
import kotlinx.android.synthetic.main.head_songlist_top.*

class SongListActivity : BaseMvpActivity<SongListPresenter>(), SongListContract.View {

    private lateinit var artistId: String
    private lateinit var picUrl: String
    private lateinit var duration: String
    private lateinit var title: String
    private var colorBg = Color.BLACK
    private lateinit var songListAdapter: SongListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_song_list
    }

    override fun injectComponent() {
        DaggerSongListComponent.builder()
                .activityComponent(mActivityComponent)
                .songListMoudle(SongListMoudle(this))
                .build()
                .inject(this)
        setStatusBar()
    }

    override fun initView() {
        artistId = intent.getStringExtra("id")
        picUrl = intent.getStringExtra("pic_big")
        duration = intent.getStringExtra("file_duration")
        colorBg = intent.getIntExtra("color", Color.BLACK)
        headRoot.setBackgroundColor(colorBg)
        collapsingTbl.setContentScrimColor(colorBg)
        mRecyclerView.layoutManager = LinearLayoutManager(this@SongListActivity, LinearLayoutManager.VERTICAL, false)
        songListAdapter = SongListAdapter(this@SongListActivity)
        mRecyclerView.adapter = songListAdapter

    }

    override fun initData() {
        mPresenter.getSongList(artistId)
    }

    override fun setListener() {
        headRoot.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

        })


    }

    override fun showSongList(datas: List<SongItemData>) {
        songListAdapter.updateData(datas)
    }

    override fun showHead(datas: SongTitleData) {
        ImageLoader.loadUrlImage(this@SongListActivity, picUrl, ivLogo)
        title = datas.title
        tvTitle.text = title
        tvTag.text = "编辑推荐：$datas.des"
        tvType.text = datas.type
        tvSongTag.text = duration + "万"
    }

}
