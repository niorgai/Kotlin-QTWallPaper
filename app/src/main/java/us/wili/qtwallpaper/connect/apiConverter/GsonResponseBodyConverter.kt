package us.wili.qtwallpaper.connect.apiConverter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * convert response body
 * Created by jianqiu on 5/24/17.
 */
class GsonResponseBodyConverter<T>(var gson: Gson, var adapter: TypeAdapter<T>): Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody?): T {
        value.use {
            val reader: JsonReader = gson.newJsonReader(value?.charStream())
            return adapter.read(reader)
        }

    }

}