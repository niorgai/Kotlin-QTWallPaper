package us.wili.qtwallpaper.data.remote.connect.apiConverter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonWriter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.io.Writer

/**
 * convert request body
 * Created by jianqiu on 5/24/17.
 */
class GsonRequestBodyConverter<T>(var gson: Gson, var adapter: TypeAdapter<T>) :Converter<T, RequestBody> {

    val MEDIA_TYPE: MediaType = MediaType.parse("application/json; charset=UTF-8")!!

    override fun convert(value: T): RequestBody {
        val buffer: Buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), Charsets.UTF_8)
        val jsonWriter: JsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }

}