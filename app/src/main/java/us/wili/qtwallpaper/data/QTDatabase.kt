package us.wili.qtwallpaper.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import us.wili.qtwallpaper.base.QtApplication
import us.wili.qtwallpaper.data.dao.CategoryItemDao
import us.wili.qtwallpaper.data.dao.WallpaperItemDao
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * database for app
 * Created by jianqiu on 5/26/17.
 */
@Database(entities = arrayOf(CategoryItem::class, WallpaperItem::class), version = 1)
abstract class QTDatabase: RoomDatabase() {

    companion object {

        private val NAME: String = "QTDatabase"

        private var instance: QTDatabase? = null

        @Synchronized fun getDatabase(): QTDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(QtApplication.getRuntimeContext(), QTDatabase::class.java, NAME)
                        .build()
            }
            return instance!!
        }

    }

    abstract fun getCategoryDao(): CategoryItemDao

    abstract fun getWallpaperDao(): WallpaperItemDao
}