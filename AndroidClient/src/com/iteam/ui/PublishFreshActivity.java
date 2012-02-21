package com.iteam.ui;

import java.util.HashMap;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.UserService;
import net.doudouer.domain.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PublishFreshActivity extends Activity implements DDMovieActivity{
	
	public static final int ADD_A_NEW_GIBBERISH = 1;
	Button sendButton;
	TextView nickNameTextView;
	EditText freshnewsContent;
	ImageView userAvatarImageView;
	User user;
	ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.publishfresh);
		user = MainActivity.getUser();
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(user.getNickName());
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		freshnewsContent = (EditText)findViewById(R.id.edit_publish_content);
		sendButton = (Button)findViewById(R.id.publish_Button);
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!freshnewsContent.getText().toString().equals("")) {
					if(progressDialog == null) {
			    		progressDialog = new ProgressDialog(PublishFreshActivity.this);
					}
					progressDialog.setMessage("正在发送数据...");
					progressDialog.show();
					//当前用户发表一个新评论
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("userID", new Long(user.getId()));
					param.put("content",new String(freshnewsContent.getText().toString()));
					Task task=new Task(Task.ADD_A_NEW_GIBBERISH, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(PublishFreshActivity.this, R.string.please_input_freshnews_content, Toast.LENGTH_SHORT).show();
				}
			}
		});
		init();
		//添加到Activity组件集合
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
		switch (((Integer)param[0]).intValue()) {
		case ADD_A_NEW_GIBBERISH:
			freshnewsContent.setText("");
			progressDialog.dismiss();
			Toast.makeText(PublishFreshActivity.this, R.string.publish_success, Toast.LENGTH_SHORT).show();
			break;
		case -100://
			Toast.makeText(this, R.string.publish_failed, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
