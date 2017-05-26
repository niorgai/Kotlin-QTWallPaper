package us.wili.qtwallpaper.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Category Item data
 * Created by jianqiu on 5/23/17.
 */
@Entity(tableName = "Category")
data class CategoryItem(
        @PrimaryKey var objectId: String,
        @ColumnInfo(index = true, name = COLUMN_IS_HOT) var isHot: Boolean,
        var order: Int,
        var name: String,
        var coverUrl: String) {

    companion object {
        const val TABLE_NAME: String = "Category"
        const val COLUMN_IS_HOT: String = "isHot"
    }
}