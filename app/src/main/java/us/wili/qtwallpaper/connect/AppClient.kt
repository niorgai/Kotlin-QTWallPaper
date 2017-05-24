package us.wili.qtwallpaper.connect

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import us.wili.qtwallpaper.BuildConfig
import us.wili.qtwallpaper.connect.apiConverter.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * custom http client
 * Created by jianqiu on 5/23/17.
 */
class AppClient {

    var client: Retrofit? = null

    fun getInstance(): Retrofit {

        if (client == null) {
            val builder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }

            builder.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)

            client = Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory())
                    .build()
        }
        return client!!

    }

}