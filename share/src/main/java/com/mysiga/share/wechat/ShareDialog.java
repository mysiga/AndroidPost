package com.mysiga.share.wechat;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mysiga.share.R;

/**
 * 分享对话框
 */

public class ShareDialog extends AlertDialog {
    private Context mContext;
    private View.OnClickListener mWeChatSessionListener;
    private View.OnClickListener mWeChatFriendListener;
    private View mContentView;

    public ShareDialog(@NonNull Context context) {
        this(context, R.style.SelectionDialog);
    }

    public ShareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public ShareDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
        }
        super.show();
        if (mContentView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            mContentView = layoutInflater.inflate(R.layout.dialog_share, null);
            TextView weChatSession = mContentView.findViewById(R.id.wechat_session);
            weChatSession.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (mWeChatSessionListener != null) {
                        mWeChatSessionListener.onClick(v);
                    }
                }
            });
            TextView weChatFriend = mContentView.findViewById(R.id.wechat_friend);
            weChatFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (mWeChatFriendListener != null) {
                        mWeChatFriendListener.onClick(v);
                    }
                }
            });

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(getContext().getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
            setContentView(mContentView, params);
        }

    }


    public ShareDialog setWeChatSessionListener(@NonNull View.OnClickListener onClickListener) {
        mWeChatSessionListener = onClickListener;
        return this;
    }

    public ShareDialog setWeChatFriendListener(@NonNull View.OnClickListener onClickListener) {
        mWeChatFriendListener = onClickListener;
        return this;
    }
}
