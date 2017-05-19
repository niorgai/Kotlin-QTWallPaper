package us.wili.qtwallpaper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.base.BaseFragment

/**
 * Created by jianqiu on 5/19/17.
 */
class HotFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        toolbar.setTitle("hello world")
    }
}