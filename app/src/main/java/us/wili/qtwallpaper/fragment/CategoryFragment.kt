package us.wili.qtwallpaper.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.CategoryAdapter
import us.wili.qtwallpaper.base.BaseFragment
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.databinding.FragmentCategoryBinding
import us.wili.qtwallpaper.viewmodel.CategoryViewModel

/**
 * CategoryFragment
 * Created by jianqiu on 5/19/17.
 */
class CategoryFragment: BaseFragment() {

    companion object {
        fun getInstance(): CategoryFragment = CategoryFragment()
    }

    private lateinit var adapter: CategoryAdapter
    private lateinit var model: CategoryViewModel
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View =
            LayoutInflater.from(container?.context).inflate(R.layout.fragment_category, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view!!)
        adapter = CategoryAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        binding.model = model
        model.getCategories().observe(this, Observer<List<CategoryItem>> {
            adapter.addAll(it)
        })
    }

    override fun onLazyLoad() {
        super.onLazyLoad()
        model.refresh()
    }

}