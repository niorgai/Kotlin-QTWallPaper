package us.wili.qtwallpaper.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Category Item data
 * Created by jianqiu on 5/23/17.
 */
@Entity(tableName = "Category")
class CategoryItem {

    @PrimaryKey var objectId: String = ""
        get
        set
    @ColumnInfo(index = true, name = COLUMN_IS_HOT) var isHot: Boolean = false
        get
        set
    var order: Int = 0
        get
        set
    var name: String = ""
        get
        set
    var coverUrl: String = ""
        get
        set

    companion object {
        const val TABLE_NAME: String = "Category"
        const val COLUMN_IS_HOT: String = "isHot"
    }
}