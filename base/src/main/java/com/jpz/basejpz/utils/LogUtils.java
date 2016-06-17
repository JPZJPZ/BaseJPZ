package com.jpz.basejpz.utils;

import android.util.Log;

/**
 * Created by zhangjp on 2016/6/8.
 */
public class LogUtils {
    private static final boolean open = true;
    private static final String TAG = "zhangjp";

    public static void logI(String tag,String msg) {
        if(open) {
            Log.i(tag,msg);
        }
    }

    public static void logI(String msg) {
        if(open) {
            Log.i(TAG,msg);
        }
    }



}
