package com.example.designsupportlibraryuseing.common.utils;

/**
 * Created by 晋伟 on 2015/7/22 0022.
 */
public class AppSetting {
    public static int getThemeColor() {
        return ActivityHelper.getIntShareData("Theme_index", 8);
    }

    public static void setThemeColor(int theme) {
        ActivityHelper.putIntShareData("Theme_index", theme);
    }
}
