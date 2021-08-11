package com.example.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class remainingbalance extends SQLiteOpenHelper{
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "remaining.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_7 = "Balance";
    public static final String i = "Hello";
    Cursor cursor;

    public remainingbalance(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    public remainingbalance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " Integer primary key autoincrement," + COL_7 + " Text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, user);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public ArrayList<String> all() {


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()) {
            //HashMap<String, String> user = new HashMap<>();
            userList.add(cursor.getString(cursor.getColumnIndex(COL_7)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            //userList.add(user);
        }
        return (userList);


    }
    public void Delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}
