package us.wili.qtwallpaper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import us.wili.qtwallpaper.base.QtApplication
import us.wili.qtwallpaper.connect.ApiCallback
import us.wili.qtwallpaper.connect.AppClient
import us.wili.qtwallpaper.connect.apiInterface.ICategoryService
import us.wili.qtwallpaper.connect.apiInterface.IWallpaperService
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.data.QTDatabase
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * hot fragment
 * Created by jianqiu on 5/25/17.
 */
class HotViewModel: ViewModel() {

    private val observableCategories: MutableLiveData<List<CategoryItem>> = MutableLiveData()
    private val observableWallpapers: MutableLiveData<List<WallpaperItem>> = MutableLiveData()

    private val iCategoryService: ICategoryService = AppClient.getInstance().create(ICategoryService::class.java)
    private val iWallpaperService: IWallpaperService = AppClient.getInstance().create(IWallpaperService::class.java)

    private val categoryCallback: ApiCallback<BaseResult<CategoryItem>> = object : ApiCallback<BaseResult<CategoryItem>>() {
        override fun onSuccess(result: BaseResult<CategoryItem>) {
            super.onSuccess(result)
            observableCategories.value = result.results
            QtApplication.getExecutors().submit {
                Runnable {
                    val database: QTDatabase = QTDatabase.getDatabase()
                    database.beginTransaction()
                    try {
                        database.getCategoryDao().insertAll(result.results!!)
                        database.setTransactionSuccessful()
                    } finally {
                        database.endTransaction()
                    }
                }
            }
        }

        override fun onFail(errorText: String?) {
            super.onFail(errorText)
            observableCategories.value = QTDatabase.getDatabase().getCategoryDao().getHotCategories()
        }
    }

    private val wallpaperCallback: ApiCallback<BaseResult<WallpaperItem>> = object : ApiCallback<BaseResult<WallpaperItem>>() {
        override fun onSuccess(result: BaseResult<WallpaperItem>) {
            super.onSuccess(result)
            observableWallpapers.value = result.results
            QtApplication.getExecutors().submit {
                Runnable {
                    val database: QTDatabase = QTDatabase.getDatabase()
                    database.beginTransaction()
                    try {
                        database.getWallpaperDao().insertAll(result.results!!)
                        database.setTransactionSuccessful()
                    } finally {
                        database.endTransaction()
                    }
                }
            }
        }

        override fun onFail(errorText: String?) {
            super.onFail(errorText)
            observableWallpapers.value = QTDatabase.getDatabase().getWallpaperDao().getHotWallpapers()
        }
    }

    fun refresh() {
        iCategoryService.getHotCategory().enqueue(categoryCallback)
        iWallpaperService.getHotWallpaper().enqueue(wallpaperCallback)
    }

    fun getCategories(): LiveData<List<CategoryItem>> = observableCategories

    fun getWallpapers(): LiveData<List<WallpaperItem>> = observableWallpapers

}