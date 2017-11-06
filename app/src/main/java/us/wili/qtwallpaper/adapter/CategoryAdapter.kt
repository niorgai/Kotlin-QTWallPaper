package us.wili.qtwallpaper.adapter

import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import qiu.niorgai.tools.image.ImageLoader
import qiu.niorgai.tools.image.ImageLoaderFactory
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.activity.CategoryDetailActivity
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.tool.ColorUtils

/**
 * Adapter for CategoryFragment
 * Created by jianqiu on 6/19/17.
 */
class CategoryAdapter: ArrayRecyclerAdapter<CategoryItem, CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = getLayoutInflater(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val loader = ImageLoader()
                .setHolder(ColorDrawable(ColorUtils.randomColor))
                .setAspectRatio(1.684210526f)
                .setUri(getItem(position)!!.coverUrl)
                .setTarget(holder.imageView)
        ImageLoaderFactory.getInstance().loadImage(loader)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(v: View?) {
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return
            }
            val model: CategoryItem = getItem(adapterPosition)!!
            v!!.context.startActivity(CategoryDetailActivity.newIntent(v.context, model.name, model.objectId))
        }

        val imageView: us.wili.qtwallpaper.tool.ImageView = itemView.findViewById(R.id.image)

        init {
            view.setOnClickListener(this)
        }

    }
}