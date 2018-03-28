package com.mysiga.common.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mysiga.http.RetrofitHelper;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * BASE_URL = RequestConstants.getApiUrl()
 */

public abstract class BaseApiRequestBuilder<T> {
    /**
     * 默认一页加载20条数据
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    protected T mServer = null;


    public BaseApiRequestBuilder(@NonNull Context context) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mServer = RetrofitHelper.create(entityClass, NetConstant.getUrl(), new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        // TODO: 2018/3/28 token
//                        .addHeader("token", AppUtils.getToken())
                        .build();
                return chain.proceed(request);
            }
        }, new NewResponseInterceptor(context));
    }
}
