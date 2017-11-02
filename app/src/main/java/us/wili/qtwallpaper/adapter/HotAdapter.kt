package us.wili.qtwallpaper.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import qiu.niorgai.tools.image.ImageLoader
import qiu.niorgai.tools.image.ImageLoaderFactory
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * Adapter for HotFragment
 * Created by jianqiu on 6/19/17.
 */
class HotAdapter: ArrayRecyclerAdapter<WallpaperItem, HotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(getLayoutInflater(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val loader = ImageLoader().setUri(getItem(position)!!.imageUrl).setTarget(holder.imageView)
        ImageLoaderFactory.getInstance().loadImage(loader)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.image)

    }
}