package us.wili.qtwallpaper.connect.apiResult

/**
 * ApiBaseResult
 * Created by jianqiu on 5/23/17.
 */
data class BaseResult<T>(val t: T) {

    lateinit var results: List<T>

}