package com.rayhuo.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MDatabase extends SQLiteOpenHelper {

	private static MDatabase theMDatabase;
	private static final String DATABASE_NAME = "ToDoList.db";  
    private static final int DATABASE_VERSION = 1;  
    private static final String TABLE_NAME ="issue";  
	
	private MDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			String createTable = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, content VARCHAR(1000), date DATETIME DEFAULT CURRENT_TIMESTAMP)";
			db.execSQL(createTable);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public static SQLiteDatabase Instance(Context context) {
        if (theMDatabase == null) {  
        	theMDatabase = new MDatabase(context);  
        }  
        return theMDatabase.getReadableDatabase();  
	}
	
	// ≤Â»Î ˝æ›
	public static void insertIssue(Context context, ContentValues cv) {
		SQLiteDatabase db = MDatabase.Instance(context);
		db.insert(TABLE_NAME, null, cv);
        db.close();
	}
}
