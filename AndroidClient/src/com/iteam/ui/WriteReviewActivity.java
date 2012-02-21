package com.iteam.ui;

import java.util.HashMap;

import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.UserService;

import net.doudouer.domain.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WriteReviewActivity extends Activity implements DDMovieActivity{
	
	public static final int WRITE_FILM_REVIEW = 13;//写影评
	
	Button reviewButton;
	TextView nickNameTextView;
	ImageView userAvatarImageView;
	EditText reviewContent;
	User user;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.writereview);
		user = MainActivity.getUser();
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(user.getNickName());
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		reviewContent = (EditText)findViewById(R.id.edit_review_content);
		reviewButton = (Button)findViewById(R.id.review_Button);
		reviewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!reviewContent.getText().toString().equals("")) {
					if(progressDialog == null) {
			    		progressDialog = new ProgressDialog(WriteReviewActivity.this);
					}
					progressDialog.setMessage("正在发送数据...");
					progressDialog.show();
					//当前用户发表新影评
					Intent intent = getIntent();
					Long movieID = intent.getLongExtra("movieID", 1);
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("userID", new Long(user.getId()));
					param.put("content",new String(reviewContent.getText().toString()));
					param.put("movieID", new Long(movieID));
					Task task=new Task(Task.WRITE_FILM_REVIEW, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(WriteReviewActivity.this, R.string.please_input_freshnews_content, Toast.LENGTH_SHORT).show();
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
		case -100://
			Toast.makeText(this, R.string.publish_failed, Toast.LENGTH_SHORT).show();
			break;
		case WRITE_FILM_REVIEW:
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
				Toast.makeText(this, R.string.publish_success, Toast.LENGTH_SHORT).show();
				Intent intent = getIntent();
				WriteReviewActivity.this.setResult(RESULT_OK,intent);
				MainService.allActivity.remove(this);
				WriteReviewActivity.this.finish();
			}
			break;
		default:
			break;
		}
	}
}
