package us.wili.qtwallpaper.connect.apiConverter

import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import us.wili.qtwallpaper.connect.apiConverter.GsonUtil.gson
import java.lang.reflect.Type

/**
 * use gson to convert json
 * Created by jianqiu on 5/24/17.
 */
class GsonConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return GsonResponseBodyConverter(gson, gson.getAdapter(TypeToken.get(type))!!)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return GsonRequestBodyConverter(gson, gson.getAdapter(TypeToken.get(type))!!)
    }

}