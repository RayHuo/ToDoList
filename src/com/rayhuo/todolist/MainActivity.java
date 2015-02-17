package com.rayhuo.todolist;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LinearLayout m_LinearLayout;
	private ListView m_ListView;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		m_LinearLayout = new LinearLayout(this);
		m_LinearLayout.setOrientation(LinearLayout.VERTICAL);
		m_LinearLayout.setBackgroundColor(Color.BLACK);
		
		m_ListView = new ListView(this);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		m_ListView.setBackgroundColor(Color.BLACK);
		
		m_LinearLayout.addView(m_ListView, param);
		
		setContentView(m_LinearLayout);
		
		Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		Toast.makeText(MainActivity.this, "Get " + cur.getCount() + " items.", Toast.LENGTH_SHORT).show();
		//		startManagingCursor(cur);
//		ListAdapter adapter = new SimpleCursorAdapter(this, 
//				android.R.layout.simple_list_item_2,
//				cur,
//				new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER},
//				new int[] {android.R.id.text1, android.R.id.text2});
//		
//		m_ListView.setAdapter(adapter);
//		m_ListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Roll to " + Long.toString(arg0.getSelectedItemId()) + " item.", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "Click to " + Integer.toString(arg2+1) + " item.", Toast.LENGTH_SHORT).show();
//			}
//			
//		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
