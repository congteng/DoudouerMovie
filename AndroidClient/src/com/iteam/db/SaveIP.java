package com.iteam.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveIP {
	
	//保存服务器IP
	public static void saveIP(Context context,String ip) {
		SharedPreferences sp = context.getSharedPreferences("ipparam", Activity.MODE_PRIVATE);
		Editor editor= sp.edit();
		editor.putString("ip", ip);
		editor.commit();
	}
	//获取服务器IP
	public static String getIP(Context context) {
		SharedPreferences sp = context.getSharedPreferences("ipparam", Activity.MODE_PRIVATE);
		String ip = sp.getString("ip", "192.168.1.100");
		return ip;
	}
}
