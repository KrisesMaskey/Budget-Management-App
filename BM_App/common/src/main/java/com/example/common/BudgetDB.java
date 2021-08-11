package com.example.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;



public class BudgetDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String DATABASE_NAME = "All.db";
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

    public BudgetDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " Integer primary key autoincrement," + COL_2 + " Text," + COL_4 + " Text," + COL_5 + " Text," + COL_7 + " Text," + COL_6 + " Text," + COL_3 + " Text not null )");
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
    public boolean insertData(String user, String pass,String s1, String s2, String s3, String s4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user);
        contentValues.put(COL_3, pass);
        contentValues.put(COL_4, s1);
        contentValues.put(COL_5, s2);
        contentValues.put(COL_6, s3);
        contentValues.put(COL_7,s4);
        //contentValues.put(COL_7,s5);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public ArrayList senderMobile(String activeuser){ //<<<< NOTE long
        String rv=null ;
        String rv1=null ;
        String rv2=null ;
        String rv3=null ;
        String rv4=null ;
        String rv5=null ;
        //<<<< default return to indicate no such row
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student_table where Month = ?",new String[]{activeuser});
        if (cursor.moveToFirst()) { //<<<< Move to the first row (should only be 1)
            rv = cursor.getString(2); //<< get the data from the column
            rv1=cursor.getString(3);
            rv2=cursor.getString(0);
            rv3=cursor.getString(5);
            rv4=cursor.getString(6);


        }
        ArrayList<String> lol= new ArrayList<>();
        lol.add(rv);
        lol.add(rv1);
        lol.add(rv2);
        lol.add(rv3);
        lol.add(rv4);
        return lol;
    }
    public ArrayList bb(String activeuser){ //<<<< NOTE long
        String rv=null ;

        //<<<< default return to indicate no such row
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from student_table where ID = ?",new String[]{activeuser});
        if (cursor.moveToFirst()) { //<<<< Move to the first row (should only be 1)
            rv = cursor.getString(4); //<< get the data from the column



        }
        ArrayList<String> lul= new ArrayList<>();
        lul.add(rv);
        return lul;
    }

    public ArrayList<String> all() {


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            userList.add(0, cursor.getString(cursor.getColumnIndex(COL_2)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            //userList.add(user);
        }
        return userList;


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
            userList.add(user);
        }
        return  (userList);


    } public ArrayList<HashMap<String, String>> all5(){


        String countQuery = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(countQuery, null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("",cursor.getString(cursor.getColumnIndex(COL_7)));
            //user.put("",cursor.getString(cursor.getColumnIndex(COL_3)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            userList.add(user);
        }
        return  (userList);


    }

    public void Delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
    public void Deletespecific(String a){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from student_table where Month = ?",new String[]{a});
        db.close();
    }
}
