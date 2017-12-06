package us.wili.qtwallpaper.bind

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout

/**
 * Bind SwipeRefreshLayout
 * Created by jianqiu on 12/6/17.
 */
class SwipeRefreshBind {

    @BindingAdapter("android:refreshing")
    public fun setRefreshing(view: SwipeRefreshLayout, refresh: Boolean) {
        view.isRefreshing = refresh
    }

}
