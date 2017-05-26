package us.wili.qtwallpaper.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import us.wili.qtwallpaper.data.dao.CategoryItemDao
import us.wili.qtwallpaper.data.model.CategoryItem

/**
 * database for app
 * Created by jianqiu on 5/26/17.
 */
@Database(entities = arrayOf(CategoryItem::class), version = 1)
abstract class QTDatabase: RoomDatabase() {

    companion object {

        private val NAME: String = "QTDatabase"

        private var instance: QTDatabase? = null

        @Synchronized fun getInstance(context: Context): QTDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, QTDatabase::class.java, NAME)
                        .build()
            }
            return instance!!
        }

    }

    abstract fun getCategoryDao(): CategoryItemDao
}