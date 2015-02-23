package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddIssue extends Activity {
	
	private Button save_button = null;
	private EditText m_editText = null;
	private MDatabase m_Database = null;
	private String TABLE_NAME = null;
	private Bundle get_bundle = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_issue);
		
		m_editText = (EditText)findViewById(R.id.editText1);
		
		// 需要把SQLiteDatabase封装成一个单例来使用。
//		Intent g_intent = getIntent();
		get_bundle = getIntent().getExtras();
		TABLE_NAME = get_bundle.getString("table_name");
		
		
		save_button = (Button)findViewById(R.id.save_button);
		save_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				m_Database = new MDatabase(AddIssue.this);
		        SQLiteDatabase m_db = m_Database.getWritableDatabase();    
		        ContentValues cv = new ContentValues();
		        cv.put("content", m_editText.getText().toString());
		        m_db.insert(TABLE_NAME, null, cv);
				m_db.close();
				
				Intent back_intent = new Intent(AddIssue.this, MainActivity.class);
				startActivity(back_intent); 
			}
			
		});
	}

	@Override
	// 捕捉返回键动作，然后返回上一级activity
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 在主页按返回键，直接退出程序。
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			Intent back_intent = new Intent(AddIssue.this, MainActivity.class);
			startActivity(back_intent);
			this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_issue, menu);
		return true;
	}

}
