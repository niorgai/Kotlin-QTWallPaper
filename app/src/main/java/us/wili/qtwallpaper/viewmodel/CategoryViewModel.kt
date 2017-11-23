package us.wili.qtwallpaper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import qiu.niorgai.runtime.ThreadManager
import us.wili.qtwallpaper.connect.ApiCallback
import us.wili.qtwallpaper.connect.AppClient
import us.wili.qtwallpaper.connect.apiInterface.ICategoryService
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.data.QTDatabase
import us.wili.qtwallpaper.data.model.CategoryItem

/**
 * category fragment
 * Created by jianqiu on 5/25/17.
 */
class CategoryViewModel: ViewModel() {

    private val observableCategories: MutableLiveData<List<CategoryItem>> = MutableLiveData()

    private val service: ICategoryService = AppClient.getInstance().create(ICategoryService::class.java)

    private val callback: ApiCallback<BaseResult<CategoryItem>> = object : ApiCallback<BaseResult<CategoryItem>>() {
        override fun onSuccess(result: BaseResult<CategoryItem>) {
            super.onSuccess(result)
            observableCategories.value = result.results
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
            })
        }
    }

    public fun refresh() {
        service.getAllCategory().enqueue(callback)
    }

    fun getCategories(): LiveData<List<CategoryItem>> {
        return observableCategories
    }

}