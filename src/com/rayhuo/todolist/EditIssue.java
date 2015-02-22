package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditIssue extends Activity {
	
	private MDatabase m_Database = null;
	private Button save_button = null;
	private Button delete_button = null;
	private EditText m_editText = null;
	private String TABLE_NAME = null;
	private String ID = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_issue);
		
		final Bundle get_bundle = getIntent().getExtras();
		TABLE_NAME = get_bundle.getString("table_name");
		ID = get_bundle.getString("id");
		String content = get_bundle.getString("content");
		
		m_editText = (EditText)findViewById(R.id.editText1);
		m_editText.setText(content.toCharArray(), 0, content.length());
		
		
		save_button = (Button)findViewById(R.id.save_button);
		save_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				m_Database = new MDatabase(EditIssue.this);
		        SQLiteDatabase m_db = m_Database.getWritableDatabase();    
		        ContentValues cv = new ContentValues();
		        cv.put("content", m_editText.getText().toString());
		        m_db.update(TABLE_NAME, cv, "_id=?", new String[] { ID });
				m_db.close();
				
				Intent back_intent = new Intent(EditIssue.this, MainActivity.class);
				startActivity(back_intent);
			}
			
		});
		
		
		delete_button = (Button)findViewById(R.id.delete_button);
		delete_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				// 若删除，直接删除后返回
				m_Database = new MDatabase(EditIssue.this);
		        SQLiteDatabase m_db = m_Database.getWritableDatabase();
		        m_db.delete(TABLE_NAME, "_id=?", new String[] { ID });
				
				Intent back_intent = new Intent(EditIssue.this, MainActivity.class);
				startActivity(back_intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_issue, menu);
		return true;
	}

}
