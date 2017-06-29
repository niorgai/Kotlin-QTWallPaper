package us.wili.qtwallpaper.data.dao

import android.arch.persistence.room.Dao
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
    fun getWallpapers() : List<WallpaperItem>
}