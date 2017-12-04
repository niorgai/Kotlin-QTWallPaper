package us.wili.qtwallpaper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import qiu.niorgai.runtime.ThreadManager
import us.wili.qtwallpaper.data.remote.connect.ApiCallback
import us.wili.qtwallpaper.data.remote.connect.AppClient
import us.wili.qtwallpaper.data.remote.connect.apiInterface.IWallpaperService
import us.wili.qtwallpaper.data.remote.connect.apiResult.BaseResult
import us.wili.qtwallpaper.data.local.QTDatabase
import us.wili.qtwallpaper.data.model.WallpaperItem
import java.net.URLEncoder

/**
 * Category Detail
 * Created by jianqiu on 11/6/17.
 */
class CategoryDetailViewModel : ViewModel() {
    private val observableWallpapers: MutableLiveData<List<WallpaperItem>> = MutableLiveData()

    private val iWallpaperService: IWallpaperService = AppClient.getInstance().create(IWallpaperService::class.java)

    private val wallpaperCallback: ApiCallback<BaseResult<WallpaperItem>> = object : ApiCallback<BaseResult<WallpaperItem>>() {
        override fun onSuccess(result: BaseResult<WallpaperItem>) {
            super.onSuccess(result)
            observableWallpapers.value = (result.results)
//            ThreadManager.getInstance().executorService.execute ({
//                val database: QTDatabase = QTDatabase.getDatabase()
//                database.beginTransaction()
//                try {
//                    database.getWallpaperDao().insertAll(result.results!!)
//                    database.setTransactionSuccessful()
//                } finally {
//                    database.endTransaction()
//                }
//            }
//            )
        }

        override fun onFail(errorText: String?) {
            super.onFail(errorText)
            ThreadManager.getInstance().executorService.execute({
                observableWallpapers.postValue(QTDatabase.getDatabase().getWallpaperDao().getHotWallpapers())
            })
        }
    }

    fun refresh(categoryId: String) {
        val all = "Wallpaper?where={\"categoryId\":{\"__type\":\"Pointer\",\"className\":\"Category\",\"objectId\":\"$categoryId\"}}"
        iWallpaperService.getWallpaperFromCategory(URLEncoder.encode(all)).enqueue(wallpaperCallback)
    }


    fun getWallpapers(): LiveData<List<WallpaperItem>> = observableWallpapers

}