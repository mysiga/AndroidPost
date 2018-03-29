package com.mysiga.post.application;

import android.support.multidex.MultiDexApplication;

import com.mysiga.share.wechat.WeChatShare;

/**
 * Created by Wilson on 2018/3/29.
 */

public class MainApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initWechatShare();
    }

    /**
     * 初始化微信分享
     */
    private void initWechatShare() {
        WeChatShare.getInstance().init(this);
    }
}
