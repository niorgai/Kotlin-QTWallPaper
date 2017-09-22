package us.wili.qtwallpaper.base

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import us.wili.qtwallpaper.R

/**
 * BaseFragment
 * Created by jianqiu on 5/19/17.
 */
open class BaseFragment: android.support.v4.app.Fragment() {

    var toolbar: Toolbar? = null

    var hasLazyLoad: Boolean = false

    var delayHandler: Handler? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            delayHandler = Handler()
            activity.window.decorView.post { kotlin.run { delayHandler.run { checkLazyLoad() } } }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        checkLazyLoad()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hasLazyLoad = false
    }

    /**
     * compat for use fragment in viewpager
     */
    private fun checkLazyLoad() {
        if (userVisibleHint && isAdded && !hasLazyLoad) {
            onLazyLoad()
        }
    }

    open fun onLazyLoad() {
        hasLazyLoad = true
    }

    fun initToolbar() {
        toolbar = view?.findViewById(R.id.tool_bar)
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
        }
    }

    fun setTitle(resId: Int) {
        toolbar?.setTitle(resId)
    }

    fun setTitle(title: String) {
        toolbar?.title = title
    }

}