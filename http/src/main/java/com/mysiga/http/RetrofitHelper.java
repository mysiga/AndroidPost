package com.mysiga.http;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final int MAX_TIME = 60;

    public static <T> T create(final Class<T> service, String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(MAX_TIME, TimeUnit.SECONDS)
                .readTimeout(MAX_TIME, TimeUnit.SECONDS)
                .writeTimeout(MAX_TIME, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service);
    }

    public static <T> T create(final Class<T> service, String baseUrl, Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (interceptors.length > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        OkHttpClient client =
                builder.connectTimeout(MAX_TIME, TimeUnit.SECONDS)
                        .readTimeout(MAX_TIME, TimeUnit.SECONDS)
                        .writeTimeout(MAX_TIME, TimeUnit.SECONDS)
                        .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service);
    }

}
