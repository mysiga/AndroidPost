package com.mysiga.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public abstract class MVPActivity<P extends IPresenter> extends AppCompatActivity implements IView, Init<P> {

    protected P mPresenter;

    @Override
    public int getMenuRes() {
        return INVALID_MENU;
    }

    @Override
    public abstract P createPresenter();

    @Override
    public void initViews(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initMenus(Menu menu) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void requestData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutRes = getLayoutRes();
        if (INVALID_LAYOUT != layoutRes) {
            setContentView(getLayoutRes());
        }
        initData(savedInstanceState);
        mPresenter = createPresenter();
        initViews(null, savedInstanceState);

        if (getMenuRes() == INVALID_MENU) {
            //如果没menu，则可以开始初始请求网络数据，
            // 如果有menu则需待menu初始化后在请求数据，以免数据回来时需要操纵menu按钮
            requestData();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 请使用initMenus(Menu menu)
     */
    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        int menuRes = getMenuRes();
        if (menuRes != INVALID_MENU) {
            getMenuInflater().inflate(menuRes, menu);
            initMenus(menu);
            requestData();
        }
        return true;
    }
}
