package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemIssue extends Activity {

	private Button edit_button = null;
	private Button delete_button = null;
	private TextView m_textView = null;
	private MDatabase m_Database = null;
	private String TABLE_NAME = null;
	private String ID = null;
	private Bundle get_bundle = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_issue);
		
		get_bundle = getIntent().getExtras();
		TABLE_NAME = get_bundle.getString("table_name");
		ID = get_bundle.getString("id");
		
		m_textView = (TextView)findViewById(R.id.textView1);
		m_textView.setText(get_bundle.getString("content"));
//		m_textView.setText("We come here!");
		
		// �༭��
		edit_button = (Button)findViewById(R.id.edit_button);
		edit_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// �����༭ҳ��
		        Intent edit_intent = new Intent(ItemIssue.this, EditIssue.class);
		        edit_intent.putExtras(get_bundle);
		        startActivity(edit_intent);
			}
			
		});
		
		
		// ɾ����
		delete_button = (Button)findViewById(R.id.delete_button);
		delete_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				m_Database = new MDatabase(ItemIssue.this);
		        SQLiteDatabase m_db = m_Database.getWritableDatabase();
		        String[] args = { ID };
		        m_db.delete(TABLE_NAME, "_id=?", args);
		        
		        // ɾ���󷵻�MainActivity
		        Intent back_intent = new Intent(ItemIssue.this, MainActivity.class);
		        startActivity(back_intent);
			}
			
		});
	}

	
	@Override
	// ��׽���ؼ�������Ȼ�󷵻���һ��activity
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ����ҳ�����ؼ���ֱ���˳�����
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			Intent back_intent = new Intent(ItemIssue.this, MainActivity.class);
			startActivity(back_intent);	// ��ס��ЩActivity����Ҫ��ȡbundle������Щ�ǲ���Ҫ�ģ����������д
			this.finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_issue, menu);
		return true;
	}

}
