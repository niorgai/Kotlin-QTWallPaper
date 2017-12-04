package us.wili.qtwallpaper.data

import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * data repo interface
 * Created by jianqiu on 12/4/17.
 */
interface DataRepo {

    fun getHotWallPaper(): List<WallpaperItem>

    fun getWallPaperFromCategory(): List<WallpaperItem>

    fun getHotCategory(): List<CategoryItem>

    fun getAllCategory(): List<CategoryItem>

}