package com.example.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ExpenseDB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "exbudget";
    private static final String TABLE_Users = "Expense";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Item";
    private static final String KEY_LOC = "Name";
    private static final String KEY_LOC1 = "Date";
    private static final String KEY_LOC2 = "Price";
    private static final String KEY_LOC3 = "Month";

    // private static final String KEY_DESG = "designation";
    public ExpenseDB(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT," + KEY_LOC1 + " TEXT,"+ KEY_LOC2 + " TEXT, "+ KEY_LOC3 + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    public boolean insertUserDetails(String s5, String s1, String s3, String s2,String mm){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, s5);
        cValues.put(KEY_LOC, s1);
        cValues.put(KEY_LOC1, s3);
        cValues.put(KEY_LOC2, s2);
        cValues.put(KEY_LOC3,mm);
        //cValues.put(KEY_DESG, designation);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        // db.close();
        if (newRowId==-1){
            return false;
        }else{
            return  true;
        }
    }
    public ArrayList senderMobile(String activeuser){ //<<<< NOTE long
        montn_converter mt= new montn_converter();
        int x= mt.a(activeuser);
        String k= String.valueOf(x);
        ArrayList<String > y= new ArrayList<>();

        //<<<< default return to indicate no such row
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Expense where Month = ?",new String[]{k});
        cursor.moveToFirst();//<<<< Move to the first row (should only be 1)
        while (cursor.moveToNext()) {

            y.add(cursor.getString(2)); //<< get the data from the column
           y.add(cursor.getString(3));
            y.add(cursor.getString(4));
            y.add(cursor.getString(5));

    }
        return y;
    }

    // Get User Details
    public ArrayList<String> GetUsers(String x){
        montn_converter mt= new montn_converter();
        int n= mt.a(x);
        String k= String.valueOf(n);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> userList = new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Expense where Month=?",new String[]{k});
        cursor.moveToFirst();//<<<< Move to the first row (should only be 1)
        if(cursor.getCount()>0) {
            userList.add(cursor.getString(1));
        }
        while (cursor.moveToNext()){
            userList.add(cursor.getString(1));
            // user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));

        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<String> all(String x){
        montn_converter mt= new montn_converter();
        int n= mt.a(x);
        String k= String.valueOf(n);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> userList = new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Expense where Month=?",new String[]{k});
        cursor.moveToFirst();//<<<< Move to the first row (should only be 1)
        if(cursor.getCount()>0) {
            userList.add(cursor.getString(cursor.getColumnIndex(KEY_LOC)));
        }
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            userList.add( cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            // user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            //user.put("Price", cursor.getString(cursor.getColumnIndex(KEY_LOC2)));
        }
        return  userList;
    }

    public ArrayList<String> all1(String x){
        montn_converter mt= new montn_converter();
        int n= mt.a(x);
        String k= String.valueOf(n);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList< String> userList = new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Expense where Month=?",new String[]{k});
        cursor.moveToFirst();//<<<< Move to the first row (should only be 1)
        if(cursor.getCount()>0) {
            userList.add(cursor.getString(cursor.getColumnIndex(KEY_LOC1)));
        }
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            //user.put("",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            userList.add(cursor.getString(cursor.getColumnIndex(KEY_LOC1)));
        }
        return  userList;
    }
    public ArrayList<String> all2(String x){
        montn_converter mt= new montn_converter();
        int n= mt.a(x);
        String k= String.valueOf(n);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList< String> userList = new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Expense where Month=?",new String[]{k});
        cursor.moveToFirst();//<<<< Move to the first row (should only be 1)
        if(cursor.getCount()>0) {
            userList.add(cursor.getString(cursor.getColumnIndex(KEY_LOC2)));
        }
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            userList.add(cursor.getString(cursor.getColumnIndex(KEY_LOC2)));
            // user.put("designation",cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            //user.put("Password",cursor.getString(cursor.getColumnIndex(KEY_LOC)));

        }
        return  userList;
    }





    // Delete User Details
    public void DeleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_Users);
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String location, String designation, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_LOC, location);
        // cVals.put(KEY_DESG, designation);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
