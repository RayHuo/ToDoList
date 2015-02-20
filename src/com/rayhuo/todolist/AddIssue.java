package com.rayhuo.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddIssue extends Activity {
	private Button save_button;
	private EditText m_editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_issue);
		
		m_editText = (EditText)findViewById(R.id.editText1);
		
		// 需要把SQLiteDatabase封装成一个单例来使用。
		
		save_button = (Button)findViewById(R.id.save_button);
		save_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				String new_content = m_editText.getText().toString();
				Intent intent = new Intent(AddIssue.this, MainActivity.class);
				intent.setAction(Intent.ACTION_SEND);
				intent.putExtra(Intent.EXTRA_TEXT, new_content);        // 参数是键值对，name value
				intent.setType("text/plain");
			    startActivity(intent);                                        // 把intent发送给SecondActivity

				
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_issue, menu);
		return true;
	}

}
