package com.rayhuo.todolist;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.provider.Contacts.People;
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
	
	private LinearLayout m_LinearLayout;
	private ListView m_ListView;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);  
		
		m_ListView = new ListView(this);
//		m_ListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        
		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		startManagingCursor(cursor);
		
		ListAdapter listAdapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_2, 
                cursor, 
                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID },
                new int[] { android.R.id.text1, android.R.id.text2 });
		m_ListView.setAdapter(listAdapter);
		
		setContentView(m_ListView);
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
