package us.wili.qtwallpaper.data.remote.connect

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.wili.qtwallpaper.data.remote.connect.apiResult.BaseResult

/**
 * custom kotlin callback
 * Created by jianqiu on 5/25/17.
 */
open class ApiCallback<T>: Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFail(t.toString())
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        val result = response?.body()
        if (result == null || result !is BaseResult<*> || result.code != 0) {
            onFail()
            return
        }
        if (result.error != null && !result.error.isEmpty()) {
            onFail(result.error)
            return
        }
        onSuccess(result)
    }

    fun onFail() {
        onFail(null)
    }

    open fun onFail(errorText: String?) {

    }

    open fun onSuccess(result: T) {

    }

}