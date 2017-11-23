package us.wili.qtwallpaper.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import us.wili.qtwallpaper.data.model.CategoryItem

/**
 * dao for category item
 * Created by jianqiu on 5/26/17.
 */
@Dao interface CategoryItemDao {

    /**
     * get hot categories
     */
    @Query("SELECT * FROM " + CategoryItem.TABLE_NAME + " WHERE " + CategoryItem.COLUMN_IS_HOT + " = 1")
    fun getHotCategories(): List<CategoryItem>

    /**
     * get All categories
     */
    @Query("SELECT * FROM " + CategoryItem.TABLE_NAME)
    fun getAllCategories(): List<CategoryItem>

    /**
     * insert categories, use for cache
     */
    @Insert
    fun insertAll(item: List<CategoryItem>)
}