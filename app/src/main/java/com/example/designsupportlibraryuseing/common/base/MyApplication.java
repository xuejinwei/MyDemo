package com.example.designsupportlibraryuseing.common.base;

import android.app.Application;
import android.os.Handler;

import com.example.designsupportlibraryuseing.common.utils.ActivityHelper;

public class MyApplication extends Application {

    private static MyApplication _context;

    @Override
    public void onCreate() {
        super.onCreate();

        _context = this;

        // 初始化ActivityHelper
        ActivityHelper.config(this);

    }

    public static MyApplication getInstance() {
        return _context;
    }

    public Handler getHandler() {
        return mHandler;
    }

    Handler mHandler = new Handler() {

    };

}