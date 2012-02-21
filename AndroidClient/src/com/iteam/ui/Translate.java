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

public class Translate extends Activity implements DDMovieActivity{

	ImageView userAvatarImageView;
	TextView nickNameTextView;
	EditText wordEditText;
	Button translateButton,detailButton;
	ProgressDialog progressDialog;
	LinearLayout translate_detaiLayout;
	TextView no_result,word,symbol,translate_result;
	String mp3path;
	boolean hasmp3 = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.translate);
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(MainActivity.user.getNickName());
		wordEditText = (EditText)findViewById(R.id.word_edittext);
		translate_detaiLayout = (LinearLayout)findViewById(R.id.translate_result_linearlayout);
		no_result = (TextView)findViewById(R.id.translate_no_result_textview);
		word = (TextView)findViewById(R.id.word_textview);
		symbol = (TextView)findViewById(R.id.symbol_textview);
		translate_result = (TextView)findViewById(R.id.translate_result_textview);
		
		translateButton = (Button)findViewById(R.id.translate_Button);
		translateButton.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				if(!wordEditText.getText().toString().equals("")) {
					if(progressDialog == null) {
			    		progressDialog = new ProgressDialog(Translate.this);
					}
					progressDialog.setMessage("正在获取翻译结果...");
					progressDialog.show();
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("wordKey",new String(wordEditText.getText().toString()));
					Task task = new Task(Task.TRANSLATE, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(Translate.this, R.string.please_input_word, Toast.LENGTH_SHORT).show();
				}
			}
		});
		detailButton = (Button)findViewById(R.id.detail_Button);
		detailButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(word.getVisibility() == View.VISIBLE) {
					Intent intent = new Intent(Translate.this,TranslateDetail.class);
					intent.putExtra("word", word.getText().toString());
					intent.putExtra("symbol", symbol.getText().toString());
					intent.putExtra("translate_result", translate_result.getText().toString());
					if (hasmp3) {
						intent.putExtra("mp3", mp3path);
					}
					startActivityForResult(intent, RESULT_OK);
				} else {
					Toast.makeText(Translate.this, R.string.please_input_word, Toast.LENGTH_SHORT).show();
				}
			}
		});
		init();
		MainService.allActivity.add(this);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			System.out.println("translate---result ok");
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			Translate.this.setResult(RESULT_OK,intent);
			Translate.this.finish();
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
			Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
			break;
		case Task.TRANSLATE:
			HashMap<String, String> hashMap = (HashMap<String, String>)param[1];
			if(hashMap.get("translate_result").equals("Not Found")) {
				no_result.setVisibility(View.VISIBLE);
				translate_detaiLayout.setVisibility(View.GONE);
			} else {
				no_result.setVisibility(View.GONE);
				translate_detaiLayout.setVisibility(View.VISIBLE);
				word.setText(hashMap.get("word"));
				if(!hashMap.get("symbol").equals("anyType{}")) {
					symbol.setText("/" + hashMap.get("symbol") + "/");
				} else {
					symbol.setText("");
				}
				translate_result.setText(hashMap.get("translate_result"));
				if(!hashMap.get("mp3").equals("anyType{}")) {
					hasmp3 = true;
					mp3path = hashMap.get("mp3");
				} else {
					hasmp3 = false;
					mp3path = null;
				}
			}
			progressDialog.dismiss();
			break;
		default:
			break;
		}
	}
}
