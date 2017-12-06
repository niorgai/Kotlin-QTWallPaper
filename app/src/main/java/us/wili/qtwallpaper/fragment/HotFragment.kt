package us.wili.qtwallpaper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.HotAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem
import us.wili.qtwallpaper.databinding.FragmentHotBinding
import us.wili.qtwallpaper.viewmodel.HotViewModel

/**
 * HotFragment
 * Created by jianqiu on 5/19/17.
 */
class HotFragment: BaseFragment(), AppBarLayout.OnOffsetChangedListener {

    companion object {
        fun getInstance(): HotFragment = HotFragment()
    }

    private lateinit var adapter: HotAdapter
    private lateinit var model: HotViewModel
    private lateinit var binding: FragmentHotBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            LayoutInflater.from(container?.context).inflate(R.layout.fragment_hot, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view!!)
        adapter = HotAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.appBar.addOnOffsetChangedListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(this).get(HotViewModel::class.java)
        binding.model = model
        model.getWallpapers().observe(this, Observer<List<WallpaperItem>> {
            adapter.addAll(it)
        })
        model.getCategories().observe(this, Observer<List<CategoryItem>> {
            binding.banner.refreshBanner(it)
        })
    }


    override fun onLazyLoad() {
        super.onLazyLoad()
        model.refresh()

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        binding.refreshLayout.isEnabled = verticalOffset == 0
    }
}