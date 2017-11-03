package us.wili.qtwallpaper.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import qiu.niorgai.widget.banner.UnlimitedBannerAdapter
import qiu.niorgai.widget.banner.UnlimitedViewPager
import qiu.niorgai.widget.banner.ViewPagerModel
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.tool.ColorUtils

/**
 * Banner
 * Created by jianqiu on 11/3/17.
 */
class HotCategoriesBanner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private var mViewPager: UnlimitedViewPager
    private var mBannerAdapter: UnlimitedBannerAdapter
    private var mDotsLayout: ViewGroup
    private var widgetHeight: Int

    init {
        inflate(context, R.layout.layout_hot_categories, this)
        visibility = View.GONE
        mViewPager = findViewById(R.id.view_pager)
        mBannerAdapter = UnlimitedBannerAdapter()
        mViewPager.adapter = mBannerAdapter
        mDotsLayout = findViewById(R.id.dots_layout)

        val metrics = resources.displayMetrics
        widgetHeight = (((metrics.widthPixels / 640f) * 380f).toInt())
    }

    fun refreshBanner(data: List<CategoryItem>?) {
        if (data == null || data.isEmpty()) {
            visibility = View.GONE
            return
        }
        visibility = View.VISIBLE
        layoutParams.height = widgetHeight
        val models = ArrayList<ViewPagerModel>()
        for (item in data) {
            val model = ViewPagerModel()
            model.aspectRatio = 1.684210526f
            model.uri = Uri.parse(item.coverUrl)
            model.holder = ColorDrawable(ColorUtils.randomColor)
            models.add(model)
        }
        mBannerAdapter.data = models
        mViewPager.setAdapterDataSize(models.size)
    }
}
