package us.wili.qtwallpaper.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import us.wili.qtwallpaper.R
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.image.QTImageView

/**
 * Adapter for HotFragment
 * Created by jianqiu on 6/19/17.
 */
class HotAdapter: ArrayRecyclerAdapter<CategoryItem, HotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(getLayoutInflater(parent.context).inflate(R.layout.item_grid, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        Glide.with(holder.imageView.context).load(getItem(position)!!.coverUrl).into(holder.imageView)
    }

    class ViewHolder: RecyclerView.ViewHolder {

        val imageView: QTImageView

        constructor (itemView: View) : super(itemView) {
            imageView = itemView.findViewById(R.id.image) as QTImageView
        }
    }
}