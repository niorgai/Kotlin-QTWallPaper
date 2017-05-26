package us.wili.qtwallpaper.connect

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import us.wili.qtwallpaper.BuildConfig
import us.wili.qtwallpaper.base.AppConstant
import us.wili.qtwallpaper.connect.apiConverter.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * custom http client
 * Created by jianqiu on 5/23/17.
 */
class AppClient {

    companion object {

        private var client: Retrofit? = null

        fun getInstance(): Retrofit {

            if (client == null) {
                val builder = OkHttpClient.Builder()

                //add Log
                builder.addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE })

                //add Header
                val headerInterceptor = Interceptor {chain ->
                    val originRequest: Request = chain.request()
                    val requestBuilder = originRequest.newBuilder()
                            .addHeader("X-LC-Id", AppConstant.AV_APP_ID)
                            .addHeader("X-LC-Key", AppConstant.AV_APP_KEY)
                            .method(originRequest.method(), originRequest.body())
                            .build()
                    val response: Response = chain.proceed(requestBuilder)
                    val originJson: String? = response.body()?.string()
                    val newResponse: Response = response.newBuilder()
                            .body(ResponseBody.create(response.body()?.contentType(), originJson!!))
                            .message(originJson)
                            .build()

                    return@Interceptor newResponse
                }
                builder.addInterceptor(headerInterceptor)

                builder.connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(false)

                client = Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl("https://leancloud.cn:443/1.1/")
                        .addConverterFactory(GsonConverterFactory())
                        .build()
            }
            return client!!

        }
    }

}