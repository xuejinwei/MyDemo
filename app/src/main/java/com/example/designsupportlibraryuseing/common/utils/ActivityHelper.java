package com.example.designsupportlibraryuseing.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;
import java.util.Set;

/**
 * 在Application中一定要先配置
 * 
 * @author wangdan
 *
 */
public class ActivityHelper {
	
	public static final String KEY = "org.aisen.android.activityhelp_key";
	
	private static Context mContext;

	private ActivityHelper() {
	}

	public static void config(Context context) {
		mContext = context;
	}


	/**
	 * 获取int
	 * 
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getIntShareData(String key, int defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}

	public static void putIntShareData(String key, int value) {
		SharedPreferences sp = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
		Editor et = sp.edit();
		et.putInt(key, value);
		et.commit();
	}

}
