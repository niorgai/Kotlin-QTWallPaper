package us.wili.qtwallpaper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.HotAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.CategoryItem
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
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onLazyLoad() {
        super.onLazyLoad()
        val model: HotViewModel = ViewModelProviders.of(this).get(HotViewModel::class.java)
        model.getCategories().observe(this, Observer<List<CategoryItem>> {
            Log.e("tag", if (it == null) "false" else it.size.toString())
            adapter.addAll(it)
        })
    }
}