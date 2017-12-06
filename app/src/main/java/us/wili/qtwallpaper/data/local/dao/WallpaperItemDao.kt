package us.wili.qtwallpaper.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
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

    @Query("SELECT * FROM " + WallpaperItem.TABLE_NAME  +
            " WHERE " + WallpaperItem.COLUMN_CATEGORY_ID + " LIKE :categoryId" +
            " ORDER BY " + WallpaperItem.COLUMN_DOWNLOADS)
    fun getWallpapersFromCategoryId(categoryId: String): List<WallpaperItem>

    /**
     * insert wallpapers, use for cache
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<WallpaperItem>)
}