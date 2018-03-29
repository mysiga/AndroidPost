# AndroidPost
###utils
总结[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)相关工具类
####[微信分享](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317340&token=&lang=zh_CN)
- 接入指南
[Android Studio环境下相对应的build.gradle文件中，添加如下依赖即可](./share/build.gradle)：
````
dependencies {
     compile 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+'
 }
````
[AndroidManifest.xml添加必要的权限支持](./app/src/main/AndroidManifest.xml)
````

<uses-permission android:name="android.permission.INTERNET"/>

<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

<uses-permission android:name="android.permission.READ_PHONE_STATE"/>

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
````
[注册分享Activity](./app/src/main/AndroidManifest.xml)
```
<!--微信回调页面-->
        <activity
            android:name=".wxapi.WXEntryActivity"
            android:exported="true"
            android:screenOrientation="portrait" />
```
导入[WXEntryActivity](./app/src/main/java/com/mysiga/post/wxapi/WXEntryActivity.java)文件
```
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
```
混淆（[proguard-rules.pro文件](./app/proguard-rules.pro)）
```
#微信分享混淆
-keep class com.tencent.mm.opensdk.** {*;}
-keep class com.tencent.wxop.** {*;}
-keep class com.tencent.mm.sdk.** {*;}
```
- 微信分享封装
    - [WeChatShare](./share/src/main/java/com/mysiga/share/wechat/WeChatShare.java):微信分享类型封装，请更换appid，
    - [ShareDialog](./share/src/main/java/com/mysiga/share/wechat/ShareDialog.java):分享选择页面
- 初始化
    需要在APP的Application的子类（[MainApplication](./app/src/main/java/com/mysiga/post/application/MainApplication.java)）的onCreate()方法初始化，如：
    ```
        /**
         * 初始化微信分享
         */
        private void initWechatShare() {
            WeChatShare.getInstance().init(this);
        }
    ```
- 调用demo    
    