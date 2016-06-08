package com.jpz.zhangjp.basejpz.com.jpz.zhangjp.basejpz.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by zhangjp on 2016/6/8.
 */
public class Utils {
    /**
     * @param activity
     * @return screen width
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * @param activity
     * @return screen height
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
