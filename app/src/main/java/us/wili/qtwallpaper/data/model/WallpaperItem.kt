package us.wili.qtwallpaper.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Wallpaper item
 * Created by jianqiu on 6/23/17.
 */
@Entity(tableName = WallpaperItem.TABLE_NAME)
class WallpaperItem {

    @PrimaryKey
    @ColumnInfo(name = COLUMN_OBJECT_ID) var objectId: String = ""
        get
        set
    @ColumnInfo(name = COLUMN_ORDER) var order: Int = 0
        get
        set
    @ColumnInfo(name = COLUMN_DOWNLOADS) var downloads: Int = 0
        get
        set
    @ColumnInfo(name = COLUMN_NAME) var name: String = ""
        get
        set
    @ColumnInfo(name = COLUMN_CATEGORY_ID) var categoryId: String? = null
        get
        set
    @ColumnInfo(name = COLUMN_IMAGE_URL) var imageUrl: String = ""
        get
        set

    companion object {
        const val TABLE_NAME = "Wallpaper"
        const val COLUMN_OBJECT_ID = "objectId"
        const val COLUMN_ORDER = "order"
        const val COLUMN_DOWNLOADS = "downloads"
        const val COLUMN_CATEGORY_ID = "categoryId"
        const val COLUMN_IMAGE_URL = "imageUrl"
        const val COLUMN_NAME = "name"
    }

    fun getCompressedImageUrl(): String = imageUrl + "?imageMogr2/thumbnail/!20p";

}