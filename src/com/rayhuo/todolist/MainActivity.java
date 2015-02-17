package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	SQLiteDatabase db;
	public String db_name = "gallery.sqlite";
	public String table_name = "pic";
	
	final DBHelper dbhelper = new DBHelper(this, db_name, null, 1);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button addButton = (Button)findViewById(R.id.add_button);
		Button deleteButton = (Button)findViewById(R.id.delete_button);
		Button modifyButton = (Button)findViewById(R.id.modify_button);
		Button queryButton = (Button)findViewById(R.id.query_button);
		
		// 从辅助类获得数据库对象
		db = dbhelper.getWritableDatabase();
		
		// 初始化数据
		initDatabase(db);
		
		// 更新下拉列表中的数据
		updateSpinner();
		
		// 定义按钮点击监听器
		OnClickListener ocl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// ContentValues对象
				ContentValues cv = new ContentValues();
				
				switch (v.getId()) {
					case R.id.add_button:
						cv.put("filename", "pic5.jpg");
						cv.put("description", "picture 5");
						
						long long1 = db.insert("pic", "", cv);
						// 添加成功后返回行号，失败后返回-1
						if(long1 == -1) {
							Toast.makeText(MainActivity.this, "ID " + long1 + ", Add failed", Toast.LENGTH_SHORT).show();
						}
						else {
							Toast.makeText(MainActivity.this, "ID " + long1 + ", Add Successfully", Toast.LENGTH_SHORT).show();
						}
						
						// 更新一下下拉列表
						updateSpinner();
						break;
						
					case R.id.delete_button:
						long long2 = db.delete("pic", "description='picture 5'", null);
						// 删除失败后返回0，成功则返回删除的条数
						Toast.makeText(MainActivity.this, "Delete " + long2 + " items", Toast.LENGTH_SHORT).show();
						updateSpinner();
						break;
						
					case R.id.modify_button:
						cv.put("filename", "pic0.jpg");
						cv.put("description", "picture 0");
						int long3 = db.update("pic", cv, "filename='pic.jpg'", null);
						Toast.makeText(MainActivity.this, "Update " + long3 + " items", Toast.LENGTH_SHORT).show();
						updateSpinner();
						break;
						
					case R.id.query_button:
						// 这个Cursor（游标）是指向返回的记录数组，查询可能返回一系列的结果，游标的功能类似迭代器
						Cursor c = db.query("pic", null, null, null, null, null, null);
						Toast.makeText(MainActivity.this, "Get total " + c.getCount() + " items. As following: ", Toast.LENGTH_SHORT).show();
						
						for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
							Toast.makeText(MainActivity.this, "The " + c.getInt(0) + " item. filename is " + c.getString(1) + ", description is " + c.getString(2), Toast.LENGTH_SHORT).show();
						}
						
						updateSpinner();
						break;
						
					default:
						break;
				}
			}
		};
		
		addButton.setOnClickListener(ocl);
		deleteButton.setOnClickListener(ocl);
		modifyButton.setOnClickListener(ocl);
		queryButton.setOnClickListener(ocl);
		
	}

	@SuppressWarnings("deprecation")
	private void updateSpinner() {
		// TODO Auto-generated method stub
		
		// 定义UI组件
		final TextView tv = (TextView)findViewById(R.id.TextView02);
		Spinner s = (Spinner)findViewById(R.id.Spinner01);
		
		//从数据库中获取数据放入游标Cursor对象
		final Cursor cursor = db.query("pic", null, null, null, null, null, null);
		
		//创建简单游标匹配器
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				                android.R.layout.simple_spinner_item, cursor, new String[] {
				                        "fileName", "description" }, new int[] {
				                        android.R.id.text1, android.R.id.text2 });
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//给下拉列表设置匹配器
		s.setAdapter(adapter);
		
		//定义子元素选择监听器
		OnItemSelectedListener oisl = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cursor.moveToPosition(position);
				tv.setText("当前pic的描述为：" + cursor.getString(2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

	private void initDatabase(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		cv.put("filename", "pic1.jpg");
		cv.put("description", "picture 1");
		db.insert(table_name, "", cv);
		
		cv.put("filename", "pic2.jpg");
		cv.put("description", "picture 2");
		db.insert(table_name, "", cv);
		
		cv.put("filename", "pic3.jpg");
		cv.put("description", "picture 3");
		db.insert(table_name, "", cv);
		
		cv.put("filename", "pic4.jpg");
		cv.put("description", "picture 4");
		db.insert(table_name, "", cv);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		db.delete(table_name, null, null);
		updateSpinner();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
