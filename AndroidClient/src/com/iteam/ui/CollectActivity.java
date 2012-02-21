package com.iteam.ui;

import java.util.HashMap;

import net.doudouer.domain.Movie;
import net.doudouer.domain.User;

import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;
import com.iteam.service.MovieService;
import com.iteam.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class CollectActivity extends Activity implements DDMovieActivity{
	
	public static final int COLLECT_MOVIE = 1;
	public static final int GET_MOVIE_BY_ID = 2;
	
	Button writeFilmReviewButton,collectButton;
	RatingBar scoreRatingBar;
	ImageView userAvatarImageView;
	ImageView moviePicImageView;
	ProgressDialog progressDialog;
	TextView movieNameTextView,actorTextView,countryTextView,descriptionTextView,directorTextView,
			languageTextView,releaseYearTextView,typeTextView;
	TextView nickNameTextView;
	double score = 0;
	Movie movie;
	User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.collect);
		user = MainActivity.getUser();
		nickNameTextView = (TextView)findViewById(R.id.nickName_TextView);
		nickNameTextView.setText(user.getNickName());
		movieNameTextView = (TextView)findViewById(R.id.movie_name_textView);
		actorTextView = (TextView)findViewById(R.id.movie_actor_textView);
		countryTextView = (TextView)findViewById(R.id.movie_country_textView);
		descriptionTextView = (TextView)findViewById(R.id.movie_description_textView);
		directorTextView = (TextView)findViewById(R.id.movie_director_textView);
		languageTextView = (TextView)findViewById(R.id.movie_language_textView);
		releaseYearTextView = (TextView)findViewById(R.id.movie_release_year_textView);
		typeTextView = (TextView)findViewById(R.id.movie_type_textView);
		userAvatarImageView = (ImageView)findViewById(R.id.user_avatar_ImageView);
		userAvatarImageView.setBackgroundDrawable(UserService.nowUserIcon);
		moviePicImageView = (ImageView)findViewById(R.id.movie_imageView);
		writeFilmReviewButton = (Button)findViewById(R.id.writeFilmReview_Button);
		writeFilmReviewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CollectActivity.this,WriteReviewActivity.class);
				intent.putExtra("movieID", movie.getId());
				startActivityForResult(intent, RESULT_OK);
			}
		});
		collectButton = (Button)findViewById(R.id.collect_Button);
		collectButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(score != 0) {
					Intent intent = getIntent();
					Long movieID = intent.getLongExtra("movieID", 1);
					HashMap<String,Object> param=new HashMap<String,Object>();
					param.put("userID", new Long(user.getId()));
					param.put("movieID",new Long(movieID));
					param.put("score",new Double(score));
					Task task=new Task(Task.COLLECT_MOVIE, param);
					MainService.newTask(task);
				} else {
					Toast.makeText(CollectActivity.this, R.string.please_score_movie_first, Toast.LENGTH_SHORT).show();
				}
			}
		});
		scoreRatingBar = (RatingBar)findViewById(R.id.score_ratingBar);
		scoreRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				score = rating;
				System.out.println("score----" + score);
			}
		});
		//添加到Activity组件集合
        MainService.allActivity.add(this);
		init();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			break;
		default:
			break;
		}
	}
	
	@Override
	public void init() {
		if(progressDialog == null) {
    		progressDialog = new ProgressDialog(CollectActivity.this);
		}
		progressDialog.setMessage("正在获取电影信息...");
		progressDialog.show();
		Intent intent = getIntent();
		Long id = intent.getLongExtra("movieID", 1);
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("id",new Long(id));
		Task task = new Task(Task.GET_MOVIE_BY_ID, param);
		MainService.newTask(task);
	}

	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			progressDialog.dismiss();
			Toast.makeText(this, R.string.collect_movie_failed, Toast.LENGTH_SHORT).show();
			break;
		case Task.COLLECT_MOVIE:
			Toast.makeText(CollectActivity.this, R.string.collect_movie_success, Toast.LENGTH_SHORT).show();
			Intent intent = getIntent();
			CollectActivity.this.setResult(RESULT_OK,intent);
			CollectActivity.this.finish();
			break;
		case GET_MOVIE_BY_ID:
			if (progressDialog.isShowing()) {
				movie = (Movie)param[1];
				HashMap<Object, Object> param2 = new HashMap<Object, Object>();
				param2.put("id",movie.getId());
				param2.put("path", movie.getAvatarLink());
				Task task2 = new Task(Task.GET_MOVIE_POSTER,param2);
				MainService.newTask(task2);
				actorTextView.setText(movie.getActor().toString());
				countryTextView.setText(movie.getCountry().toString());
				descriptionTextView.setText("简介：" + movie.getDescription());
				directorTextView.setText(movie.getDirector().toString());
				languageTextView.setText(movie.getLanguage());
				movieNameTextView.setText(movie.getMovieName());
				releaseYearTextView.setText(movie.getReleaseYear());
				typeTextView.setText(movie.getType());
				progressDialog.dismiss();
			}
			break;
		case Task.GET_MOVIE_POSTER:
			moviePicImageView.setImageDrawable(MovieService.allMoviePoster.get(movie.getId()));
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = getIntent();
			CollectActivity.this.setResult(RESULT_OK,intent);
			CollectActivity.this.finish();
			MainService.allActivity.remove(this);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
