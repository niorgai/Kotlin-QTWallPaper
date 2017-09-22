package us.wili.qtwallpaper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.HotAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.WallpaperItem
import us.wili.qtwallpaper.viewmodel.HotViewModel

/**
 * HotFragment
 * Created by jianqiu on 5/19/17.
 */
class HotFragment: BaseFragment() {

    companion object {
        fun getInstance(): HotFragment {
            return HotFragment()
        }
    }

    private lateinit var adapter: HotAdapter
    private lateinit var model: HotViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setTitle("Hot Fragment")
        adapter = HotAdapter()
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(this).get(HotViewModel::class.java)
        model.getWallpapers().observe(this, Observer<List<WallpaperItem>> {
            adapter.addAll(it)
        })
    }

    override fun onLazyLoad() {
        super.onLazyLoad()
        model.refresh()

    }
}