package us.wili.qtwallpaper.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.adapter.HotAdapter
import us.wili.qtwallpaper.base.BaseActivity
import us.wili.qtwallpaper.data.model.WallpaperItem
import us.wili.qtwallpaper.viewmodel.CategoryDetailViewModel

/**
 * Each Category
 * Created by jianqiu on 11/6/17.
 */
class CategoryDetailActivity : BaseActivity() {

    private var mAdapter: HotAdapter? = null
    private lateinit var viewModel: CategoryDetailViewModel

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
        initToolBar()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        mAdapter = HotAdapter()
        recyclerView.adapter = mAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val intent = intent
        if (intent == null) {
            finish()
            return
        }
        val name = intent.getStringExtra(NAME)
        val id = intent.getStringExtra(ID)
        title = name
        viewModel = ViewModelProviders.of(this).get(CategoryDetailViewModel::class.java)
        viewModel.refresh(id)
        viewModel.getWallpapers().observe(this, Observer<List<WallpaperItem>> {
            mAdapter!!.addAll(it)
        })
    }

    companion object {

        private val NAME = "name"
        private val ID = "id"

        fun newIntent(context: Context, name: String, id: String): Intent {
            val intent = Intent(context, CategoryDetailActivity::class.java)
            intent.putExtra(NAME, name)
            intent.putExtra(ID, id)
            return intent
        }
    }
}
