package us.wili.qtwallpaper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

/**
 * ArrayAdapter for RecyclerView
 * Created by jianqiu on 6/19/17.
 */
abstract class ArrayRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var mObjects: ArrayList<T> = ArrayList()

    private var inflater: LayoutInflater? = null

    protected fun getLayoutInflater(context: Context): LayoutInflater {
        if (inflater == null) {
            inflater = LayoutInflater.from(context)
        }
        return inflater!!
    }

    public fun addAll(objects: Collection<T>?) {
        if (objects == null || objects.isEmpty()) {
            return
        }
        mObjects.addAll(objects)
        notifyDataSetChanged()
    }

    public fun clear() {
        mObjects.clear()
        notifyDataSetChanged()
    }

    public fun getItem(position: Int): T? {
        if (position < mObjects.size) {
            return mObjects.get(position)
        }
        return null
    }

    override fun getItemCount(): Int {
        return mObjects.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH? {
        return null
    }

}