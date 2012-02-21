package com.iteam.ui;

import java.util.HashMap;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements DDMovieActivity{
	
	public static final int REGISTER_SUCCESS = 1;
	
	TextView toRegisterTextView;
	AutoCompleteTextView emailTextView;
	EditText passwordTextView,nickNameEditText;
	Button registerButton;
	ImageView clearImageView;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		emailTextView = (AutoCompleteTextView)findViewById(R.id.email_EditText);
        passwordTextView = (EditText)findViewById(R.id.password_EditText);
        nickNameEditText = (EditText)findViewById(R.id.nickname_EditText);
        clearImageView = (ImageView)findViewById(R.id.clear_UserName);
        clearImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				emailTextView.setText("");
				passwordTextView.setText("");
				nickNameEditText.setText("");
			}
        });
        registerButton = (Button)findViewById(R.id.register_Button);
        registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(emailTextView.getText().toString().equals("")) {
					Toast.makeText(Register.this, R.string.email_is_empty, Toast.LENGTH_SHORT).show();
					return;
				}
				if(passwordTextView.getText().toString().equals("")) {
					Toast.makeText(Register.this, R.string.password_is_empty, Toast.LENGTH_SHORT).show();
					return;
				}
				if(nickNameEditText.getText().toString().equals("")) {
					Toast.makeText(Register.this, R.string.nickname_is_empty, Toast.LENGTH_SHORT).show();
					return;
				}
				register();
			}
		});
        toRegisterTextView = (TextView)findViewById(R.id.toLogin_TextView);
        toRegisterTextView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Register.this,Login.class);
				intent.putExtra("flag", "register");
				startActivity(intent);
				finish();
			}
        });
        //添加到Activity组件集合
        MainService.allActivity.add(this);
	}
	protected void register() {
		if(progressDialog == null) {
    		progressDialog = new ProgressDialog(Register.this);
		}
		progressDialog.setMessage("正在注册...");
		progressDialog.show();
		HashMap<String, String> param= new HashMap<String, String>();
		param.put("email", emailTextView.getText().toString());
		param.put("password", passwordTextView.getText().toString());
		param.put("nickName", nickNameEditText.getText().toString());
		Task task = new Task(Task.REGISTER, param);
		MainService.newTask(task);		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.exit(0);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		init();
	}
	@Override
	public void init() {
	}
	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.reg_failed, Toast.LENGTH_SHORT).show();
			break;
		case REGISTER_SUCCESS:
			progressDialog.dismiss();// 关闭进度条
			Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
			Intent it = new Intent(this,Login.class);
			it.putExtra("flag", "register_success");
			it.putExtra("email", emailTextView.getText().toString());
			it.putExtra("password", passwordTextView.getText().toString());
			this.startActivity(it);
			MainService.allActivity.remove(this);
			finish();
			break;
		default:
			break;
		}
	}
}
