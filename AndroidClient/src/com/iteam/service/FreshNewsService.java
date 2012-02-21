package com.iteam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.doudouer.domain.Discuss;
import net.doudouer.domain.FreshNews;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import com.iteam.util.NetUtil;

public class FreshNewsService {
	
	@SuppressWarnings("unchecked")
	public static List<FreshNews> getFreshNews(Long userID, int off, int len, boolean desc) throws IOException, XmlPullParserException{
		String METHOD_NAME = "getFreshNewsForSpecificUser";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", off);
		soapObject.addProperty("arg2", len);
		soapObject.addProperty("arg3", desc);
		//生成调用webService方法的SOAP请求信息。
		Vector<Object> resultVector = (Vector<Object>) NetUtil.useEnvelope(soapObject);;
		return parseFreshNews(resultVector);
	}
	
	public static List<FreshNews> parseFreshNews(Vector<Object> resultVector) {
		List<FreshNews> freshNewsList = new ArrayList<FreshNews>();
		for(int i = 0; i < resultVector.size(); i++){
	       SoapObject detail = (SoapObject) resultVector.elementAt(i);
	       String content = detail.getProperty("content").toString();
	       Long happenTo = Long.valueOf(detail.getProperty("happenTo").toString());
	       Long id = Long.valueOf(detail.getProperty("id").toString());
	       Long newsID = Long.valueOf(detail.getProperty("newsID").toString());  
	       String newsOfEntity = detail.getProperty("newsOfEntity").toString();
	       String newsType = detail.getProperty("newsType").toString();
		   Long timestamp = Long.valueOf(detail.getProperty("timestamp").toString());
	       Long userID = Long.valueOf(detail.getProperty("userID").toString());
	      
	       FreshNews freshNews = new FreshNews();
	       freshNews.setContent(content);
	       freshNews.setHappenTo(happenTo);
	       freshNews.setId(id);
	       freshNews.setNewsID(newsID);
	       freshNews.setNewsOfEntity(newsOfEntity);
	       freshNews.setNewsType(newsType);
	       freshNews.setTimestamp(timestamp);
	       freshNews.setUserID(userID);
	       
	       int discussCount = detail.getPropertyCount() - 8;
	       Vector<Object> discussVector = new Vector<Object>();
	       if(discussCount != 0) {//这个新鲜事有评论，需要解析
	    	   for (int j = 1; j <= discussCount; j++) {
	    		   SoapObject s = (SoapObject)detail.getProperty(j);
	    		   discussVector.add(s);
	    	   }
	    	   List<Discuss> discussList = DiscussService.parseDiscuss(discussVector);
		       freshNews.setDiscussList(discussList);
	       }
	       freshNewsList.add(freshNews);
		}
		return freshNewsList;
	}
}
