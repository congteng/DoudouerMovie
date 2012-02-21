package com.iteam.ui;

import net.doudouer.domain.User;
import com.iteam.service.MainService;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity{
	public static final String TAB_PUBLISHFRESH = "tab_publishfresh";
	public static final String TAB_HOME = "tab_home";
	public static final String TAB_LIFEHELPER = "tab_lifehelper";
	TabHost tabHost;
	RadioGroup radioGroup;
	TabSpec publishFresh_TabSpec,home_TabSpec,lifeHelper_TabSpec;
	static User user;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		user = (User)(getIntent().getSerializableExtra("user"));
		tabHost = this.getTabHost();
		//创建子页
		home_TabSpec = tabHost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME);
		home_TabSpec.setContent(new Intent(MainActivity.this, HomeActivity.class));
		tabHost.addTab(home_TabSpec);
		publishFresh_TabSpec = tabHost.newTabSpec(TAB_PUBLISHFRESH).setIndicator(TAB_PUBLISHFRESH);
		publishFresh_TabSpec.setContent(new Intent(MainActivity.this, PublishFreshActivity.class));
		tabHost.addTab(publishFresh_TabSpec);
		lifeHelper_TabSpec = tabHost.newTabSpec(TAB_LIFEHELPER).setIndicator(TAB_LIFEHELPER);
		lifeHelper_TabSpec.setContent(new Intent(MainActivity.this, LifeHelper.class));
		tabHost.addTab(lifeHelper_TabSpec);
		radioGroup = (RadioGroup)findViewById(R.id.main_radio);
		//设置监听
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.publishfresh://发布新鲜事
					tabHost.setCurrentTabByTag(TAB_PUBLISHFRESH);
					break;
				case R.id.home://主页
					tabHost.setCurrentTabByTag(TAB_HOME);
					break;
				case R.id.lifehelper://？？
					tabHost.setCurrentTabByTag(TAB_LIFEHELPER);
					break;
				default:
					break;
				}
			}
		});
		//添加到Activity组件集合
        MainService.allActivity.add(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "团队简介").setIcon(R.drawable.menu_set);
		menu.add(1, 2, 1, "切换账号").setIcon(R.drawable.menu_switch_account);
		menu.add(1, 3, 2, "退出").setIcon(R.drawable.menu_exit);
		System.out.println("optionmenu--main");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:// 团队简介
			Intent it = new Intent(this, TeamIntro.class);
			startActivityForResult(it, RESULT_OK);
			break;
		case 2:// 切换账号
			Intent intent = new Intent(this, Login.class);
			intent.putExtra("flag", "main");
			startActivity(intent);
			MainService.allActivity.removeAll(MainService.allActivity);
			finish();
			break;
		case 3:// 退出
			MainService.promptExit(this);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public static User getUser() {
		return user;
	}
}
