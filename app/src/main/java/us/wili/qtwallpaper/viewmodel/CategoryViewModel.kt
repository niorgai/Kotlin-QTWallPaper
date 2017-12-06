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
import us.wili.qtwallpaper.data.remote.connect.ApiCallback
import us.wili.qtwallpaper.data.remote.connect.AppClient
import us.wili.qtwallpaper.data.remote.connect.apiInterface.ICategoryService
import us.wili.qtwallpaper.data.remote.connect.apiResult.BaseResult

/**
 * category fragment
 * Created by jianqiu on 5/25/17.
 */
class CategoryViewModel: ViewModel() {

    companion object {

        @BindingAdapter("android:refreshing")
        @JvmStatic
        fun setRefreshing(view: SwipeRefreshLayout, refresh: Boolean) {
            view.isRefreshing = refresh
        }

        @JvmStatic
        @BindingAdapter("android:listener")
        fun setListener(view: SwipeRefreshLayout, model:CategoryViewModel) {
            view.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { model.refresh() })
        }
    }

    public var isRefreshing: ObservableBoolean = ObservableBoolean(false)

    private val observableCategories: MutableLiveData<List<CategoryItem>> = MutableLiveData()

    private val service: ICategoryService = AppClient.getInstance().create(ICategoryService::class.java)

    private val callback: ApiCallback<BaseResult<CategoryItem>> = object : ApiCallback<BaseResult<CategoryItem>>() {
        override fun onSuccess(result: BaseResult<CategoryItem>) {
            super.onSuccess(result)
            observableCategories.value = result.results
            isRefreshing.set(false)
            ThreadManager.getInstance().executorService.execute ({ val database: QTDatabase = QTDatabase.getDatabase()
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
                observableCategories.postValue(QTDatabase.getDatabase().getCategoryDao().getAllCategories())
                isRefreshing.set(false)
            })
        }
    }

    fun refresh() {
        isRefreshing.set(true)
        service.getAllCategory().enqueue(callback)
    }

    fun getCategories(): LiveData<List<CategoryItem>> = observableCategories

}