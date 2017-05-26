package us.wili.qtwallpaper.connect.apiConverter

import com.google.gson.GsonBuilder

/**
 * gson single Instance
 * Created by jianqiu on 5/25/17.
 */
object GsonUtil {

    var gson = GsonBuilder()
            .registerTypeAdapterFactory(BooleanTypeAdapter.FACTORY)
            .create()

}