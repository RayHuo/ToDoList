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
		setContentView(R.layout.activity_main);  // ������д�����λ�ã���������
			
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
		
		
		// ��ʼ�����ݿ�
		initDatabase();
		
		// ֱ����AddIssue�а����ݷŽ�intent�ﷵ������Ȼ����������insert�������뵽���ݿ⼴��
		
		// ������ʾ�õ�ListView
////		m_ListView = new ListView(this);		
//		m_ListView = (ListView)findViewById(R.id.listView1);
//		
//		// ��ȡ����
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
		
		
//		//�򿪻򴴽�ToDoList.db���ݿ�  
//        m_db = openOrCreateDatabase("ToDoList.db", Context.MODE_PRIVATE, null);  
//        m_db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
//        
//        //����issue��  
//        m_db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, content VARCHAR(1000), date DATETIME DEFAULT CURRENT_TIMESTAMP)");  
//        
//        // �˹���ʼ��һЩ����
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
        data.add("��������1");
        data.add("��������2");
        data.add("��������3");
        data.add("��������4");
         
        return data;
    }
}
