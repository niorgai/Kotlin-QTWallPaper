package us.wili.qtwallpaper.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import qiu.niorgai.tools.ScreenUtils
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
class HotCategoriesBanner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    private var mViewPager: UnlimitedViewPager
    private var mBannerAdapter: UnlimitedBannerAdapter
    private var mDotsLayout: ViewGroup
    private var widgetHeight: Int
    private var preIndex: Int = 0
    private var dotParams: LinearLayout.LayoutParams

    init {
        inflate(context, R.layout.layout_hot_categories, this)
        visibility = View.GONE
        mViewPager = findViewById(R.id.view_pager)
        mBannerAdapter = UnlimitedBannerAdapter()
        mViewPager.adapter = mBannerAdapter
        mViewPager.addOnPageChangeListener(this)
        mDotsLayout = findViewById(R.id.dots_layout)

        val metrics = resources.displayMetrics
        widgetHeight = (((metrics.widthPixels / 640f) * 380f).toInt())

        val size = ScreenUtils.dip2px(6f)
        dotParams = LinearLayout.LayoutParams(size, size)
        dotParams.leftMargin = size / 2
        dotParams.rightMargin = size / 2

    }

    fun refreshBanner(data: List<CategoryItem>?) {
        if (data == null || data.isEmpty()) {
            visibility = View.GONE
            return
        }
        visibility = View.VISIBLE
        layoutParams.height = widgetHeight

        mViewPager.stopAutoScroll()
        mDotsLayout.removeAllViewsInLayout()
        val models = ArrayList<ViewPagerModel>()
        data.forEachIndexed { index, item ->
            val model = ViewPagerModel()
            model.aspectRatio = 1.684210526f
            model.uri = Uri.parse(item.coverUrl)
            model.holder = ColorDrawable(ColorUtils.randomColor)
            models.add(model)

            val dot = ImageView(context)
            dot.setBackgroundResource(if (index == 0) R.drawable.dot_focused_grey else R.drawable.dot_normal_white)
            mDotsLayout.addView(dot, dotParams)
        }
        preIndex = 0
        mBannerAdapter.data = models
        mViewPager.setAdapterDataSize(models.size)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        (mDotsLayout.getChildAt(preIndex) as ImageView).setImageResource(R.drawable.dot_normal_white)
        (mDotsLayout.getChildAt(mViewPager.currentPos) as ImageView).setImageResource(R.drawable.dot_focused_grey)
        preIndex = mViewPager.currentPos
    }
}
