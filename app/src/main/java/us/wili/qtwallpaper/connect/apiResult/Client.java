package us.wili.qtwallpaper.connect.apiResult;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import us.wili.qtwallpaper.BuildConfig;
import us.wili.qtwallpaper.base.AppConstant;

/**
 * AppClient
 * Created by jianqiu on 11/6/17.
 */
public class Client {

    private static volatile Client sInstance;

    public static Client getInstance() {
        if (sInstance == null) {
            synchronized (Client.class) {
                if (sInstance == null) {
                    sInstance = new Client();
                }
            }
        }
        return sInstance;
    }

    private OkHttpClient mClient;

    private Client() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request.Builder requestBuilder = originRequest.newBuilder()
                        .addHeader("X-LC-Id", AppConstant.INSTANCE.getAV_APP_ID())
                        .addHeader("X-LC-Key", AppConstant.INSTANCE.getAV_APP_KEY())
                        .method(originRequest.method(), originRequest.body());

                return chain.proceed(requestBuilder.build());
            }
        };
        builder.addInterceptor(interceptor);

        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
        mClient = builder.build();

    }
}
