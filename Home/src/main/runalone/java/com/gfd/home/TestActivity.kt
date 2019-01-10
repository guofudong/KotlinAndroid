package com.gfd.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gfd.home.ui.fragment.HomeFragment

/**
 * 单独测试Activity
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root, HomeFragment()).commit()
    }
}