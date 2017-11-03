package us.wili.qtwallpaper.data

import android.arch.persistence.room.TypeConverter
import us.wili.qtwallpaper.connect.apiConverter.GsonConverterFactory
import us.wili.qtwallpaper.data.model.CategoryItem

/**
 * Type Converter of CategoryItem
 * Created by jianqiu on 11/3/17.
 */
public class CategoryConverter {



    @TypeConverter
    fun categoryToString(data: String) : CategoryItem = GsonConverterFactory.getInstance().fromJson(data, CategoryItem::class.java)

    @TypeConverter
    fun stringToCategory(item: CategoryItem) : String = GsonConverterFactory.getInstance().toJson(item)
}