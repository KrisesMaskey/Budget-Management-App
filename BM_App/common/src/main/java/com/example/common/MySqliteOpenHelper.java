package com.example.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "Info.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Month";
    public static final String COL_3 = "Salary";
    public static final String COL_4 = "Investment";
    public static final String COL_5 = "Reserve";
    public static final String COL_6 = "Expense";
    public static final String COL_7 = "Balance";
    public static final String i = "Hello";
    Cursor cursor;

    public MySqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " Integer primary key autoincrement," + COL_2 + " Text," + COL_4 + " Text,"+ COL_5 + " Text,"+ COL_6 + " Text," + COL_3 + " Text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
/*
    public boolean insert(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, user);
        long result = db.update(TABLE_NAME, contentValues, COL_7+" = ?", new String[] {String.valueOf("Balance")});
        if (result == -1)
            return false;
        else
            return true;
    }
    */

    public boolean insertData(String user, String pass,String s1, String s2, String s3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user);
        contentValues.put(COL_3, pass);
        contentValues.put(COL_4, s1);
        contentValues.put(COL_5, s2);
        contentValues.put(COL_6, s3);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<HashMap<String, String>> all() {


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("", cursor.getString(cursor.getColumnIndex(COL_2)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return (userList);


    }

    public ArrayList<HashMap<String, String>> all1() {


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("", cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return (userList);


    }

    public ArrayList<HashMap<String, String>> all2() {


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("", cursor.getString(cursor.getColumnIndex(COL_4)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return (userList);


    }
    public ArrayList<HashMap<String, String>> all3(){


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("",cursor.getString(cursor.getColumnIndex(COL_5)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return  (userList);



    }
    public ArrayList<HashMap<String, String>> all4(){


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("",cursor.getString(cursor.getColumnIndex(COL_6)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return  (userList);


    }
    public long getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }
    public void Delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}


