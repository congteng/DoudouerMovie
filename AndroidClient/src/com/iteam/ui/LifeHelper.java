package com.iteam.ui;

import com.iteam.logic.DDMovieActivity;
import com.iteam.service.MainService;
import com.iteam.service.UserService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LifeHelper extends Activity implements DDMovieActivity{

	Button translateButton,telButton,weatherButton;
//	ImageView userAvatar_helper_ImageView;
	ImageView userAvatarImageView;
	TextView nickNameTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.lifehelper); 
		translateButton = (Button)findViewById(R.id.helper_button_translate);
		translateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeHelper.this,Translate.class);
				startActivityForResult(intent, RESULT_OK);
			}
		});
		telButton = (Button)findViewById(R.id.helper_button_tel);
		telButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeHelper.this,Tel.class);
				startActivityForResult(intent, RESULT_OK);
			}
		});
		weatherButton = (Button) findViewById(R.id.helper_button_weather);
		weatherButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeHelper.this,Weather.class);
				startActivityForResult(intent, RESULT_OK);
			}
		});
//		userAvatar_helper_ImageView = (ImageView)findViewById(R.id.helper_user_avatar);
//		userAvatar_helper_ImageView.setBackgroundDrawable(UserService.nowUserIcon);
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(MainActivity.user.getNickName());
		init();
		MainService.allActivity.add(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MainService.promptExit(this.getParent());
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void refresh(Object... param) {
		
	}

}
