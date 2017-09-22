package us.wili.qtwallpaper.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
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

        Glide.with(holder.imageView.context).load(getItem(position)!!.imageUrl).into(holder.imageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.image)

    }
}