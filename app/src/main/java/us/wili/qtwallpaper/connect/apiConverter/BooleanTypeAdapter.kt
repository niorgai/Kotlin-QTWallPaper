package us.wili.qtwallpaper.connect.apiConverter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * convert boolean & Boolean
 * Created by jianqiu on 5/24/17.
 */
class BooleanTypeAdapter: TypeAdapter<Boolean>() {

    companion object {
        val FACTORY: TypeAdapterFactory = object : TypeAdapterFactory {
            override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
                if (type?.rawType == Boolean::class.java || type?.rawType == Boolean::class.javaPrimitiveType) {
                    return BooleanTypeAdapter() as TypeAdapter<T>
                } else{
                    return null
                }
            }
        }
    }

    override fun write(out: JsonWriter?, value: Boolean?) {
        if (value == null) {
            out?.nullValue()
            return
        }
        out?.value(value.toString())
    }

    override fun read(`in`: JsonReader): Boolean {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return false
        }
        try {
            return `in`.nextBoolean()
        } catch (e: Exception){
            var value: String
            try {
                value = `in`.nextName()
            } catch (ex: Exception) {
                value = `in`.nextString()
            }
            if (value.trim() == "") {
                return false
            }
            return !(value.trim() == "0" || value.trim() == "false")
        }
    }

}