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
	
	private ListView m_ListView;
	private SQLiteDatabase m_db;
	private final String TABLE_NAME = "issue";
	private Button add_button;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  // 这句必须写在这个位置，否则会出错
			
//		m_ListView = new ListView(this);
////		m_ListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
//        
//		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//		startManagingCursor(cursor);		
//		ListAdapter listAdapter = new SimpleCursorAdapter(this, 
//				android.R.layout.simple_list_item_2, 
//                cursor, 
//                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID },
//                new int[] { android.R.id.text1, android.R.id.text2 });
//		m_ListView.setAdapter(listAdapter);
//		
//		setContentView(m_ListView);
		
		
		// 初始化数据库
		initDatabase();
		
		// 直接在AddIssue中把数据放进intent里返回来，然后调用这里的insert函数插入到数据库即可
		
		// 创建显示用的ListView
////		m_ListView = new ListView(this);		
//		m_ListView = (ListView)findViewById(R.id.listView1);
//		
//		// 获取数据
//		Cursor cur = m_db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//		startManagingCursor(cur);		
//		ListAdapter listAdapter = new SimpleCursorAdapter(this, 
//				android.R.layout.simple_list_item_2, 
//                cur, 
//                new String[] { "content", "date" },
//                new int[] { android.R.id.text1, android.R.id.text2 });
//		m_ListView.setAdapter(listAdapter);
//		
//		add_button = (Button)findViewById(R.id.add_button);
//		add_button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View view) {
//				// TODO Auto-generated method stub
//				Intent add_intent = new Intent(MainActivity.this, AddIssue.class);
//				startActivity(add_intent);
//			}
//			
//		});
		
	}

	private void initDatabase() {
		ContentValues cv = new ContentValues();
		cv.put("content", "This is a joke!");
		MDatabase.Instance(MainActivity.this);
		MDatabase.insertIssue(MainActivity.this, cv);
		
		cv = new ContentValues();
		cv.put("content", "Happy new year!");
		MDatabase.Instance(MainActivity.this);
		MDatabase.insertIssue(MainActivity.this, cv);
		
		cv = new ContentValues();
		cv.put("content", "Android Learning!");
		MDatabase.Instance(MainActivity.this);
		MDatabase.insertIssue(MainActivity.this, cv);
		
		
//		//打开或创建ToDoList.db数据库  
//        m_db = openOrCreateDatabase("ToDoList.db", Context.MODE_PRIVATE, null);  
//        m_db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
//        
//        //创建issue表  
//        m_db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, content VARCHAR(1000), date DATETIME DEFAULT CURRENT_TIMESTAMP)");  
//        
//        // 人工初始化一些数据
//        ContentValues cv = new ContentValues();
//        cv.put("content", "This is a joke!");
//        m_db.insert(TABLE_NAME, null, cv);
//        
//        cv = new ContentValues();
//        cv.put("content", "Happy new year!");
//        m_db.insert(TABLE_NAME, null, cv);
//        
//        cv = new ContentValues();
//        cv.put("content", "Android Learning!");
//        m_db.insert(TABLE_NAME, null, cv);
	}
	
	private List<String> getData(){       
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
         
        return data;
    }
}
