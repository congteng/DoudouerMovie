package com.iteam.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.User;
import com.iteam.logic.Task;
import com.iteam.logic.DDMovieActivity;
import com.iteam.service.MainService;
import com.iteam.service.UserService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class FreshNewsActivity extends Activity implements DDMovieActivity{
	
	public static final int GET_FRESHNEWS = 1;
	public static final int GET_ALLUSER_AVATAR = 2;
	public static final int ADD_NEW_FRIENDS = 12;//增加好友
	ListView listView;
	List<FreshNews> freshNews;
	MyAdapter adapter;
	boolean isRefresh = true;
	User user;
	View headerView;
	int off = 0;
	int len = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freshnews);
		user = MainActivity.getUser();
		listView = (ListView)findViewById(R.id.freshnews_listview);
		headerView = findViewById(R.id.progress);
		//注册上下文菜单
		registerForContextMenu(listView);
		init();
		//添加到Activity组件集合
        MainService.allActivity.add(this);
        System.out.println("freshnews---oncreate");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			System.out.println("freshnews---result ok");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("freshnews---onpause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("freshnews---onrestart");
	}

	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("freshnews---onstart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("freshnews---onstop");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("freshnews---onresume");
	}

	//用户触发了上下文菜单的某一项
	public boolean onContextItemSelected(MenuItem item) {
		// 得到菜单项信息
		AdapterContextMenuInfo lm=(AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
		case 1:// 评论
			if(((MyAdapter)(listView.getAdapter())).freshNewsList.get(lm.position-1).getNewsType().toString().equals("recordGibberish")) {
				Intent intent = new Intent(this,DiscussActivity.class);
				intent.putExtra("ID", lm.id);
				startActivityForResult(intent, RESULT_OK);
			} else {
				Toast.makeText(this, R.string.no_freshnews_to_discuss, Toast.LENGTH_SHORT).show();
			}
			break;
		case 2:// 查看电影
			if(((MyAdapter)(listView.getAdapter())).freshNewsList.get(lm.position-1).getNewsType().toString().equals("collectFilm")) {
				Intent intent = new Intent(this,CollectActivity.class);
				intent.putExtra("movieID", ((MyAdapter)(listView.getAdapter())).freshNewsList.get(lm.position-1).getNewsID());
				startActivityForResult(intent, RESULT_OK);
			} else {
				Toast.makeText(this, R.string.no_movie_to_collect, Toast.LENGTH_SHORT).show();
			}
			break;
		case 3:// 加好友
			if(((MyAdapter)(listView.getAdapter())).freshNewsList.get(lm.position-1).getNewsType().toString().equals("addFriend")) {
				HashMap<String,Object> param=new HashMap<String,Object>();
				param.put("userID", new Long(user.getId()));
				param.put("id",new Long(((MyAdapter)(listView.getAdapter())).freshNewsList.get(lm.position-1).getNewsID()));
				Task task=new Task(Task.ADD_NEW_FRIENDS, param);
				MainService.newTask(task);
			} else {
				Toast.makeText(this, R.string.no_friend_to_add, Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MainService.promptExit(this.getParent());
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 初始化上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo lm = (AdapterContextMenuInfo) menuInfo;
		if (lm.id != 0 && lm.id != -1) {
			menu.setHeaderTitle("我要进行..");
			menu.add(1, 1, 1, lm.id + "评论");
			menu.add(1, 2, 2, lm.id + "查看电影详情");
			menu.add(1, 3, 3, lm.id + "添加这位好友");
		}
	}
	
	@Override
	public void init() {
		getFreshNews(off, len);
	}
	
	public void getFreshNews(int off, int len) {
		// 加载当前用户的新鲜事信息
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("userID", new Long(user.getId()));
		param.put("off",new Integer(off));
		param.put("len",new Integer(len));
		Task task=new Task(Task.GET_FRESHNEWS, param);
		MainService.newTask(task);
	}
	
	boolean first_refresh = true;

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.get_freshnews_failed, Toast.LENGTH_SHORT).show();
			break;
		case GET_FRESHNEWS:
			if(isRefresh) {
				freshNews = (List<FreshNews>)param[1];
				//创建并绑定适配器
				adapter = new MyAdapter(this,freshNews);	
				listView.setAdapter(adapter);
				if(((MyAdapter)listView.getAdapter()).headerBar != null) {
					((MyAdapter)listView.getAdapter()).headerBar.setVisibility(View.GONE);
				}
			} else {
				((MyAdapter)listView.getAdapter()).addMoreData((List<FreshNews>)param[1]);
				if(((MyAdapter)listView.getAdapter()).footerBar != null) {
					((MyAdapter)listView.getAdapter()).footerBar.setVisibility(View.GONE);
				}
			}
			if (first_refresh) {
				Toast.makeText(this, R.string.getting_user_info, Toast.LENGTH_LONG).show();	
				first_refresh = false;
			}
			headerView.setVisibility(View.GONE);
			break;
		case GET_ALLUSER_AVATAR:
			((MyAdapter)listView.getAdapter()).notifyDataSetChanged();
			break;
		case Task.GET_ALL_USER_NICKNAME:
			((MyAdapter)listView.getAdapter()).notifyDataSetChanged();
			break;
		case ADD_NEW_FRIENDS:
			Toast.makeText(this, R.string.add_new_friend_success, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		public List<FreshNews> freshNewsList;
		public Context context;
		public ProgressBar headerBar,footerBar;
		boolean canDiscuss,canCollect,canAdd;

		public MyAdapter(Context con, List<FreshNews> list) {
			freshNewsList = list;
			context = con;
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return freshNewsList.size()+3;
		}

		@Override
		public Object getItem(int position) {
			return freshNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			if (position == 0){// 选中第一项
				return 0;
			} else if (position > 0 && (position < this.getCount() - 2)) {
				return freshNewsList.get(position - 1).getId();// 如果用户选中了中间项
			} else {
				return -1;// 表示用户选中最后一项
			}
		}
		
		// 增加更多的数据
		public void addMoreData(List<FreshNews> moreList) {
			this.freshNewsList.addAll(moreList);// 把新数据增加到原有集合
			this.notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0){// 第一项返回刷新
				View view = inflater.inflate(
						R.layout.list_header, null);
				headerBar = (ProgressBar)view.findViewById(R.id.refreshHeaderProgressBar);
				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						headerBar.setVisibility(View.VISIBLE);
						isRefresh = true;
						getFreshNews(0, len);
					}
				});
				return view;
			} else if (position == this.getCount()-2) {// 更多
				View view1 = inflater.inflate(
						R.layout.list_footer, null);
				footerBar = (ProgressBar)view1.findViewById(R.id.moreFooterProgressBar);
				view1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						footerBar.setVisibility(View.VISIBLE);
						isRefresh = false;
						off+=len;
						getFreshNews(off, len);
					}
				});
				return view1;
			} else if (position == this.getCount()-1) {
				View view2 = inflater.inflate(
						R.layout.list_footer, null);
				view2.setVisibility(View.GONE);
				return view2;
			}
			
			ViewHolder holder = null;
			View newView = null;
			if (convertView != null && (convertView.findViewById(R.id.avatar_image)!=null)) {
				//获取原来内存中保存的条目信息
				newView=convertView;
			} else {
				newView=inflater.inflate(R.layout.list_item, null);
			}
			holder=new ViewHolder();
			holder.avatorImageView = (ImageView)newView.findViewById(R.id.avatar_image);
			holder.nickNameTextView = (TextView)newView.findViewById(R.id.nickname_freshnews_happento);
			holder.timeTextView = (TextView)newView.findViewById(R.id.timestamp);
			holder.contentTextView = (TextView)newView.findViewById(R.id.freshnews_content);

			holder.avatorImageView.setImageDrawable(UserService.alluserIcon.get(freshNewsList.get(position-1).getHappenTo()));
			
			String nickNameString = UserService.alluserNickName.get(freshNewsList.get(position-1).getHappenTo());
			if (nickNameString == null) {
				nickNameString = "Ta";
			}
			if(freshNewsList.get(position-1).getNewsType().toString().equals("collectFilm")) {
				holder.nickNameTextView.setText(nickNameString + " " + "收藏了电影");
			} else if (freshNewsList.get(position-1).getNewsType().toString().equals("recordGibberish")){
				holder.nickNameTextView.setText(nickNameString + " " + "说");
			} else if (freshNewsList.get(position-1).getNewsType().toString().equals("addFriend")) {
				holder.nickNameTextView.setText(nickNameString + " " + "加了好友");
			} else if (freshNewsList.get(position-1).getNewsType().toString().equals("writeFilmReview")) {
				holder.nickNameTextView.setText(nickNameString + " " + "刚刚");
			}
			
			Date date = new Date(freshNewsList.get(position-1).getTimestamp());
			String time;
			if(date.getMinutes() <10) {
				time = (date.getMonth()+1) +"月"+ date.getDate() +"日 "+ date.getHours() + ":0"+ date.getMinutes();
			} else {
				time = (date.getMonth()+1) +"月"+ date.getDate() +"日 "+ date.getHours() + ":"+ date.getMinutes();
			}
			
			holder.timeTextView.setText(time);
			holder.contentTextView.setText(freshNewsList.get(position-1).getContent().toString());
			
			holder.discuss_avatarImageView1 = (ImageView)newView.findViewById(R.id.avatar_image_in_discuss1);
			holder.discuss_avatarImageView2 = (ImageView)newView.findViewById(R.id.avatar_image_in_discuss2);
			holder.discuss_avatarImageView3 = (ImageView)newView.findViewById(R.id.avatar_image_in_discuss3);
			holder.discuss_nickNameTextView1 = (TextView)newView.findViewById(R.id.discuss_happento1);
			holder.discuss_nickNameTextView2 = (TextView)newView.findViewById(R.id.discuss_happento2);
			holder.discuss_nickNameTextView3 = (TextView)newView.findViewById(R.id.discuss_happento3);
			holder.discuss_contentTextView1 = (TextView)newView.findViewById(R.id.discuss_content1);
			holder.discuss_contentTextView2 = (TextView)newView.findViewById(R.id.discuss_content2);
			holder.discuss_contentTextView3 = (TextView)newView.findViewById(R.id.discuss_content3);
			holder.discuss_LinearLayout1 = (LinearLayout)newView.findViewById(R.id.discuss_linearLayout1);
			holder.discuss_LinearLayout2 = (LinearLayout)newView.findViewById(R.id.discuss_linearLayout2);
			holder.discuss_LinearLayout3 = (LinearLayout)newView.findViewById(R.id.discuss_linearLayout3);
			
			holder.discuss_LinearLayout1.setVisibility(View.GONE);
			holder.discuss_LinearLayout2.setVisibility(View.GONE);
			holder.discuss_LinearLayout3.setVisibility(View.GONE);
			if(freshNewsList.get(position-1).getDiscussList().size()>=1) {
				holder.discuss_LinearLayout1.setVisibility(View.VISIBLE);
				holder.discuss_nickNameTextView1.setText(UserService.alluserNickName.get(freshNewsList.get(position-1).getDiscussList().get(0).getWhoDiss()));
				holder.discuss_contentTextView1.setText(freshNewsList.get(position-1).getDiscussList().get(0).getContent().toString());
				holder.discuss_avatarImageView1.setImageDrawable(UserService.alluserIcon.get(freshNewsList.get(position-1).getDiscussList().get(0).getWhoDiss()));
			}
			if(freshNewsList.get(position-1).getDiscussList().size()>=2) {
				holder.discuss_LinearLayout2.setVisibility(View.VISIBLE);
				holder.discuss_nickNameTextView2.setText(UserService.alluserNickName.get(freshNewsList.get(position-1).getDiscussList().get(1).getWhoDiss()));
				holder.discuss_contentTextView2.setText(freshNewsList.get(position-1).getDiscussList().get(1).getContent().toString());
				holder.discuss_avatarImageView2.setImageDrawable(UserService.alluserIcon.get(freshNewsList.get(position-1).getDiscussList().get(1).getWhoDiss()));
			}
			if(freshNewsList.get(position-1).getDiscussList().size()>=3) {
				holder.discuss_LinearLayout3.setVisibility(View.VISIBLE);
				holder.discuss_nickNameTextView3.setText(UserService.alluserNickName.get(freshNewsList.get(position-1).getDiscussList().get(2).getWhoDiss()));
				holder.discuss_contentTextView3.setText(freshNewsList.get(position-1).getDiscussList().get(2).getContent().toString());
				holder.discuss_avatarImageView3.setImageDrawable(UserService.alluserIcon.get(freshNewsList.get(position-1).getDiscussList().get(2).getWhoDiss()));
			}
			return newView;
		}
	}

	//定义静态类用于获取处理列表中每个条目数据的更新
	private class ViewHolder {
		ImageView avatorImageView;//头像 
		TextView nickNameTextView;//昵称
		TextView timeTextView;//时间
		TextView contentTextView;//内容
		ImageView discuss_avatarImageView1,discuss_avatarImageView2,discuss_avatarImageView3;
		TextView discuss_nickNameTextView1,discuss_nickNameTextView2,discuss_nickNameTextView3;
		TextView discuss_contentTextView1,discuss_contentTextView2,discuss_contentTextView3;
		LinearLayout discuss_LinearLayout1,discuss_LinearLayout2,discuss_LinearLayout3;
	}
	
}
