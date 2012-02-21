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
import android.widget.TextView;
import android.widget.Toast;

public class Tel extends Activity implements DDMovieActivity{

	ImageView userAvatarImageView;
	TextView nickNameTextView;
	EditText telNumberEditText;
	TextView tel_resultTextView;
	Button searchButton;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tel);
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(MainActivity.user.getNickName());
		telNumberEditText = (EditText)findViewById(R.id.tel_number_edittext);
		tel_resultTextView = (TextView)findViewById(R.id.tel_result_textview);
		searchButton = (Button)findViewById(R.id.search_tel_button);
		searchButton.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				if(telNumberEditText.getText().toString().toCharArray().length == 11) {
					if(progressDialog == null) {
			    		progressDialog = new ProgressDialog(Tel.this);
					}
					progressDialog.setMessage("正在查询网络数据库...");
					progressDialog.show();
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("mobileCode",new String(telNumberEditText.getText().toString()));
					Task task=new Task(Task.SEARCH_TEL_INFO, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(Tel.this, R.string.please_input_telnumber, Toast.LENGTH_SHORT).show();
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
			Tel.this.setResult(RESULT_OK,intent);
			Tel.this.finish();
			MainService.allActivity.remove(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			progressDialog.dismiss();
			Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
			break;
		case Task.SEARCH_TEL_INFO:
			tel_resultTextView.setText((String)param[1]);
			progressDialog.dismiss();
			break;
		default:
			break;
		}
	}

}
