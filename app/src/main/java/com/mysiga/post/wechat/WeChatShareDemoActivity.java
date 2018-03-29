package com.mysiga.post.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.mysiga.post.R;
import com.mysiga.share.wechat.ShareDialog;
import com.mysiga.share.wechat.WeChatShare;

/**
 * Created by Wilson on 2018/3/29.
 */

public class WeChatShareDemoActivity extends FragmentActivity {
    private ShareDialog mShareDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_share);
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareView();
            }
        });
    }

    private void showShareView() {
        if (mShareDialog == null) {
            mShareDialog = new ShareDialog(WeChatShareDemoActivity.this)
                    .setWeChatFriendListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WeChatShare.getInstance().shareWebPage(WeChatShare.WeChatType.WXSceneTimeline, "www.baidu.com", "测试", "测试详情", null);
                        }
                    }).setWeChatSessionListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WeChatShare.getInstance().shareWebPage(WeChatShare.WeChatType.WXSceneSession, "www.baidu.com", "测试", "测试详情", null);
                        }
                    });
        }
        if (mShareDialog.isShowing()) {
            return;
        }
        mShareDialog.show();
    }
}
