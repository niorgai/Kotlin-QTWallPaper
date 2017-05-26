package us.wili.qtwallpaper.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import us.wili.qtwallpaper.connect.ApiCallback
import us.wili.qtwallpaper.connect.AppClient
import us.wili.qtwallpaper.connect.apiInterface.ICategoryService
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.model.CategoryItem

/**
 * hot fragment
 * Created by jianqiu on 5/25/17.
 */
class HotViewModel: ViewModel() {

    private val observableCategories: MutableLiveData<List<CategoryItem>> = MutableLiveData()

    init {
        val service: ICategoryService = AppClient.getInstance().create(ICategoryService::class.java)
        service.getHotCategory().enqueue(object : ApiCallback<BaseResult<CategoryItem>>() {
            override fun onSuccess(result: BaseResult<CategoryItem>) {
                super.onSuccess(result)
                observableCategories.value = result.results
            }

            override fun onFail(errorText: String?) {
                super.onFail(errorText)
                observableCategories.value = null
            }
        })
    }

    fun getCategories(): LiveData<List<CategoryItem>> = observableCategories

}