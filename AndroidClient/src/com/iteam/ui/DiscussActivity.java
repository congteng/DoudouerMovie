package com.iteam.ui;

import java.util.HashMap;

import net.doudouer.domain.User;

import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiscussActivity extends Activity implements DDMovieActivity{

	public static final int DISCUSS_TO_USERGIBBERISH = 1;
	Button backButton,sendButton;
	EditText discussContent;
	User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.discuss);
		user = MainActivity.getUser();
		discussContent = (EditText)findViewById(R.id.edit_discuss_content);
		backButton = (Button)findViewById(R.id.buttun_discuss_back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				DiscussActivity.this.setResult(RESULT_OK,intent);
				MainService.allActivity.remove(this);
				DiscussActivity.this.finish();
			}
		});
		sendButton = (Button)findViewById(R.id.buttun_discuss_send);
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!discussContent.getText().toString().equals("")) {
					Intent intent = getIntent();
					long index = intent.getLongExtra("ID", 1);
					//当前用户发表一个新评论
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("whoDiss", new Long(user.getId()));
					param.put("discussContent",new String(discussContent.getText().toString()));
					param.put("index",new Long(index));
					Task task=new Task(Task.DISCUSS_TO_USERGIBBERISH, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(DiscussActivity.this, R.string.please_input_response_content, Toast.LENGTH_SHORT).show();
				}
			}
		});
		init();
		//添加到Activity组件集合
        MainService.allActivity.add(this);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void refresh(Object... param) {
		switch (((Integer)param[0]).intValue()) {
		case DISCUSS_TO_USERGIBBERISH:
			Toast.makeText(DiscussActivity.this, R.string.discuss_success, Toast.LENGTH_SHORT).show();
			Intent intent = getIntent();
			DiscussActivity.this.setResult(RESULT_OK,intent);
			MainService.allActivity.remove(this);
			DiscussActivity.this.finish();
			break;
		case -100://
			Toast.makeText(this, R.string.discuss_failed, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			DiscussActivity.this.setResult(RESULT_OK,intent);
			MainService.allActivity.remove(this);
			DiscussActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
