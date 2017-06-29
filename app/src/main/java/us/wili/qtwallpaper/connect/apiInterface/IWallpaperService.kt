package us.wili.qtwallpaper.connect.apiInterface;

import retrofit2.Call
import retrofit2.http.GET
import us.wili.qtwallpaper.connect.apiResult.BaseResult
import us.wili.qtwallpaper.data.model.WallpaperItem

/**
 * Wallpaper
 * Created by jianqiu on 6/29/17.
 */
public interface IWallpaperService {

    @GET("classes/Wallpaper?limit=36&&order=-downloads")
    fun getHotWallpaper(): Call<BaseResult<WallpaperItem>>
}
