package com.iteam.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class Logo extends Activity{
	
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.logo);
		imageView = (ImageView)findViewById(R.id.logo);
		showImage(imageView);
	}
	
	private void showImage(ImageView imageView) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
		alphaAnimation.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(Logo.this,Login.class);
				intent.putExtra("flag", "logo");
				startActivity(intent);
				finish();
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
		alphaAnimation.setDuration(3000);
		imageView.setAnimation(alphaAnimation);
	}
	
}
