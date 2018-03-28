package com.mysiga.common.http;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 在此检测返回数据是否合法,错误提示,非正常格式数据预处理
 */
public class NewResponseInterceptor implements Interceptor {
    private Context mContext;

    public NewResponseInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String requestUrl = request.url().toString();

        Response response = chain.proceed(request);
        MediaType mediaType = response.body().contentType();


        String result = response.body().string();
        Log.i("info", "请求地址：" + requestUrl);
        Log.i("info", "请求结果：" + result);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            int resultCode = jsonObject.optInt("resultCode");
            if (resultCode != NetConstant.CODE_SUCCESS) {

                if (resultCode == NetConstant.CODE_NO_LOGIN) {
                    //未登录
//                    ActivitiesManager.getInstance().popAllActivities();
//                    ARouter.getInstance().build(ARouterConstants.LOGIN_LOGIN).navigation();
//                    LoginUtils.putCookie(mContext.getApplicationContext(), null);
//                    LoginUtils.putUserInfo(mContext.getApplicationContext(), null);
//                    ToastUtils.show("登录失效,请重新登录!");
                } else {
//                    String resultMsg = jsonObject.optString("resultMsg");
//                    String data = jsonObject.optString("data");
//                    if (StringUtils.isNotEmpty(data)) {
//                        ToastUtils.show(data);
//                    } else if (StringUtils.isNotEmpty(resultMsg)) {
//                        ToastUtils.show(resultMsg);
//                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Response.Builder resBuilder = response.newBuilder();
        resBuilder.body(ResponseBody.create(mediaType, result));

        return resBuilder.build();
    }

}
