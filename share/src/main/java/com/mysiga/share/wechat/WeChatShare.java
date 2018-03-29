package com.mysiga.share.wechat;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 微信分享相关封装
 */

public class WeChatShare {
    // TODO: 2018/3/29 更换使用的app_id
    public static String WX_APP_ID = "111111111111";
    public IWXAPI mWxapi;
    private static WeChatShare mInstance;

    private WeChatShare() {

    }

    public static WeChatShare getInstance() {
        if (mInstance == null) {
            synchronized (WeChatShare.class) {
                if (mInstance == null) {
                    mInstance = new WeChatShare();
                }
            }
        }
        return mInstance;
    }

    /**
     * 在{@link Application#onCreate()}中初始化微信相关数据
     *
     * @param context
     * @return
     */
    public void init(@NonNull Context context) {
        //微信相关
        mWxapi = WXAPIFactory.createWXAPI(context.getApplicationContext(), WX_APP_ID, true);
        mWxapi.registerApp(WX_APP_ID);
    }

    public IWXAPI getWxapi() {
        if (mWxapi == null) {
            throw new NullPointerException("before  must be init(context)");
        }
        return mWxapi;
    }


    /**
     * 分享文本
     *
     * @param weChatType 微信类型
     * @param text       分享内容
     */
    public void shareText(@WeChatType int weChatType, String text) {
        WXTextObject mediaObject = new WXTextObject();
        mediaObject.text = text;

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        wxMediaMessage.description = text;

        SendMessageToWX.Req req = getReq(weChatType, ShareType.TEXT, wxMediaMessage);
        mWxapi.sendReq(req);
    }

    /**
     * 分享图片
     *
     * @param weChatType 微信类型
     * @param bitmap     图片
     * @param thumbData  缩略图
     */
    public void shareImgage(final @WeChatType int weChatType, final @NonNull Bitmap bitmap, final @NonNull Bitmap thumbData) {
        WXImageObject mediaObject = new WXImageObject(bitmap);

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        //设置缩略图
        if (thumbData != null) {
            wxMediaMessage.thumbData = bmpToByteArray(thumbData, true);
        }

        SendMessageToWX.Req req = getReq(weChatType, ShareType.IMAGE, wxMediaMessage);
        mWxapi.sendReq(req);

    }

    /**
     * 分享音乐
     *
     * @param weChatType
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicDataUrl 音乐url
     * @param thumbData    音乐缩略图
     */
    public void shareMusic(final @WeChatType int weChatType, String title, String description,
                           String musicDataUrl, final @NonNull Bitmap thumbData) {
        WXMusicObject mediaObject = new WXMusicObject();
        mediaObject.musicDataUrl = musicDataUrl;

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        wxMediaMessage.title = title;
        wxMediaMessage.description = description;
        //设置缩略图
        if (thumbData != null) {
            wxMediaMessage.thumbData = bmpToByteArray(thumbData, true);
        }

        SendMessageToWX.Req req = getReq(weChatType, ShareType.MUSIC, wxMediaMessage);
        mWxapi.sendReq(req);

    }

    /**
     * 分享视频
     *
     * @param weChatType  微信类型
     * @param title       视频标题
     * @param description 视频详情
     * @param videoUrl    视频url
     * @param thumbData   视频缩略图
     */
    public void shareVideo(final @WeChatType int weChatType, String title, String description,
                           String videoUrl, final @NonNull Bitmap thumbData) {
        WXVideoObject mediaObject = new WXVideoObject();
        mediaObject.videoUrl = videoUrl;

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        wxMediaMessage.title = title;
        wxMediaMessage.description = description;
        //设置缩略图
        if (thumbData != null) {
            wxMediaMessage.thumbData = bmpToByteArray(thumbData, true);
        }

        SendMessageToWX.Req req = getReq(weChatType, ShareType.VIDEO, wxMediaMessage);
        mWxapi.sendReq(req);

    }

