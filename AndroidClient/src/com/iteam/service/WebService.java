package com.iteam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import com.iteam.util.NetUtil;

public class WebService {
	
	public static final String NAMESPACE = "http://WebXml.com.cn/";
	
	public static Map<String, String> getWeather(String theCityCode) throws IOException, XmlPullParserException {
		String METHOD_NAME = "getWeather";
		SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
		soapObject.addProperty("theCityCode", theCityCode);
		soapObject.addProperty("userID", "");
		//生成调用webService方法的SOAP请求信息。
		String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
		String soapAction = NAMESPACE + METHOD_NAME;
		Object object = NetUtil.useEnvelope(soapObject,url,soapAction);
		String string = ((SoapObject)object).getProperty(0).toString();
		if(string.equals("查询结果为空。http://www.webxml.com.cn/")) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("null", "null");
			return hashMap;
		} else {
			SoapObject result = (SoapObject)object;
			return parseWeather(result);
		}
	}
	
	private static Map<String, String> parseWeather(SoapObject result) {
		Map<String, String> weather_detail = new HashMap<String, String>();
		weather_detail.put("cityName", result.getProperty(0).toString());
		weather_detail.put("weather_today", result.getProperty(4).toString());
		weather_detail.put("air", result.getProperty(5).toString());
		weather_detail.put("direct", result.getProperty(6).toString());
		weather_detail.put("weather_tomorrow", result.getProperty(7).toString());
		weather_detail.put("tomorrow_temp", result.getProperty(8).toString());
		weather_detail.put("tomorrow_wind", result.getProperty(9).toString());
		weather_detail.put("weather_after_tomorrow", result.getProperty(12).toString());
		weather_detail.put("after_tomorrow_temp", result.getProperty(13).toString());
		weather_detail.put("after_tomorrow_wind", result.getProperty(14).toString());
		return weather_detail;
	}

	public static String getMobileCodeInfo(String mobileCode) throws IOException, XmlPullParserException {
		String METHOD_NAME = "getMobileCodeInfo";
		SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
		soapObject.addProperty("mobileCode", mobileCode);
		soapObject.addProperty("userID", "");
		//生成调用webService方法的SOAP请求信息。
		String url = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
		String soapAction = NAMESPACE + METHOD_NAME;
		return NetUtil.useEnvelope(soapObject,url,soapAction).toString();
	}

	public static Map<String, String> translate(String wordKey) throws IOException, XmlPullParserException {
		String METHOD_NAME = "TranslatorString";
		SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
		soapObject.addProperty("wordKey", wordKey);
		//生成调用webService方法的SOAP请求信息。
		String url = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
		String soapAction = NAMESPACE + METHOD_NAME;
		Object object = NetUtil.useEnvelope(soapObject,url,soapAction);
		SoapObject result = (SoapObject)object;
		return parseTranslate(result);
	}

	private static Map<String, String> parseTranslate(SoapObject result) {
		Map<String, String> translate_detail = new HashMap<String, String>();
		translate_detail.put("word", result.getProperty(0).toString());
		translate_detail.put("symbol", result.getProperty(1).toString());
		translate_detail.put("translate_result", result.getProperty(3).toString());
		translate_detail.put("mp3", result.getProperty(4).toString());
		return translate_detail;
	}

	public static List<String> getSentence(String wordKey) throws IOException, XmlPullParserException {
		String METHOD_NAME = "TranslatorSentenceString";
		SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
		soapObject.addProperty("wordKey", wordKey);
		//生成调用webService方法的SOAP请求信息。
		String url = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
		String soapAction = NAMESPACE + METHOD_NAME;
		SoapObject result = (SoapObject)NetUtil.useEnvelope(soapObject,url,soapAction);
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < result.getPropertyCount(); i++) {
			stringList.add(result.getProperty(i).toString());
		}
		return stringList;
	}

	public static Object getMp3(String mp3) throws IOException, XmlPullParserException {
		String METHOD_NAME = "GetMp3";
		SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
		soapObject.addProperty("Mp3", mp3);
		//生成调用webService方法的SOAP请求信息。
		String url = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
		String soapAction = NAMESPACE + METHOD_NAME;
		String result = (String)NetUtil.useEnvelope(soapObject,url,soapAction).toString();
		return result;
	}
}
