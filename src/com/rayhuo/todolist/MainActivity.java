package com.rayhuo.todolist;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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
	private Boolean flag = false;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  // 这句必须写在这个位置，否则会出错
		
        // 初始化数据库
//     	initDatabase(cv);
        m_Database = new MDatabase(MainActivity.this);
        SQLiteDatabase m_db = m_Database.getReadableDatabase();
		
		// 直接在AddIssue中把数据放进intent里返回来，然后调用这里的insert函数插入到数据库即可
		
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
		
		add_button = (Button)findViewById(R.id.add_button);
		add_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent add_intent = new Intent(MainActivity.this, AddIssue.class);
				add_intent.setAction(Intent.ACTION_SEND);
				add_intent.putExtra(Intent.EXTRA_TEXT, TABLE_NAME);        // 参数是键值对，name value
				add_intent.setType("text/plain");
				startActivity(add_intent);
			}
			
		});
		
		m_db.close();
	}

//	private void initDatabase(ContentValues c) {		
//		//打开或创建ToDoList.db数据库  
//        m_db = openOrCreateDatabase("ToDoList.db", Context.MODE_PRIVATE, null);  
//        m_db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
//        
//        //创建issue表  
//        m_db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, content VARCHAR(1000), date DATETIME DEFAULT CURRENT_TIMESTAMP)");  
//        
//        if(c != null)
//        	m_db.insert(TABLE_NAME, null, c);
//        m_db.close();
//        // 人工初始化一些数据
////        if(!flag) {
////	        ContentValues cv = new ContentValues();
////	        cv.put("content", "This is a joke!");
////	        m_db.insert(TABLE_NAME, null, cv);
////	        
////	        cv = new ContentValues();
////	        cv.put("content", "Happy new year!");
////	        m_db.insert(TABLE_NAME, null, cv);
////	        
////	        cv = new ContentValues();
////	        cv.put("content", "Android Learning!");
////	        m_db.insert(TABLE_NAME, null, cv);
////	        
////	        flag = true;
////        }
//	}

	@Override
	protected void onDestroy ()  {
//		m_db.close();
	}
}
