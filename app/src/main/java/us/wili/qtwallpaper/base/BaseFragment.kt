package us.wili.qtwallpaper.base

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import us.wili.qtwallpaper.R

/**
 * Created by jianqiu on 5/19/17.
 */
open class BaseFragment: Fragment() {

    lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    open fun initToolbar() {
        toolbar = view.findViewById(R.id.tool_bar) as Toolbar
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
    }

}