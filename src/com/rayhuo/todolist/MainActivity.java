package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.Button;

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
	}

	private void updateSpinner() {
		// TODO Auto-generated method stub
		
	}

	private void initDatabase(SQLiteDatabase db2) {
		// TODO Auto-generated method stub
		
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
