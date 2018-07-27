package com.example.aliouswang.rxplayground;

import android.util.Log;

/**
 * Created by aliouswang on 2018/7/27.
 */

public class L {

    public static final String TAG = "rxjava";

    public static void e(String pre) {
        Log.e(TAG, pre + "; cur thread is : " + Thread.currentThread().getName());
    }

}
