package com.iteam.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.kobjects.base64.Base64;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.iteam.logic.DDMovieActivity;
import com.iteam.logic.Task;
import com.iteam.service.MainService;

public class TranslateDetail extends Activity implements DDMovieActivity{

	Button backButton,soundButton;
	TextView no_sentence,word,symbol,translate_result,sentence;
	ProgressDialog progressDialog;
	LinearLayout translate_detaiLayout;
	boolean hasMp3 = true;
	MediaPlayer mediaPlayer; 
	File mp3File;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.translatedetail);
		translate_detaiLayout = (LinearLayout)findViewById(R.id.translate_result_linearlayout);
		no_sentence = (TextView)findViewById(R.id.translate_no_result_textview);
		word = (TextView)findViewById(R.id.word_textview);
		symbol = (TextView)findViewById(R.id.symbol_textview);
		translate_result = (TextView)findViewById(R.id.translate_result_textview);
		sentence = (TextView)findViewById(R.id.sentense_textview);
		backButton = (Button)findViewById(R.id.buttun_detail_back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				TranslateDetail.this.setResult(RESULT_OK,intent);
				MainService.allActivity.remove(this);
				TranslateDetail.this.finish();
			}
		});
		soundButton = (Button)findViewById(R.id.buttun_detail_sound);
		soundButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(hasMp3) {
					if(mp3File!=null) {
						if(mediaPlayer!=null) {
							mediaPlayer.stop();
						}
						mediaPlayer = MediaPlayer.create(TranslateDetail.this, Uri.parse(mp3File.getPath()));
						try {
							mediaPlayer.prepare();
						} catch (Exception e) {
						}
						mediaPlayer.start();
					} else {
						Toast.makeText(TranslateDetail.this, R.string.no_Mp3_to_play, Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(TranslateDetail.this, R.string.no_Mp3_to_play, Toast.LENGTH_SHORT).show();
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
			Intent intent = getIntent();
			TranslateDetail.this.setResult(RESULT_OK,intent);
			MainService.allActivity.remove(this);
			TranslateDetail.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void init() {
		if(progressDialog == null) {
    		progressDialog = new ProgressDialog(TranslateDetail.this);
		}
		progressDialog.setMessage("正在获取数据...");
		progressDialog.show();
		Intent intent = getIntent();
		String word = intent.getStringExtra("word");
		String translate_result = intent.getStringExtra("translate_result");
		if(intent.getStringExtra("symbol") != null) {
			String symbol = intent.getStringExtra("symbol");
			this.symbol.setText(symbol);
		}
		this.word.setText(word);
		this.translate_result.setText(translate_result);
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("wordKey",word);
		Task task = new Task(Task.GET_SENTENCE, param);
		MainService.newTask(task);
		if(intent.getStringExtra("mp3") != null){
			hasMp3 = true;
			String mp3 = intent.getStringExtra("mp3");
			HashMap<String,Object> param1=new HashMap<String,Object>();
			param1.put("Mp3",mp3);
			Task task1 = new Task(Task.GET_MP3, param1);
			MainService.newTask(task1);
		} else {
			hasMp3 = false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		switch(((Integer)param[0]).intValue()) {
		case -100://
			Toast.makeText(this, R.string.fail, Toast.LENGTH_SHORT).show();
			progressDialog.dismiss();
			break;
		case Task.GET_MP3:
			String str = (String)param[1];
			byte[] bytes = Base64.decode(str);
			try {
				mp3File = new File("/sdcard/" + "file.mp3");
				if (!mp3File.exists()) {
					mp3File.createNewFile();
				} 
				FileOutputStream fos = null;
				fos = new FileOutputStream(mp3File);
				fos.write(bytes);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Toast.makeText(this, R.string.get_mp3_success, Toast.LENGTH_SHORT).show();
			break;
		case Task.GET_SENTENCE:
			List<String> stringList = (ArrayList<String>)param[1];
			String sentence = "";
			for (int i = 0; i < stringList.size(); i++) {
				sentence += ((i+1) + "." +stringList.get(i) + "\n");
			}
			if(sentence.equals("1.- Empty -\n")) {
				this.sentence.setText(R.string.no_sentence);
			} else {
				this.sentence.setText(sentence);	
			}
			progressDialog.dismiss();
			break;
		default:
			break;
		}
	}
	
}
