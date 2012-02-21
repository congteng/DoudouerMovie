package com.iteam.ui;

import java.util.HashMap;
import java.util.List;

import net.doudouer.domain.Movie;
import net.doudouer.domain.User;

import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.MovieService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class GuessYourLikeActivity extends Activity implements DDMovieActivity{
	
	ListView listView;
	List<Movie> movies;
	MyAdapter adapter;
	View headerView;
	User user;
	int len = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.guessyourlike);
		user = MainActivity.getUser();
		listView = (ListView)findViewById(R.id.guessyourlike_listview);
		headerView = findViewById(R.id.progress);
		//注册上下文菜单
		registerForContextMenu(listView);
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
	
	//用户触发了上下文菜单的某一项
	public boolean onContextItemSelected(MenuItem item) {
		// 得到菜单项信息
		AdapterContextMenuInfo lm=(AdapterContextMenuInfo)item.getMenuInfo();
		switch (item.getItemId()) {
		case 1:// 查看详细信息
			Intent intent = new Intent(this,CollectActivity.class);
			intent.putExtra("movieID", lm.id);
			startActivityForResult(intent, RESULT_OK);
			break;
		case 2:// 收藏
			HashMap<String,Object> param=new HashMap<String,Object>();
			param.put("userID", new Long(user.getId()));
			param.put("movieID",new Long(lm.id));
			param.put("score",new Double(5));
			Task task=new Task(Task.COLLECT_MOVIE, param);
			MainService.newTask(task);
			Toast.makeText(this, R.string.collect_movie_success, Toast.LENGTH_SHORT);
			break;
		case 3:// 写影评
			Intent intent1 = new Intent(this,WriteReviewActivity.class);
			intent1.putExtra("movieID", lm.id);
			startActivityForResult(intent1, RESULT_OK);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void getRecommendMovies(int howmany) {
		// 加载当前用户的影视信息
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("userID", new Long(user.getId()));
		param.put("howmany",new Integer(howmany));
		Task task = new Task(Task.GET_RECOMMEND_MOVIE_LIST, param);
		MainService.newTask(task);
	}
	
	// 初始化上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo lm = (AdapterContextMenuInfo) menuInfo;
		if (lm.id != 0 && lm.id != -1) {
			menu.setHeaderTitle("我要进行..");
			menu.add(1, 1, 1, lm.id + "查看详细信息");
			menu.add(1, 2, 2, lm.id + "收藏");
			menu.add(1, 3, 3, lm.id + "写影评");
		}
	}
	
	@Override
	public void init() {
		getRecommendMovies(len);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.get_movies_failed, Toast.LENGTH_SHORT).show();
			break;
		case Task.GET_RECOMMEND_MOVIE_LIST:
			movies = (List<Movie>)param[1];
			//创建并绑定适配器
			adapter = new MyAdapter(this,movies);	
			listView.setAdapter(adapter);
			if(((MyAdapter)listView.getAdapter()).headerBar != null) {
				((MyAdapter)listView.getAdapter()).headerBar.setVisibility(View.GONE);
			}
			headerView.setVisibility(View.GONE);
			break;
		case Task.COLLECT_MOVIE:
			Toast.makeText(this, R.string.collect_movie_success, Toast.LENGTH_SHORT).show();
			break;
		case Task.GET_MOVIE_POSTER:
			((MyAdapter)listView.getAdapter()).notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		public List<Movie> moviesList;
		public Context context;
		public ProgressBar headerBar;
		
		public MyAdapter(Context con, List<Movie> list) {
			moviesList = list;
			context = con;
			inflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			return moviesList.size()+1;
		}

		@Override
		public Object getItem(int position) {
			return moviesList.get(position);
		}

		@Override
		public long getItemId(int position) {
			if (position == 0){// 选中第一项
				return 0;
			} else if (position > 0 && (position < this.getCount())) {
				return moviesList.get(position - 1).getId();// 如果用户选中了中间项
			} else {
				return -1;// 表示用户选中最后一项
			}
		}
		
		// 增加更多的数据
		public void addMoreData(List<Movie> moreList) {
			this.moviesList.addAll(moreList);// 把新数据增加到原有集合
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
						getRecommendMovies(len);
					}
				});
				return view;
			} 
			
			ViewHolder holder = null;
			View newView = null;
			if (convertView != null && (convertView.findViewById(R.id.movie_imageView)!=null)) {
				//获取原来内存中保存的条目信息
				newView=convertView;
			} else {
				newView=inflater.inflate(R.layout.list_movie_item, null);
			}
			holder=new ViewHolder();
			holder.movieImageView = (ImageView)newView.findViewById(R.id.movieImage_list);
			holder.movieNameTextView = (TextView)newView.findViewById(R.id.movie_list_name_textView);
			holder.movietypeTextView = (TextView)newView.findViewById(R.id.movie_list_type_textView);
			holder.actorTextView = (TextView)newView.findViewById(R.id.movie_list_actor_textView);
			holder.releaseYeayTextView = (TextView)newView.findViewById(R.id.movie_list_release_year_textView);
			
			BitmapDrawable bitmapDrawable = MovieService.allMoviePoster.get(moviesList.get(position-1).getId());
			if (bitmapDrawable != null) {
				holder.movieImageView.setImageDrawable(bitmapDrawable);
			}
			holder.movieNameTextView.setText(moviesList.get(position-1).getMovieName().toString());
			holder.movietypeTextView.setText(moviesList.get(position-1).getType().toString());
			holder.actorTextView.setText(moviesList.get(position-1).getActor().toString());
			holder.releaseYeayTextView.setText(moviesList.get(position-1).getReleaseYear().toString());
			return newView;
		}
	}
	
	//定义静态类用于获取处理列表中每个条目数据的更新
	private class ViewHolder {
		ImageView movieImageView; 
		TextView movieNameTextView;
		TextView movietypeTextView;
		TextView actorTextView;
		TextView releaseYeayTextView;
	}
	
}
