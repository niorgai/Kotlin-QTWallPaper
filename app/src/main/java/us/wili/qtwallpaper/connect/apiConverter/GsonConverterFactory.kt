package us.wili.qtwallpaper.connect.apiConverter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * use INSTANCE to convert json
 * Created by jianqiu on 5/24/17.
 */
class GsonConverterFactory: Converter.Factory() {

    companion object {
        private var gson: Gson? = null

        @Synchronized fun getInstance(): Gson {
            if (gson == null) {
                gson = GsonBuilder()
                        .registerTypeAdapterFactory(BooleanTypeAdapter.FACTORY)
                        .create();
            }
            return gson!!
        }
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return GsonResponseBodyConverter(getInstance(), getInstance().getAdapter(TypeToken.get(type))!!)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return GsonRequestBodyConverter(getInstance(), getInstance().getAdapter(TypeToken.get(type))!!)
    }

}