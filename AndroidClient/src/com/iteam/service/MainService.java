package com.iteam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.User;

import com.iteam.db.SaveIP;
import com.iteam.db.SaveUserLogin;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.ui.CollectActivity;
import com.iteam.ui.DiscussActivity;
import com.iteam.ui.FreshNewsActivity;
import com.iteam.ui.Login;
import com.iteam.ui.MovieCollectionActivity;
import com.iteam.ui.PublishFreshActivity;
import com.iteam.ui.R;
import com.iteam.ui.Register;
import com.iteam.ui.WriteReviewActivity;
import com.iteam.util.NetUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

public class MainService extends Service implements Runnable {
	
	//WebService的命名空间
	public static final String NAMESPACE = "http://webservice.doudouer.net/";
	public static String IP = "192.168.1.100";
	public static String URL = "http://" + IP + ":8080/DoudouerMovie/webservice/DoudouerService";
	
	public static String IMAGE_URL = "http://" + IP + ":8080/DoudouerMovie/";
	
	public boolean isrun = true;
	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();// 保存所有Activity
//	public static int lastActivityId;// 保存前端Activity编号
	int sleepTime = 1000;

	// 从集合中通过name获取Activity对象
	public static Activity getActivityByName(String name) {
		for (Activity ac : allActivity) {
			if (ac.getClass().getName().indexOf(name) >= 0) {
				return ac;
			}
		}
		return null;
	}

	public static ArrayList<Task> allTask = new ArrayList<Task>();

