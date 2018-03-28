package con.mysiga.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

/**
 * {@link Fragment}兼容动画
 */

public class FragmentAnimUtils {
    /**
     * 有返回值动画
     *
     * @param fragment
     * @param intent
     * @param requestCode
     * @param enterResId
     * @param exitResId
     */
    public static void startActivityForResultCustomAnim(@NonNull Fragment fragment,
                                                        @NonNull Intent intent,
                                                        int requestCode,
                                                        int enterResId, int exitResId) {
        Bundle optionBundle = ActivityOptionsCompat.makeCustomAnimation(fragment.getContext(), enterResId, exitResId).toBundle();
        fragment.startActivityForResult(intent, requestCode, optionBundle);
    }

    /**
     * 无返回值动画
     *
     * @param fragment
     * @param intent
     * @param enterResId
     * @param exitResId
     */
    public static void startActivityCustomAnim(@NonNull Fragment fragment,
                                               @NonNull Intent intent,
                                               int enterResId, int exitResId) {
        Bundle optionBundle = ActivityOptionsCompat.makeCustomAnimation(fragment.getContext(), enterResId, exitResId).toBundle();
        fragment.startActivity(intent, optionBundle);
    }
}
