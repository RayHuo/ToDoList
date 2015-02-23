package com.rayhuo.todolist;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private MDatabase m_Database = null;
	private ListView m_ListView = null;
	private final String TABLE_NAME = "issue";
	private Button add_button = null;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  // 这句必须写在这个位置，否则会出错
		
        // 初始化数据库
        m_Database = new MDatabase(MainActivity.this);
        final SQLiteDatabase m_db = m_Database.getReadableDatabase();
		
		// 创建显示用的ListView		
		m_ListView = (ListView)findViewById(R.id.listView1);
		
		// 获取数据
		Cursor cur = m_db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		startManagingCursor(cur);		
		ListAdapter listAdapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_2, 
                cur, 
                new String[] { "content", "date" },
                new int[] { android.R.id.text1, android.R.id.text2 });
		m_ListView.setAdapter(listAdapter);
		
		// listView点击事件
		m_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub

				// 获取点击到的issue的信息
				Cursor content_cur= (Cursor)parent.getItemAtPosition(position);
				String content = content_cur.getString(content_cur.getColumnIndex("content"));
//				Toast.makeText(MainActivity.this, "Here : " + content, Toast.LENGTH_SHORT).show();
				
				Bundle edit_bundle = new Bundle();
				edit_bundle.putString("content", content);
				edit_bundle.putString("table_name", TABLE_NAME);
				edit_bundle.putString("id", String.valueOf(id));	// 这个id就是该item在sqlite数据库中表里的id
				// 然后通过intent传给EditIssue这个Activity
				Intent edit_intent = new Intent(MainActivity.this, ItemIssue.class);
				edit_intent.putExtras(edit_bundle);
				startActivity(edit_intent);	
			}
			
			
		});
		
		
		// 添加按钮
		add_button = (Button)findViewById(R.id.add_button);
		add_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent add_intent = new Intent(MainActivity.this, AddIssue.class);
				Bundle add_bundle = new Bundle();
				add_bundle.putString("table_name", TABLE_NAME);	// 键值对
				add_intent.putExtras(add_bundle);
				startActivity(add_intent);
			}
			
		});
		
		m_db.close();
	}

	
	@Override
	// 捕捉返回键动作，然后结束程序
	// 在这一步还是有问题，会跳转到主页面两次
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 在主页按返回键，直接退出程序。
		if(keyCode == KeyEvent.KEYCODE_BACK) {
//			this.finish();
			System.exit(0);
            return true;
		}

		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy ()  {
		
	}
}
