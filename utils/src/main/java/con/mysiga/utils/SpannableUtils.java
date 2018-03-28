package con.mysiga.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

/**
 * {@link SpannableStringBuilder}相关处理工具类
 */

public class SpannableUtils {
    /**
     * 两个字符串替换颜色
     *
     * @param context
     * @param first
     * @param second
     * @param firstColor
     * @param secondColor
     * @return
     */
    public static SpannableStringBuilder getTimeString(@NonNull Context context,
                                                       String first,
                                                       String second,
                                                       @ColorRes int firstColor,
                                                       @ColorRes int secondColor) {
        return setSpanColor(new String[]{first, second},
                new int[]{ResourcesCompat.getColor(context.getResources(), firstColor, null),
                        ResourcesCompat.getColor(context.getResources(), secondColor, null)});
    }

    /**
     * 三个字符串替换颜色
     *
     * @param context
     * @param first
     * @param second
     * @param third
     * @param firstColor
     * @param secondColor
     * @param thirdColor
     * @return
     */
    public static SpannableStringBuilder getTimeString(@NonNull Context context,
                                                       String first,
                                                       String second,
                                                       String third,
                                                       @ColorRes int firstColor,
                                                       @ColorRes int secondColor,
                                                       @ColorRes int thirdColor) {
        return setSpanColor(new String[]{first, second, third},
                new int[]{
                        ResourcesCompat.getColor(context.getResources(), firstColor, null),
                        ResourcesCompat.getColor(context.getResources(), secondColor, null),
                        ResourcesCompat.getColor(context.getResources(), thirdColor, null)
                });
    }

    /**
     * 处理字体大小一样，颜色不一样的文字拼接
     *
     * @param spanString
     * @param spanColor
     * @return
     */
    public static SpannableStringBuilder setSpanColor(String[] spanString, int[] spanColor) {
        if (spanString.length != spanColor.length) {
            throw new IllegalArgumentException("must spanString.length == spanColor.length");
        }
        SpannableStringBuilder style = new SpannableStringBuilder();
        for (String aSpanString : spanString) {
            if (!TextUtils.isEmpty(aSpanString)) {
                style.append(aSpanString);
            }
        }
        StringBuilder spanBuilder = new StringBuilder();
        for (int i = 0, length = spanString.length; i < length; i++) {
            if (!TextUtils.isEmpty(spanString[i])) {
                style.setSpan(new ForegroundColorSpan(spanColor[i]),
                        spanBuilder.length(),
                        spanBuilder.length() + spanString[i].length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spanBuilder.append(spanString[i]);
            }
        }
        return style;
    }

    /**
     * 处理字体大小不一样
     *
     * @param spanString
     * @param spanSize
     * @return
     */
    public static SpannableStringBuilder setSpanSize(String[] spanString, float[] spanSize) {
        if (spanString.length != spanSize.length) {
            throw new IllegalArgumentException("must spanString.length == spanSize.length");
        }
        SpannableStringBuilder style = new SpannableStringBuilder();
        for (String aSpanString : spanString) {
            style.append(aSpanString);
        }
        StringBuilder spanBuilder = new StringBuilder();
        for (int i = 0, length = spanString.length; i < length; i++) {
            style.setSpan(new RelativeSizeSpan(spanSize[i]), spanBuilder.length(),
                    spanBuilder.length() + spanString[i].length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanBuilder.append(spanString[i]);
        }
        return style;
    }
}
