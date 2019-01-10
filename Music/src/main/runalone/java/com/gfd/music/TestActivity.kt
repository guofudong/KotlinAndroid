package com.gfd.music

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gfd.music.ui.fragment.MusicFragment

/**
 * 单独测试Activity
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root, MusicFragment()).commit()
    }
}