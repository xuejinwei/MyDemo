package com.example.designsupportlibraryuseing.common.utils;


import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.common.base.MyApplication;

/**
 * Created by wangdan on 15/4/30.
 */
public class ThemeUtils {

    public static int[][] themeArr = {
            {R.style.AppTheme_Red},
            {R.style.AppTheme_Pink},
            {R.style.AppTheme_Purple},
            {R.style.AppTheme_DeepPurple},
            {R.style.AppTheme_Indigo},
            {R.style.AppTheme_Blue},
            {R.style.AppTheme_LightBlue},
            {R.style.AppTheme_Cyan},
            {R.style.AppTheme_Teal},
            {R.style.AppTheme_Green},
            {R.style.AppTheme_LightGreen},
            {R.style.AppTheme_Lime},
            {R.style.AppTheme_Yellow},
            {R.style.AppTheme_Amber},
            {R.style.AppTheme_Orange},
            {R.style.AppTheme_DeepOrange},
            {R.style.AppTheme_Brown},
            {R.style.AppTheme_Grey},
            {R.style.AppTheme_BlueGrey}
    };

    public static int[][] themeColorArr = {
            {R.color.md_red_500, R.color.md_red_700},
            {R.color.md_pink_500, R.color.md_pink_700},
            {R.color.md_purple_500, R.color.md_purple_700},
            {R.color.md_deep_purple_500, R.color.md_deep_purple_700},
            {R.color.md_indigo_500, R.color.md_indigo_700},
            {R.color.md_blue_500, R.color.md_blue_700},
            {R.color.md_light_blue_500, R.color.md_light_blue_700},
            {R.color.md_cyan_500, R.color.md_cyan_700},
            {R.color.md_teal_500, R.color.md_teal_500},
            {R.color.md_green_500, R.color.md_green_500},
            {R.color.md_light_green_500, R.color.md_light_green_500},
            {R.color.md_lime_500, R.color.md_lime_700},
            {R.color.md_yellow_500, R.color.md_yellow_700},
            {R.color.md_amber_500, R.color.md_amber_700},
            {R.color.md_orange_500, R.color.md_orange_700},
            {R.color.md_deep_orange_500, R.color.md_deep_orange_700},
            {R.color.md_brown_500, R.color.md_brown_700},
            {R.color.md_grey_500, R.color.md_grey_700},
            {R.color.md_blue_grey_500, R.color.md_blue_grey_700}
    };

    public static int getThemeColor() {
        return MyApplication.getInstance().getResources().getColor(themeColorArr[AppSetting.getThemeColor()][0]);
    }
}
