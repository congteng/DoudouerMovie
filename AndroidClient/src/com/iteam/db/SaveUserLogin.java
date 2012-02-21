package com.iteam.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveUserLogin {
	
	//保存用户登录信息
	public static void saveUser(Context context,String email, String password) {
		SharedPreferences sp = context.getSharedPreferences("loginparam", Activity.MODE_PRIVATE);
		Editor editor= sp.edit();
		editor.putString("email", email);
		editor.putString("password", password);
		editor.commit();
	}
	//获取用户登录信息
	public static String[] getUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("loginparam", Activity.MODE_PRIVATE);
		String params[] = new String[2];
		params[0] = sp.getString("email", null);
		params[1] = sp.getString("password", null);
		return params;
	}
}
