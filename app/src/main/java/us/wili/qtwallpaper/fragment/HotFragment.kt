package us.wili.qtwallpaper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.HotAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem
import us.wili.qtwallpaper.view.HotCategoriesBanner
import us.wili.qtwallpaper.viewmodel.HotViewModel

/**
 * HotFragment
 * Created by jianqiu on 5/19/17.
 */
class HotFragment: BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun getInstance(): HotFragment {
            return HotFragment()
        }
    }

    private lateinit var adapter: HotAdapter
    private lateinit var model: HotViewModel
    private lateinit var banner: HotCategoriesBanner
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        setTitle("Hot Fragment")
        adapter = HotAdapter()
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        banner = view.findViewById(R.id.banner)
        refreshLayout = view.findViewById(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(this).get(HotViewModel::class.java)
        model.getWallpapers().observe(this, Observer<List<WallpaperItem>> {
            Log.d("123", it!!.size.toString())
            adapter.addAll(it)
            refreshLayout.isRefreshing = false
        })
        model.getCategories().observe(this, Observer<List<CategoryItem>> {
            banner.refreshBanner(it)
            refreshLayout.isRefreshing = false
        })
    }


    override fun onLazyLoad() {
        super.onLazyLoad()
        refreshLayout.isRefreshing = true
        model.refresh()

    }

    override fun onRefresh() {
        model.refresh()
    }
}