    /**
     * 分享小程序
     *
     * @param weChatType      微信类型
     * @param title           小程序消息title
     * @param description     小程序消息desc
     * @param webpageUrl      兼容低版本的网页链接
     * @param miniProgramId   小程序原始id
     * @param miniProgramPage 小程序页面路径
     * @param thumbData       小程序消息封面图片，小于128k
     */
    public void shareMiniProgram(final @WeChatType int weChatType,
                                 String title, String description,
                                 String webpageUrl, String miniProgramId,
                                 String miniProgramPage,
                                 final @NonNull Bitmap thumbData) {
        WXMiniProgramObject mediaObject = new WXMiniProgramObject();
        mediaObject.webpageUrl = webpageUrl;
        mediaObject.userName = miniProgramId;
        mediaObject.path = miniProgramPage;

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        wxMediaMessage.title = title;
        wxMediaMessage.description = description;
        //设置缩略图
        if (thumbData != null) {
            wxMediaMessage.thumbData = bmpToByteArray(thumbData, true);
        }

        SendMessageToWX.Req req = getReq(weChatType, ShareType.WEBPAGE, wxMediaMessage);
        mWxapi.sendReq(req);

    }

    /**
     * 分享网页
     *
     * @param weChatType
     * @param url            网页地址
     * @param urlTitle       网页标题
     * @param urlDescription 网页详情
     * @param thumbData      缩略图
     */
    public void shareWebPage(final @WeChatType int weChatType,
                             final String url, final String urlTitle,
                             final String urlDescription, final Bitmap thumbData) {
        WXWebpageObject mediaObject = new WXWebpageObject();
        mediaObject.webpageUrl = url;

        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = mediaObject;
        wxMediaMessage.title = urlTitle;
        wxMediaMessage.description = urlDescription;
        //设置缩略图
        if (thumbData != null) {
            wxMediaMessage.thumbData = bmpToByteArray(thumbData, true);
        }

        SendMessageToWX.Req req = getReq(weChatType, ShareType.WEBPAGE, wxMediaMessage);
        mWxapi.sendReq(req);
    }

    @NonNull
    private SendMessageToWX.Req getReq(@WeChatType int weChatType,
                                       @NonNull @ShareType String shareType,
                                       @NonNull WXMediaMessage wxMediaMessage) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction(shareType);
        req.message = wxMediaMessage;
        if (WeChatType.WXSceneTimeline == weChatType) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else if (WeChatType.WXSceneSession == weChatType) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else if (WeChatType.WXSceneFavorite == weChatType) {
            req.scene = SendMessageToWX.Req.WXSceneFavorite;
        } else {

        }
        return req;
    }

    /**
     * 生成{@link SendMessageToWX.Req#transaction}字段用于唯一标识一个请求
     *
     * @param type
     * @return
     */
    public static String buildTransaction(@ShareType String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 微信类型：<Li>微信会话</Li><Li>微信朋友圈</Li><Li>微信收藏</Li>
     */
    @IntDef({WeChatType.WXSceneSession,
            WeChatType.WXSceneTimeline,
            WeChatType.WXSceneFavorite})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeChatType {
        /**
         * 微信聊天界面
         */
        int WXSceneSession = 0;
        /**
         * 微信朋友圈
         */
        int WXSceneTimeline = 1;
        /**
         * 微信收藏
         */
        int WXSceneFavorite = 2;
    }

    /**
     * 微信分享类型
     */
    @StringDef({ShareType.TEXT, ShareType.MUSIC, ShareType.IMAGE,
            ShareType.VIDEO, ShareType.WEBPAGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShareType {
        /**
         * 文字
         */
        String TEXT = "text";
        /**
         * 图片
         */
        String IMAGE = "image";
        /**
         * 音乐
         */
        String MUSIC = "music";
        /**
         * 视频
         */
        String VIDEO = "video";
        /**
         * 网页
         */
        String WEBPAGE = "webpage";
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
