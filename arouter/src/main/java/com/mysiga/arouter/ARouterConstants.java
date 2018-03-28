package com.mysiga.arouter;

/**
 * 注册{@link com.alibaba.android.arouter.launcher.ARouter}路由表
 */

public interface ARouterConstants {

    /***************以下注册路由地址不能改，会影响覆盖安装****************/

    /**
     * 共用
     **/
    String COMMON_IMAGE = "/common/image";
    String WEB_WEB = "/web/web";

    /**
     * 登录
     **/
    String LOGIN_LOGIN = "/login/login";

    /**
     * 通知
     */
    String NOTIFICATION_LIST = "/notification/list";


    /**
     * 意见反馈路由地址
     */
    String HOME_FEEDBACK = "/home/feedback";

    String HOME = "/home/learn_and_schedule";


    String EXTRA_NUM = "num";
    /**
     * 动画requestCode，解决ARouter的bug：https://github.com/alibaba/ARouter/issues/262
     */
    int REQUEST_CODE_ANIM = 10001;
}
