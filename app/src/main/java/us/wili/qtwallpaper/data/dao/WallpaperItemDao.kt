package us.wili.qtwallpaper.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * dao for wallpaper item
 * Created by jianqiu on 6/29/17.
 */
@Dao interface WallpaperItemDao {

    /**
     * get wallpapers for special Category
     */
    @Query("SELECT * FROM " + WallpaperItem.TABLE_NAME)
    fun getWallpapers(): List<WallpaperItem>

    /**
     * get hot wallpapers
     */
    @Query("SELECT * FROM " + WallpaperItem.TABLE_NAME + " ORDER BY " + WallpaperItem.COLUMN_DOWNLOADS + " DESC LIMIT 36")
    fun getHotWallpapers(): List<WallpaperItem>

    /**
     * insert wallpapers, use for cache
     */
    @Insert
    fun insertAll(item: List<WallpaperItem>)
}