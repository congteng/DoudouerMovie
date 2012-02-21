package com.iteam.ui;


import java.util.HashMap;

import net.doudouer.domain.User;

import com.iteam.db.SaveUserLogin;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.util.NetUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements DDMovieActivity{
	public static final int LOGIN_REFRESH = 1;
	private String[] email_address = { "418181586@qq.com", "liubinjaywln@163.com", "liubin@126.com",
			"qijing222@gmail.com","test1@163.com", "test1@126.com","test1@doudouer.com","test1@sina.com", "test1@sohu.com", "test1@hotmail.com", "@yahoo.com.cn",
			"@live.cn", "@yean.net" };
	TextView toRegisterTextView;
	AutoCompleteTextView emailTextView;
	ArrayAdapter<String> adapter;
	EditText passwordTextView;
	Button loginButton;
	ImageView clearImageView;
	ProgressDialog progressDialog;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailTextView = (AutoCompleteTextView)findViewById(R.id.email_EditText);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, email_address);  
        emailTextView.setAdapter(adapter);  
        passwordTextView = (EditText)findViewById(R.id.password_EditText);
        clearImageView = (ImageView)findViewById(R.id.clear_UserName);
        clearImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				emailTextView.setText("");
				passwordTextView.setText("");
			}
        });
        loginButton = (Button)findViewById(R.id.login_Button);
        loginButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(emailTextView.getText().toString().equals("")) {
					Toast.makeText(Login.this, R.string.email_is_empty, Toast.LENGTH_SHORT).show();
					return;
				}
				if(passwordTextView.getText().toString().equals("")) {
					Toast.makeText(Login.this, R.string.password_is_empty, Toast.LENGTH_SHORT).show();
					return;
				}
				logining();
			}
		});
        toRegisterTextView = (TextView)findViewById(R.id.toRegister_TextView);
        toRegisterTextView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Login.this,Register.class);
				startActivity(intent);
			}
        });
    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MainService.promptExit(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

    private void logining(){
    	if(progressDialog == null) {
    		progressDialog = new ProgressDialog(Login.this);
		}
		progressDialog.setMessage("正在登录...");
		progressDialog.show();
		HashMap<String, String> param= new HashMap<String, String>();
		param.put("email", emailTextView.getText().toString());
		param.put("password", passwordTextView.getText().toString());
		Task task = new Task(Task.LOGIN, param);
		MainService.newTask(task);
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();
		if(intent.getStringExtra("flag").equals("logo")) {
			init();
		} else if(intent.getStringExtra("flag").equals("register")) {
			
		} else if(intent.getStringExtra("flag").equals("register_success")){
			emailTextView.setText(intent.getStringExtra("email"));
			passwordTextView.setText(intent.getStringExtra("password"));
			logining();
		} else if(intent.getStringExtra("flag").equals("main")) {
			//添加到Activity组件集合
	        MainService.allActivity.add(this);
		}
	}

	@Override
	public void init() {
		if(NetUtil.checkNet(this)) {//检测网络成功
			Intent intent = new Intent("com.iteam.logic.MainService");
			this.startService(intent);
			//添加到Activity组件集合
	        MainService.allActivity.add(this);
			String params[] = SaveUserLogin.getUser(this);
			if(params[0] == null) {
				
			} else {//已经记录用户，自动登录
				emailTextView.setText(SaveUserLogin.getUser(this)[0]);
				passwordTextView.setText(SaveUserLogin.getUser(this)[1]);
				logining();
			}
		} else {//检测网络失败
			MainService.AlertNetError(this);
		}
	}

	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
			break;
		case LOGIN_REFRESH:
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();// 关闭进度条
				Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();
				User user = (User)param[1];
				Bundle bundle=new Bundle();           
				bundle.putSerializable("user",user);
				Intent it=new Intent(this,MainActivity.class);
				it.putExtras(bundle);  
				this.startActivity(it);
				MainService.allActivity.remove(this);
				finish();	
			}
			break;
		default:
			break;
		}
	}
}