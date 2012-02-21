package com.iteam.ui;

import com.iteam.db.SaveIP;
import com.iteam.service.MainService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IP extends Activity{

	EditText ipEditText;
	Button confirmButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.ip);
		ipEditText = (EditText)findViewById(R.id.ip_edittext);
		ipEditText.setText(SaveIP.getIP(this));
		confirmButton = (Button)findViewById(R.id.ip_confirm_button);
		confirmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!ipEditText.getText().toString().equals("")) {
					MainService.IP = ipEditText.getText().toString();
					Intent intent = new Intent(IP.this, Logo.class);
					startActivity(intent);
					finish();
				} else if (ipEditText.getText().length()<8) {
					Toast.makeText(IP.this, R.string.please_check_ip, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(IP.this, R.string.please_input_ip, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
}
