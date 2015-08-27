package com.example.designsupportlibraryuseing.common.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.example.designsupportlibraryuseing.common.utils.AppSetting;
import com.example.designsupportlibraryuseing.common.utils.ThemeUtils;

/**
 * Created by 晋伟 on 2015/7/22 0022.
 */
public class BaseActivity extends AppCompatActivity {
    private String TAG = "BaseActivity";
    private int theme = 0;// 当前界面设置的主题

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            theme = configTheme();
        } else {
            theme = savedInstanceState.getInt("theme");
        }
        // 设置主题
        if (theme > 0)
            setTheme(theme);
        // 设置语言
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (theme == configTheme()) {

        } else { // 这里是判断如果主题改变的话重新启动activity
            Log.i(TAG, "theme changed, reload()");
            reload();

            return;
        }
    }

    /**
     * 重新启动当下activity，取消进入和退出动画
     */
    public void reload() {

        Intent intent = getIntent();
        //finish activity再次重启activity，但是没有任何进入推出动画
        overridePendingTransition(0, 0);//activity 进入和 退出动画
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);//设置为 FLAG_ACTIVITY_NO_ANIMATION ，activity进入推出无动画
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        //end
    }

    /**
     * @return 设置的主题
     */
    protected int configTheme() {
        return ThemeUtils.themeArr[AppSetting.getThemeColor()][0];
    }
}
