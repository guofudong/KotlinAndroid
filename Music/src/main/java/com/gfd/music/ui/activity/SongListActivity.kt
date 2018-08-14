package com.gfd.music.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.IBinder
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.adapter.SongListAdapter
import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongTitleData
import com.gfd.music.injection.component.DaggerSongListComponent
import com.gfd.music.injection.module.SongListMoudle
import com.gfd.music.mvp.contract.SongListContract
import com.gfd.music.mvp.preesnter.SongListPresenter
import com.gfd.music.service.MusicPlayService
import com.gfd.music.service.MusicServiceStub
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_song_list.*
import kotlinx.android.synthetic.main.head_songlist_top.*
import kotlinx.android.synthetic.main.layout_play_contral.*

class SongListActivity : BaseMvpActivity<SongListPresenter>(), SongListContract.View {

    private lateinit var artistId: String
    private lateinit var picUrl: String
    private lateinit var duration: String
    private lateinit var title: String
    private lateinit var songList: List<SongItemData>
    private var colorBg = Color.BLACK
    private lateinit var songListAdapter: SongListAdapter
    private lateinit var songId: String
    private var mPlayService: MusicPlayService? = null

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
        bindService(Intent(this@SongListActivity, MusicPlayService::class.java),
                object : ServiceConnection {
                    override fun onServiceDisconnected(name: ComponentName) {

                    }

                    override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                        mPlayService = (service as MusicServiceStub).getService()
                    }

                }, Context.BIND_AUTO_CREATE)
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
        songListAdapter.seOnClickListener(object : BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                playContralRoot.visibility = View.VISIBLE
                val song = songList[position]
                ImageLoader.loadUrlImage(this@SongListActivity, song.pic, album)
                songName.text = song.name
                songaAtist.text = song.autor
                songId = song.songId
                if(mPlayService != null){
                    mPlayService?.playMusicById(song.songId)
                }

            }
        })
        playMusic.setOnClickListener {

        }

    }

    override fun showSongList(datas: List<SongItemData>) {
        songList = datas
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
