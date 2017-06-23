package us.wili.qtwallpaper.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Category Item data
 * Created by jianqiu on 5/23/17.
 */
@Entity(tableName = CategoryItem.TABLE_NAME)
class CategoryItem {

    @PrimaryKey
    @ColumnInfo(name = COLUMN_OBJECT_ID) var objectId: String = ""
        get
        set
    @ColumnInfo(index = true, name = COLUMN_IS_HOT) var isHot: Boolean = false
        get
        set
    @ColumnInfo(name = COLUMN_ORDER) var order: Int = 0
        get
        set
    @ColumnInfo(name = COLUMN_NAME) var name: String = ""
        get
        set
    @ColumnInfo(name = COLUMN_COVER_URL) var coverUrl: String = ""
        get
        set

    companion object {
        const val TABLE_NAME = "Category"
        const val COLUMN_OBJECT_ID = "objectId"
        const val COLUMN_IS_HOT = "isHot"
        const val COLUMN_ORDER = "order"
        const val COLUMN_NAME = "name"
        const val COLUMN_COVER_URL = "coverUrl"
    }
}