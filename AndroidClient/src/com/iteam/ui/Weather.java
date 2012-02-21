package com.iteam.ui;

import java.util.HashMap;

import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Weather extends Activity implements DDMovieActivity{

	ImageView userAvatarImageView;
	TextView nickNameTextView;
	EditText cityNameEditText;
	Button searchButton;
	ProgressDialog progressDialog;
	LinearLayout weather_detaiLayout;
	TextView no_result,cityName,weather_today,air,direct,weather_tomorrow,tomorrow_temp,tomorrow_wind,
		weather_after_tomorrow,after_tomorrow_temp,after_tomorrow_wind;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.weather);
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(MainActivity.user.getNickName());
		cityNameEditText = (EditText)findViewById(R.id.city_name_edittext);
		weather_detaiLayout = (LinearLayout)findViewById(R.id.weather_detail_linearlayout);
		no_result = (TextView)findViewById(R.id.weather_no_result_textview);
		cityName = (TextView)findViewById(R.id.weather_cityname_textview);
		weather_today = (TextView)findViewById(R.id.weather_today_textview);
		air = (TextView)findViewById(R.id.weather_air_textview);
		direct = (TextView)findViewById(R.id.weather_direct_textview);
		weather_tomorrow = (TextView)findViewById(R.id.weather_tomorrow_textview);
		tomorrow_temp = (TextView)findViewById(R.id.weather_tomorrow_temp_textview);
		tomorrow_wind = (TextView)findViewById(R.id.weather_tomorrow_wind_textview);
		weather_after_tomorrow = (TextView)findViewById(R.id.weather_after_tomorrow_textview);
		after_tomorrow_temp = (TextView)findViewById(R.id.weather_after_tomorrow_temp_textview);
		after_tomorrow_wind = (TextView)findViewById(R.id.weather_after_tomorrow_wind_textview);
		
		searchButton = (Button)findViewById(R.id.search_tel_button);
		searchButton.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				if(!cityNameEditText.getText().toString().equals("")) {
					if(progressDialog == null) {
			    		progressDialog = new ProgressDialog(Weather.this);
					}
					progressDialog.setMessage("正在获取天气信息...");
					progressDialog.show();
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("theCityCode",new String(cityNameEditText.getText().toString()));
					Task task=new Task(Task.SEARCH_WEATHER_INFO, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(Weather.this, R.string.please_input_city_name, Toast.LENGTH_SHORT).show();
				}
			}
		});
		init();
		MainService.allActivity.add(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			Weather.this.setResult(RESULT_OK,intent);
			Weather.this.finish();
			MainService.allActivity.remove(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void init() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			progressDialog.dismiss();
			Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
			break;
		case Task.SEARCH_WEATHER_INFO:
			HashMap<String, String> hashMap = (HashMap<String, String>)param[1];
			if(hashMap.size() == 1) {
				no_result.setVisibility(View.VISIBLE);
				weather_detaiLayout.setVisibility(View.INVISIBLE);
			} else {
				no_result.setVisibility(View.GONE);
				weather_detaiLayout.setVisibility(View.VISIBLE);
				cityName.setText(((HashMap<String, String>)param[1]).get("cityName"));
				weather_today.setText(((HashMap<String, String>)param[1]).get("weather_today"));
				air.setText(((HashMap<String, String>)param[1]).get("air"));
				direct.setText(((HashMap<String, String>)param[1]).get("direct"));
				weather_tomorrow.setText(((HashMap<String, String>)param[1]).get("weather_tomorrow"));
				tomorrow_temp.setText(((HashMap<String, String>)param[1]).get("tomorrow_temp"));
				tomorrow_wind.setText(((HashMap<String, String>)param[1]).get("tomorrow_wind"));
				weather_after_tomorrow.setText(((HashMap<String, String>)param[1]).get("weather_after_tomorrow"));
				after_tomorrow_temp.setText(((HashMap<String, String>)param[1]).get("after_tomorrow_temp"));
				after_tomorrow_wind.setText(((HashMap<String, String>)param[1]).get("after_tomorrow_wind"));
			}
			progressDialog.dismiss();
			break;
		default:
			break;
		}
	}

}
