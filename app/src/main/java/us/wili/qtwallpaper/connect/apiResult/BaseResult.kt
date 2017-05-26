package us.wili.qtwallpaper.connect.apiResult

/**
 * ApiBaseResult
 * Created by jianqiu on 5/23/17.
 */
data class BaseResult<T>(val t: T) {

    val code: Int = 0

    val error: String? = null

    val results: List<T>? = null

}