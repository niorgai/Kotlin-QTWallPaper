package us.wili.qtwallpaper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.support.v4.widget.SwipeRefreshLayout
import qiu.niorgai.runtime.ThreadManager
import us.wili.qtwallpaper.data.local.QTDatabase
import us.wili.qtwallpaper.data.model.CategoryItem
import us.wili.qtwallpaper.data.model.WallpaperItem
import us.wili.qtwallpaper.data.remote.connect.ApiCallback
import us.wili.qtwallpaper.data.remote.connect.AppClient
import us.wili.qtwallpaper.data.remote.connect.apiInterface.ICategoryService
import us.wili.qtwallpaper.data.remote.connect.apiInterface.IWallpaperService
import us.wili.qtwallpaper.data.remote.connect.apiResult.BaseResult

/**
 * hot fragment
 * Created by jianqiu on 5/25/17.
 */
class HotViewModel: ViewModel() {

    companion object {

        @JvmStatic
        @BindingAdapter("android:listener")
        fun setListener(view: SwipeRefreshLayout, model: HotViewModel) {
            view.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { model.refresh() })
        }
    }

    val isRefreshing: ObservableBoolean = ObservableBoolean(false)

    private val observableCategories: MutableLiveData<List<CategoryItem>> = MutableLiveData()
    private val observableWallpapers: MutableLiveData<List<WallpaperItem>> = MutableLiveData()

    private val iCategoryService: ICategoryService = AppClient.getInstance().create(ICategoryService::class.java)
    private val iWallpaperService: IWallpaperService = AppClient.getInstance().create(IWallpaperService::class.java)

    private val categoryCallback: ApiCallback<BaseResult<CategoryItem>> = object : ApiCallback<BaseResult<CategoryItem>>() {
        override fun onSuccess(result: BaseResult<CategoryItem>) {
            super.onSuccess(result)
            observableCategories.value = result.results
            isRefreshing.set(false)

            ThreadManager.getInstance().executorService.execute({
                val database: QTDatabase = QTDatabase.getDatabase()
                database.beginTransaction()
                try {
                    database.getCategoryDao().insertAll(result.results!!)
                    database.setTransactionSuccessful()
                } finally {
                    database.endTransaction()
                }
            })
        }


        override fun onFail(errorText: String?) {
            super.onFail(errorText)
            ThreadManager.getInstance().executorService.execute({
                observableCategories.postValue(QTDatabase.getDatabase().getCategoryDao().getHotCategories())
                isRefreshing.set(false)
            })
        }
    }

    private val wallpaperCallback: ApiCallback<BaseResult<WallpaperItem>> = object : ApiCallback<BaseResult<WallpaperItem>>() {
        override fun onSuccess(result: BaseResult<WallpaperItem>) {
            super.onSuccess(result)
            observableWallpapers.value = (result.results)
            isRefreshing.set(false)
            ThreadManager.getInstance().executorService.execute ({
                val database: QTDatabase = QTDatabase.getDatabase()
                database.beginTransaction()
                try {
                    database.getWallpaperDao().insertAll(result.results!!)
                    database.setTransactionSuccessful()
                } finally {
                    database.endTransaction()
                }
            }
            )
        }

        override fun onFail(errorText: String?) {
            super.onFail(errorText)
            ThreadManager.getInstance().executorService.execute({
                observableWallpapers.postValue(QTDatabase.getDatabase().getWallpaperDao().getHotWallpapers())
                isRefreshing.set(false)
            })
        }
    }

    fun refresh() {
        isRefreshing.set(true)
        iCategoryService.getHotCategory().enqueue(categoryCallback)
        iWallpaperService.getHotWallpaper().enqueue(wallpaperCallback)
    }

    fun getCategories(): LiveData<List<CategoryItem>> = observableCategories

    fun getWallpapers(): LiveData<List<WallpaperItem>> = observableWallpapers

}