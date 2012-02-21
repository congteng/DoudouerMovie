package com.iteam.logic;

import java.util.Map;

public class Task {
	private int taskID;//任务编号
	private Map<?, ?> taskParam;//任务参数
	
	public static final int LOGIN = 1;//用户登录
	public static final int REGISTER = 2;//用户注册
	public static final int GET_FRESHNEWS = 3;//获得用户首页
	public static final int DISCUSS_TO_USERGIBBERISH = 4;//评论心情
	public static final int ADD_A_NEW_GIBBERISH = 5;//用户发表一个心情
	public static final int GET_EVALUATEMOVIES = 6;//获取给该用户评价的电影
	public static final int COLLECT_MOVIE = 7;//收藏电影
	public static final int GET_MOVIE_BY_ID = 8;//通过ID得到电影信息
	public static final int GET_NOWUSER_AVATAR = 9;//获取当前用户头像
	public static final int GET_USER_AVATAR_PATH_BY_ID = 10;//根据用户id 得到用户头像地址
	public static final int GET_ALLUSER_AVATAR = 11;//获取新鲜事涉及到的所有用户头像
	public static final int ADD_NEW_FRIENDS = 12;//增加好友
	public static final int WRITE_FILM_REVIEW = 13;//写影评
	public static final int GET_RECOMMEND_MOVIE_LIST = 14;//获取推荐给指定用户的电影
	public static final int GET_MOVIE_POSTER = 20;//获取电影图片
	public static final int GET_USER_NICKNAME_BY_ID = 21;//获取用户昵称
	public static final int GET_ALL_USER_NICKNAME = 22;//获取新鲜事涉及到的所有用户昵称
	
	public static final int SEARCH_TEL_INFO = 15;//查询电话归属地信息
	public static final int SEARCH_WEATHER_INFO = 16;//查询天气预报
	public static final int TRANSLATE = 17;//翻译
	public static final int GET_SENTENCE = 18;//获取单词的例句
	public static final int GET_MP3 = 19;//获取MP3数据流
	
	public Task(int id, Map<?, ?> param) {
		this.taskID = id;
		this.taskParam = param;
	}
	   
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public Map<?, ?> getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map<?, ?> taskParam) {
		this.taskParam = taskParam;
	}
}
