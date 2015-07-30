package com.tustar.active.utils;

import android.util.Log;

import com.tustar.active.BuildConfig;

/**
 * Created by Tu on 7/30/15.
 */
public class Logger {

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private Logger() {
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }
}
