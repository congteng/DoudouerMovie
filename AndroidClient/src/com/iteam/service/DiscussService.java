package com.iteam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.doudouer.domain.Discuss;

import org.ksoap2.serialization.SoapObject;

public class DiscussService {
	
	public static List<Discuss> parseDiscuss(Vector<Object> resultVector) {
		List<Discuss> discussList = new ArrayList<Discuss>();
		for(int i = 0; i < resultVector.size(); i++){
	       SoapObject detail = (SoapObject) resultVector.elementAt(i);
	       String content = detail.getProperty("content").toString();
	       String dissTo = detail.getProperty("dissTo").toString();
	       Long id = Long.valueOf(detail.getProperty("id").toString());
	       Long indexOfFreshNews = Long.valueOf(detail.getProperty("indexOfFreshNews").toString());  
		   Long timestamp = Long.valueOf(detail.getProperty("timestamp").toString());
	       Long whoDiss = Long.valueOf(detail.getProperty("whoDiss").toString());
	       
	       Discuss discuss = new Discuss();
	       discuss.setContent(content);
	       discuss.setDissTo(dissTo);
	       discuss.setId(id);
	       discuss.setIndexOfFreshNews(indexOfFreshNews);
	       discuss.setTimestamp(timestamp);
	       discuss.setWhoDiss(whoDiss);
	       discussList.add(discuss);
		}
		return discussList;
	}
}
