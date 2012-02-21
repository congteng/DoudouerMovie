package com.iteam.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import transport.MySoapEnvelope;
import com.iteam.service.MainService;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean checkNet(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	// 从一个URL取得一个图片
	public static BitmapDrawable getImageFromUrl(String path) {
		BitmapDrawable icon = null;
		try {
			URL url = new URL(path);
			HttpURLConnection hc = (HttpURLConnection) url.openConnection();
			icon = new BitmapDrawable(hc.getInputStream());
			hc.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icon;
	}
	
	public static Object useEnvelope (SoapObject soapObject) throws IOException, XmlPullParserException {
		MySoapEnvelope envelope = new MySoapEnvelope(SoapEnvelope.VER11);
		envelope.encodingStyle = "utf-8";
		envelope.dotNet = false;
		envelope.setAddAdornments(false);
		envelope.implicitTypes = true;
		envelope.setOutputSoapObject(soapObject);
		HttpTransportSE ht = new HttpTransportSE(MainService.URL);
		ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ht.debug = true;
		ht.call(null, envelope);
		return envelope.getResponse();
	}
	
	public static Object useEnvelope (SoapObject soapObject, String url, String soapAction) throws IOException, XmlPullParserException {
		MySoapEnvelope envelope = new MySoapEnvelope(SoapEnvelope.VER11);
		envelope.encodingStyle = "utf-8";
		envelope.dotNet = true;
		envelope.setAddAdornments(false);
		envelope.implicitTypes = true;
		envelope.setOutputSoapObject(soapObject);
		HttpTransportSE ht = new HttpTransportSE(url);
		ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ht.debug = true;
		ht.call(soapAction, envelope);
		return envelope.getResponse();
	}
}
