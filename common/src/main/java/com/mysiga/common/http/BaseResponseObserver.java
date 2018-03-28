package com.mysiga.common.http;


import android.content.Context;

import con.mysiga.utils.ToastUtils;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.mysiga.common.http.NetConstant.SUCCESS;

/**
 * Created by Wilson on 2018/3/13.
 */

public abstract class BaseResponseObserver<T> implements SingleObserver<BaseResponse<T>> {
    private Context mContext;

    public BaseResponseObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(BaseResponse<T> tBaseResponse) {
        String resultCode = tBaseResponse.getResultCode();
        if (SUCCESS.equals(resultCode)) {
            onBusinessSuccess(tBaseResponse.getData());
        } else {
            onBusinessFail(tBaseResponse);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        ToastUtils.showLongToastSafe(mContext, e.getMessage());
    }

    public void onBusinessFail(BaseResponse<T> tBaseResponse) {
        String resultMsg = tBaseResponse.getResultMsg();
        if (resultMsg != null && !"".equals(resultMsg)) {
            ToastUtils.showLongToastSafe(mContext, resultMsg);
        }
    }

    /**
     * 业务请求数据成功
     *
     * @param data
     */
    public abstract void onBusinessSuccess(T data);
}
