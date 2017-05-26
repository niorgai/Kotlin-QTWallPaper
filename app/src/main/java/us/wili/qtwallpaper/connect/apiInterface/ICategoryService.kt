package us.wili.qtwallpaper.connect.apiInterface

import retrofit2.Call
import retrofit2.http.GET
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.model.CategoryItem

/**
 * Category
 * Created by jianqiu on 5/23/17.
 */
interface ICategoryService {

    @GET("classes/Category?where=%7B%22isHot%22%3Atrue%7D&&&&&")
    fun getHotCategory(): Call<BaseResult<CategoryItem>>
}