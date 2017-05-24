package us.wili.qtwallpaper.connect.apiInterface

import retrofit2.Call
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.model.CategoryItem

/**
 * Category
 * Created by jianqiu on 5/23/17.
 */
interface CategoryService {

    fun getHotCategory(): Call<BaseResult<CategoryItem>>
}