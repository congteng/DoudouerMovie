package com.iteam.ui;

import com.iteam.service.MainService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

public class TeamIntro extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.teamintro);
		//添加到Activity组件集合
        MainService.allActivity.add(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			TeamIntro.this.setResult(RESULT_OK,intent);
			TeamIntro.this.finish();
			MainService.allActivity.remove(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
