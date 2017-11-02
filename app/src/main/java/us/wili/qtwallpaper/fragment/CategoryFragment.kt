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
import us.wili.qtwallpaper.adapter.CategoryAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.viewmodel.CategoryViewModel

/**
 * CategoryFragment
 * Created by jianqiu on 5/19/17.
 */
class CategoryFragment: BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun getInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var adapter: CategoryAdapter
    private lateinit var model: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        setTitle("Category Fragment")
        adapter = CategoryAdapter()
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        refreshLayout = view.findViewById(R.id.refresh_layout)
        refreshLayout.setOnRefreshListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        model.getCategories().observe(this, Observer<List<CategoryItem>> {
            Log.e("tag", if (it == null) "false" else it.size.toString())
            adapter.addAll(it)
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