package con.mysiga.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;

/**
 * 定制兼容SDK>16的startActivity动画{@link Activity}
 */
public class ActivityAnimUtils {

    public static void startActivityCustomAnim(@NonNull Activity activity, @NonNull Intent intent,
                                               int enterResId, int exitResId) {
        Bundle optionBundle = ActivityOptionsCompat.makeCustomAnimation(activity, enterResId, exitResId).toBundle();
        ActivityCompat.startActivity(activity, intent, optionBundle);
    }

    public static void startActivityForResultCustomAnim(@NonNull Activity activity, @NonNull Intent intent, int requestCode,
                                                        int enterResId, int exitResId) {
        Bundle optionBundle = ActivityOptionsCompat.makeCustomAnimation(activity, enterResId, exitResId).toBundle();
        ActivityCompat.startActivityForResult(activity, intent, requestCode, optionBundle);
    }

    private static void startActivityScaleUp(@NonNull Activity activity, @NonNull Intent intent,
                                             @Nullable View source,
                                             int startX, int startY, int startWidth, int startHeight) {
        Bundle optionBundle = source != null
                ? ActivityOptionsCompat.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight).toBundle()
                : null;
        ActivityCompat.startActivity(activity, intent, optionBundle);
    }

    public static void startActivityScaleUp(@NonNull Activity activity,
                                            @NonNull Intent intent,
                                            @Nullable View source,
                                            @Nullable StartElement startElement) {
        int startX = 0;
        int startY = 0;
        int startWidth = 0;
        int startHeight = 0;
        if (startElement != null) {
            startX = startElement.startX;
            startY = startElement.startY;
            startWidth = startElement.startWidth;
            startHeight = startElement.startHeight;
        }
        startActivityScaleUp(activity, intent, source, startX, startY, startWidth, startHeight);
    }

    public static void startActivityScaleUp(@NonNull Activity activity, @NonNull Intent intent, @Nullable View source) {
        int startX = 0;
        int startY = 0;
        int startWidth = 0;
        int startHeight = 0;
        if (source != null) {
            startWidth = source.getWidth();
            startHeight = source.getHeight();
        }
        startActivityScaleUp(activity, intent, source, startX, startY, startWidth, startHeight);
    }

    public static void startActivityForResultScaleUp(@NonNull Activity activity, @NonNull Intent intent, int requestCode, @Nullable View source) {
        int startX = 0;
        int startY = 0;
        int startWidth = 0;
        int startHeight = 0;
        if (source != null) {
            startWidth = source.getWidth();
            startHeight = source.getHeight();
        }
        startActivityForResultScaleUp(activity, intent, requestCode, source, startX, startY, startWidth, startHeight);
    }

    private static void startActivityForResultScaleUp(@NonNull Activity activity, @NonNull Intent intent, int requestCode,
                                                      @Nullable View source,
                                                      int startX, int startY, int startWidth, int startHeight) {
        Bundle optionBundle = source != null
                ? ActivityOptionsCompat.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight).toBundle()
                : null;
        ActivityCompat.startActivityForResult(activity, intent, requestCode, optionBundle);
    }

    public static void startActivityThumbnailScaleUp(@NonNull Activity activity,
                                                     @NonNull Intent intent,
                                                     @Nullable View source,
                                                     @Nullable Bitmap thumbnail,
                                                     int startX, int startY) {
        Bundle optionBundle = source == null || thumbnail == null
                ? null
                : ActivityOptionsCompat.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY).toBundle();
        ActivityCompat.startActivity(activity, intent, optionBundle);
    }

    public static void startActivitySceneTransition(@NonNull Activity activity,
                                                    @NonNull Intent intent,
                                                    @Nullable View source,
                                                    @Nullable String sharedElementName,
                                                    @Nullable StartElement sharedStartElement) {
        Bundle optionBundle = null;
        if (source != null && TextUtils.isEmpty(sharedElementName) == false && sharedStartElement != null) {
            // 兼容处理
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                optionBundle = ActivityOptionsCompat.makeScaleUpAnimation(
                        source,
                        sharedStartElement.startX,
                        sharedStartElement.startY,
                        sharedStartElement.startWidth,
                        sharedStartElement.startHeight).toBundle();
            } else {
                optionBundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, source, sharedElementName).toBundle();
            }
        }
        ActivityCompat.startActivity(activity, intent, optionBundle);
    }

    public static void startActivitySceneTransition(@NonNull Activity activity,
                                                    @NonNull Intent intent,
                                                    @Nullable Pair<View, String>... sharedElements) {
        Bundle optionBundle = sharedElements != null
                ? ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements).toBundle()
                : null;
        ActivityCompat.startActivity(activity, intent, optionBundle);
    }

    public static class StartElement {
        public int startX;
        public int startY;
        public int startWidth;
        public int startHeight;

        public StartElement(int startX, int startY, int startWidth, int startHeight) {
            this.startX = startX;
            this.startY = startY;
            this.startWidth = startWidth;
            this.startHeight = startHeight;
        }
    }
}
