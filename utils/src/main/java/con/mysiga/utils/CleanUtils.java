package con.mysiga.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 清除相关工具类
 */
public class CleanUtils {

    private CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 清除内部缓存
     * <p>/data/data/com.xxx.xxx/cache</p>
     *
     * @param context 上下文
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalCache(Context context) {
        return FileUtils.deleteFilesInDir(context.getCacheDir());
    }

    /**
     * 清除内部文件
     * <p>/data/data/com.xxx.xxx/files</p>
     *
     * @param context 上下文
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalFiles(Context context) {
        return FileUtils.deleteFilesInDir(context.getFilesDir());
    }

    /**
     * 清除内部数据库
     * <p>/data/data/com.xxx.xxx/databases</p>
     *
     * @param context 上下文
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalDbs(Context context) {
        return FileUtils.deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "databases");
    }

    /**
     * 根据名称清除数据库
     * <p>/data/data/com.xxx.xxx/databases/dbName</p>
     *
     * @param context 上下文
     * @param dbName  数据库名称
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalDbByName(Context context, String dbName) {
        return context.deleteDatabase(dbName);
    }

    /**
     * 清除内部SP
     * <p>/data/data/com.xxx.xxx/shared_prefs</p>
     *
     * @param context 上下文
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanInternalSP(Context context) {
        return FileUtils.deleteFilesInDir(context.getFilesDir().getParent() + File.separator + "shared_prefs");
    }

    /**
     * 清除外部缓存
     * <p>/storage/emulated/0/android/data/com.xxx.xxx/cache</p>
     *
     * @param context 上下文
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanExternalCache(Context context) {
        return isSDCardEnable() && FileUtils.deleteFilesInDir(context.getExternalCacheDir());
    }

    /**
     * 清除自定义目录下的文件
     *
     * @param dirPath 目录路径
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanCustomCache(String dirPath) {
        return FileUtils.deleteFilesInDir(dirPath);
    }

    /**
     * 清除自定义目录下的文件
     *
     * @param dir 目录
     * @return {@code true}: 清除成功<br>{@code false}: 清除失败
     */
    public static boolean cleanCustomCache(File dir) {
        return FileUtils.deleteFilesInDir(dir);
    }
    /**
     * 清除App所有数据
     *
     * @param context  上下文
     * @param dirPaths 目录路径
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(Context context, String... dirPaths) {
        File[] dirs = new File[dirPaths.length];
        int i = 0;
        for (String dirPath : dirPaths) {
            dirs[i++] = new File(dirPath);
        }
        return cleanAppData(context, dirs);
    }

    /**
     * 清除App所有数据
     *
     * @param context 上下文
     * @param dirs    目录
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public static boolean cleanAppData(Context context, File... dirs) {
        boolean isSuccess = cleanInternalCache(context);
        isSuccess &= cleanInternalDbs(context);
        isSuccess &= cleanInternalSP(context);
        isSuccess &= cleanInternalFiles(context);
        isSuccess &= cleanExternalCache(context);
        for (File dir : dirs) {
            isSuccess &= cleanCustomCache(dir);
        }
        return isSuccess;
    }
    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