	// 添加任务
	public static void newTask(Task task) {
		allTask.add(task);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void run() {
		while(isrun) {
			Task lastTask = null;
			synchronized (allTask) {
				if(allTask.size()>0) {
					//取任务
					lastTask = allTask.get(0);
					//执行任务
					doTask(lastTask);
				}
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//更新UI
	private Handler handler=new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			DDMovieActivity ddActivity;
			switch (msg.what) {
			case Task.LOGIN:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("Login");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Login.LOGIN_REFRESH),msg.obj);
				}
				break;
			case Task.REGISTER:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("Register");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Register.REGISTER_SUCCESS));
				}
				break;
			case Task.GET_FRESHNEWS:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("FreshNewsActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(FreshNewsActivity.GET_FRESHNEWS),msg.obj);
				}
				break;
			case Task.DISCUSS_TO_USERGIBBERISH:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("DiscussActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(DiscussActivity.DISCUSS_TO_USERGIBBERISH));
				}
				break;
			case Task.ADD_A_NEW_GIBBERISH:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("PublishFreshActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(PublishFreshActivity.ADD_A_NEW_GIBBERISH));
				}
				break;
			case Task.GET_EVALUATEMOVIES:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("MovieCollectionActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(MovieCollectionActivity.GET_EVALUATEMOVIES),msg.obj);
				}
				break;
			case Task.GET_MOVIE_POSTER:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("CollectActivity");
				if (ddActivity == null) {
					ddActivity = (DDMovieActivity)MainService.getActivityByName("MovieCollectionActivity");
				} 
				if (ddActivity == null) {
					ddActivity = (DDMovieActivity)MainService.getActivityByName("GuessYourLikeActivity");
				}
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_MOVIE_POSTER));
				}
				break;
			case Task.GET_MOVIE_BY_ID:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("CollectActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(CollectActivity.GET_MOVIE_BY_ID),msg.obj);
				}
				break;
			case Task.GET_NOWUSER_AVATAR:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("HomeActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_NOWUSER_AVATAR));
				}
				break;
			case Task.GET_USER_AVATAR_PATH_BY_ID:
				break;
			case Task.GET_ALLUSER_AVATAR:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("FreshNewsActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(FreshNewsActivity.GET_ALLUSER_AVATAR));
				}
				break;
			case Task.GET_ALL_USER_NICKNAME:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("FreshNewsActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_ALL_USER_NICKNAME));
				}
				break;
			case Task.ADD_NEW_FRIENDS:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("FreshNewsActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(FreshNewsActivity.ADD_NEW_FRIENDS));
				}
				break;
			case Task.WRITE_FILM_REVIEW:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("WriteReviewActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(WriteReviewActivity.WRITE_FILM_REVIEW));
				}
				break;
			case Task.GET_RECOMMEND_MOVIE_LIST:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("GuessYourLikeActivity");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_RECOMMEND_MOVIE_LIST),msg.obj);
				}
				break;
			case Task.COLLECT_MOVIE:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("CollectActivity");
				if(ddActivity == null) {
					ddActivity = (DDMovieActivity)MainService.getActivityByName("MovieCollectionActivity");
				} 
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.COLLECT_MOVIE));
				}
				break;
			case Task.SEARCH_TEL_INFO:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("Tel");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.SEARCH_TEL_INFO),msg.obj);
				}
				break;
			case Task.SEARCH_WEATHER_INFO:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("Weather");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.SEARCH_WEATHER_INFO),msg.obj);
				}
				break;
			case Task.TRANSLATE:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("Translate");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.TRANSLATE),msg.obj);
				}
				break;
			case Task.GET_SENTENCE:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("TranslateDetail");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_SENTENCE),msg.obj);
				}
				break;
			case Task.GET_MP3:
				ddActivity = (DDMovieActivity)MainService.getActivityByName("TranslateDetail");
				if (ddActivity != null) {
					ddActivity.refresh(new Integer(Task.GET_MP3),msg.obj);
				}
				break;
			default:
				break;
			}
		}
	};
	
	private void doTask(Task task) {
		Message msg = handler.obtainMessage();
		msg.what = task.getTaskID();
		try{
			switch (task.getTaskID()) {
			case Task.LOGIN:
				String email = (String)task.getTaskParam().get("email");
				String password = (String)task.getTaskParam().get("password");
				User nowUser = UserService.userLogin(email, password);
				msg.obj = nowUser;
				//获取当前登录用户头像
				HashMap<String, String> param= new HashMap<String, String>();
				param.put("path",IMAGE_URL+nowUser.getAvatarLink());
				Task task1 = new Task(Task.GET_NOWUSER_AVATAR,param);
				MainService.newTask(task1);
				//保存登录数据
				SaveIP.saveIP(this, IP);
				SaveUserLogin.saveUser(this, email, password);
				break;
			case Task.REGISTER:
				String email_reg = (String)task.getTaskParam().get("email");
				String password_reg = (String)task.getTaskParam().get("password");
				String nickName_reg = (String)task.getTaskParam().get("nickName");
				if(UserService.userRegister(email_reg, password_reg, nickName_reg)) {
					//保存登录数据
					SaveUserLogin.saveUser(this, email_reg, password_reg);
				}
				break;
			case Task.GET_FRESHNEWS:
				long userID = (Long)task.getTaskParam().get("userID");
				int off = (Integer)task.getTaskParam().get("off");
				int len = (Integer)task.getTaskParam().get("len");
				List<FreshNews> freshNews = FreshNewsService.getFreshNews(userID, off, len, true);
//				UserService.getAllUserNickNameInFreshNews(freshNews);
				UserService.getAllUserInfoInFreshNews(freshNews);
				msg.obj = freshNews;
				break;
			case Task.DISCUSS_TO_USERGIBBERISH:
				long whoDiss = (Long)task.getTaskParam().get("whoDiss");
				String discussContent = (String)task.getTaskParam().get("discussContent");
				long index = (Long)task.getTaskParam().get("index");
				UserService.discussToUserGibberish(whoDiss, discussContent, index);
				break;
			case Task.ADD_A_NEW_GIBBERISH:
				long userID1 = (Long)task.getTaskParam().get("userID");
				String content = (String)task.getTaskParam().get("content");
				UserService.addANewGibberish(userID1, content);
				break;
			case Task.GET_EVALUATEMOVIES:
				long userID2 = (Long)task.getTaskParam().get("userID");
				int from = (Integer)task.getTaskParam().get("from");
				int len1 = (Integer)task.getTaskParam().get("len");
				List<Movie> movies = MovieService.getEvaluateMovies(userID2, from, len1);
				//建立获取电影图片的任务
				MovieService.getAllMoviePoster(movies);
				msg.obj = movies;
				break;
			case Task.GET_MOVIE_POSTER:
				//获取电影ID
				Long movieID = (Long)task.getTaskParam().get("id");
				String pathString2 = (String)task.getTaskParam().get("path");
				 //获取用户头像   
				BitmapDrawable bd2 = NetUtil.getImageFromUrl(IMAGE_URL + pathString2);
				//添加头像到集合    
				MovieService.allMoviePoster.put(movieID,bd2);
				break;
			case Task.GET_MOVIE_BY_ID:
				long id = (Long)task.getTaskParam().get("id");
				Movie movie = MovieService.getMovieById(id);
				msg.obj = movie;
				break;
			case Task.GET_NOWUSER_AVATAR:
				String path = (String)task.getTaskParam().get("path");
				UserService.nowUserIcon = NetUtil.getImageFromUrl(path);
				break;
			case Task.GET_USER_AVATAR_PATH_BY_ID:
				Long id1 = (Long)task.getTaskParam().get("id");
				String path1 = UserService.getUserAvatarPathById(id1);
				HashMap<Object, Object> param3 = new HashMap<Object, Object>();
				param3.put("path",IMAGE_URL+path1);
				param3.put("userID",id1);
				Task task3 = new Task(Task.GET_ALLUSER_AVATAR,param3);
				MainService.newTask(task3);
				break;
			case Task.GET_USER_NICKNAME_BY_ID:
				Long id6 = (Long)task.getTaskParam().get("id");
				String nickName = UserService.getUserNickNameById(id6);
				HashMap<Object, Object> param6 = new HashMap<Object, Object>();
				param6.put("nickName",nickName);
				param6.put("userID",id6);
				Task task6 = new Task(Task.GET_ALL_USER_NICKNAME,param6);
				MainService.newTask(task6);
				break;
			case Task.GET_ALL_USER_NICKNAME:
				//获取用户ID
				Long userID8= (Long)task.getTaskParam().get("userID");
				String nickName2 = (String)task.getTaskParam().get("nickName");
				//添加头像到集合    
				UserService.alluserNickName.put(userID8,nickName2);
				break;
			case Task.GET_ALLUSER_AVATAR:
				//获取用户ID
				Long userID3= (Long)task.getTaskParam().get("userID");
				String pathString = (String)task.getTaskParam().get("path");
				 //获取用户头像   
				BitmapDrawable bd = NetUtil.getImageFromUrl(pathString);
				//添加头像到集合    
				UserService.alluserIcon.put(userID3,bd);
				break;
			case Task.ADD_NEW_FRIENDS:
				Long userID4= (Long)task.getTaskParam().get("userID");
				Long id2= (Long)task.getTaskParam().get("id");
				UserService.addNewFriends(userID4, id2);
				break;
			case Task.WRITE_FILM_REVIEW:
				Long userID5= (Long)task.getTaskParam().get("userID");
				String content1 = (String)task.getTaskParam().get("content");
				Long movieID2 = (Long)task.getTaskParam().get("movieID");
				UserService.writeFilmReview(userID5, content1, movieID2);
				break;
			case Task.GET_RECOMMEND_MOVIE_LIST:
				Long userID6= (Long)task.getTaskParam().get("userID");
				int howmany = (Integer)task.getTaskParam().get("howmany");
				//获取当前电影的图片
				List<Movie> recommendMovieList = MovieService.getRecommendMovieList(userID6, howmany);
				MovieService.getAllMoviePoster(recommendMovieList);
				msg.obj = recommendMovieList;
				break;
			case Task.COLLECT_MOVIE:
				Long userID7= (Long)task.getTaskParam().get("userID");
				Long movieID3= (Long)task.getTaskParam().get("movieID");
				Double score = (Double)task.getTaskParam().get("score");
 				UserService.collectMovie(movieID3, userID7, score);
 				break;
			case Task.SEARCH_TEL_INFO:
				String mobileCode = (String)task.getTaskParam().get("mobileCode");
				msg.obj = WebService.getMobileCodeInfo(mobileCode);
				break;
			case Task.SEARCH_WEATHER_INFO:
				String theCityCode = (String)task.getTaskParam().get("theCityCode");
				msg.obj = WebService.getWeather(theCityCode);
				break;
			case Task.TRANSLATE:
				String wordKey = (String)task.getTaskParam().get("wordKey");
				msg.obj = WebService.translate(wordKey);
				break;
			case Task.GET_SENTENCE:
				String wordKey1 = (String)task.getTaskParam().get("wordKey");
				msg.obj = WebService.getSentence(wordKey1);
				break;
			case Task.GET_MP3:
				String Mp3 = (String)task.getTaskParam().get("Mp3");
				msg.obj = WebService.getMp3(Mp3);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			msg.what = -100;
		}
		handler.sendMessage(msg);
		allTask.remove(task);//任务执行完毕
	}

	@Override
	public void onCreate() {
		super.onCreate();
		URL = "http://" + IP + ":8080/DoudouerMovie/webservice/DoudouerService";
		
		IMAGE_URL = "http://" + IP + ":8080/DoudouerMovie/";
		isrun = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isrun = false;
	}

	/**
	 * 提示用户网络状态错误
	 * @param context
	 */
	public static void AlertNetError (final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.error);
		builder.setMessage(R.string.net_connection_error);
		builder.setNegativeButton(R.string.setnet, new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
				dialog.dismiss();
			}
		});
		builder.setPositiveButton(R.string.exit, new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				exitApp(context);
			}
		});
		builder.create().show();
	}
	
	public static void exitApp(Context context) {
		for (Activity ac : allActivity) {
			ac.finish();
		}
		allActivity.removeAll(allActivity);
		Intent it = new Intent("com.itcast.logic.MainService");
		context.stopService(it);
	}
	
	public static void promptExit(final Context con) {
		// 创建对话框
		LayoutInflater li = LayoutInflater.from(con);
		View exitV = li.inflate(R.layout.exitdialog, null);
		AlertDialog.Builder ab = new AlertDialog.Builder(con);
		ab.setView(exitV);// 设定对话框显示的View对象
		ab.setPositiveButton(R.string.exit,
				new OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						exitApp(con);
					}
				});
		ab.setNegativeButton(R.string.cancel, null);
		// 显示对话框
		ab.show();
	}
}
