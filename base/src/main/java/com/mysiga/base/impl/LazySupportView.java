package com.mysiga.base.impl;

/**
 * Created by Wilson on 2018/3/15.
 */

public class LazySupportView {
    private ISupportView mISupportView;
    private boolean mIsInited;
    private boolean mIsPrepared;
    private boolean mVisibleToUser;

    public LazySupportView(ISupportView ISupportView) {
        mISupportView = ISupportView;
    }

    public void onCreateView() {
        mIsPrepared = true;
        lazyLoad();
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        mVisibleToUser = isVisibleToUser;
        if (mVisibleToUser) {
            lazyLoad();
        }
    }

    public void lazyLoad() {
        if (mVisibleToUser && mIsPrepared) {
            if (!mIsInited) {
                mIsInited = true;
                mISupportView.onLazyInitView();
            }
            mISupportView.onFragmentShow();
        }
    }
}
