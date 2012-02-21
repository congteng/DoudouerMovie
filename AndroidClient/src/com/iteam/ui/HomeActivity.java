package com.iteam.ui;


import net.doudouer.domain.User;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.UserService;

public class HomeActivity extends ActivityGroup implements DDMovieActivity{
	public static final String TAB_FRESHNEWS = "tab_FreshNews";
	public static final String TAB_MOVIECOLLECTION = "tab_movieCollection";
	public static final String TAB_GUESSYOURLIKE = "tab_guessYourLike";
	RadioGroup radioGroup;
	TextView nickNameTextView;
	ImageView userAvatarImageView;
	LinearLayout container_freshnews,container_moviecollection,container_guessyourlike;// 装载sub Activity的容器
	LinearLayout freshNews_container,movieCollection_container,guessYourLike_container;
	FrameLayout top_tab;
	User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		user = MainActivity.getUser();
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(user.getNickName());
		container_freshnews = (LinearLayout)findViewById(R.id.container_freshnews);
		container_guessyourlike = (LinearLayout)findViewById(R.id.container_guessyourlike);
		container_moviecollection = (LinearLayout)findViewById(R.id.container_moviecollection);
		top_tab = (FrameLayout)findViewById(R.id.top_tab);
		radioGroup = (RadioGroup)findViewById(R.id.main_radio);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switchActivity(checkedId);
			}
		});
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		init();
		//添加到Activity组件集合
        MainService.allActivity.add(this);
	}
	
	/** 
     * 根据ID打开指定的Activity 
     * @param id RadioGroup选中项的序号 
     */  
    void switchActivity(int id) {
//		container.removeAllViews();// 必须先清除容器中所有的View
		Intent intent = null;
		switch (id) {
		case R.id.freshnews://新鲜事界面
			container_guessyourlike.setVisibility(View.GONE);
			container_moviecollection.setVisibility(View.GONE);
			container_freshnews.setVisibility(View.VISIBLE);
			intent = new Intent(HomeActivity.this, FreshNewsActivity.class);
			Window subActivity = getLocalActivityManager().startActivity("subActivity", intent);
			container_freshnews.addView(subActivity.getDecorView());
			break;
		case R.id.movieCollection://电影收藏
			container_guessyourlike.setVisibility(View.GONE);
			container_freshnews.setVisibility(View.GONE);
			container_moviecollection.setVisibility(View.VISIBLE);
			intent = new Intent(HomeActivity.this, MovieCollectionActivity.class);
			Window subActivity1 = getLocalActivityManager().startActivity("subActivity", intent);
			container_moviecollection.addView(subActivity1.getDecorView());
			break;
		case R.id.guessYourLike://猜你喜欢
			container_freshnews.setVisibility(View.GONE);
			container_moviecollection.setVisibility(View.GONE);
			container_guessyourlike.setVisibility(View.VISIBLE);
			intent = new Intent(HomeActivity.this, GuessYourLikeActivity.class);
			Window subActivity2 = getLocalActivityManager().startActivity("subActivity", intent);
			container_guessyourlike.addView(subActivity2.getDecorView());
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:	
			System.out.println("home---result ok");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("home---onresume");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("home---ondestroy");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("home---pause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("home---onstop");
	}
	
	@Override
	public void init() {
		switchActivity(R.id.freshnews);//默认打开新鲜事
	}

	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
			break;
		case Task.GET_NOWUSER_AVATAR:
			userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
			break;
		default:
			break;
		}
	}
}
