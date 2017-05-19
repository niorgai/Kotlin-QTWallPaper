package us.wili.qtwallpaper.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import us.wili.qtwallpaper.R

/**
 * BaseActivity
 * Created by jianqiu on 5/19/17.
 */
open class BaseActivity: AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initToolbar() {
        toolbar = findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolbar)
    }
}