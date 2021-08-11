package com.example.common;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;


public class DB_to_String extends Activity {

    public String bmdata() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        final MySqliteOpenHelper sql = new MySqliteOpenHelper(DB_to_String.this);
        ArrayList<HashMap<String, String>> data = sql.all();
        String p = String.valueOf(data).replaceAll("\\=", "");
        return (p);
    }
    public String bmdata1() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        final MySqliteOpenHelper sql = new MySqliteOpenHelper(DB_to_String.this);
        ArrayList<HashMap<String, String>> data1 = sql.all1();
        String p1 = String.valueOf(data1).replaceAll("\\=", "");
        return (p1);
    } public String bmdata2() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        final MySqliteOpenHelper sql = new MySqliteOpenHelper(DB_to_String.this);
        ArrayList<HashMap<String, String>> data2 = sql.all2();
        String p2 = String.valueOf(data2).replaceAll("\\=", "");
        return (p2);
    } public String bmdata3() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        final MySqliteOpenHelper sql = new MySqliteOpenHelper(DB_to_String.this);
        ArrayList<HashMap<String, String>> data3 = sql.all3();
        String p3 = String.valueOf(data3).replaceAll("\\=", "");
        return (p3);
    } public String bmdata4() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        final MySqliteOpenHelper sql = new MySqliteOpenHelper(DB_to_String.this);
        ArrayList<HashMap<String, String>> data4 = sql.all4();
        String p4 = String.valueOf(data4).replaceAll("\\=", "");
        return (p4);
    }public String type() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        ArrayList<String> type1 = mysql.GetUsers();
        ArrayList<String> price = mysql.all2();
        String t = String.valueOf(type1).replaceAll("\\=", "");
        return t;
    }public String price() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        ArrayList<String> type1 = mysql.GetUsers();
        ArrayList<String> price = mysql.all2();
        String pp = String.valueOf(price).replaceAll("\\=", "");
        return pp;
    }public ArrayList<String> typ() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        ArrayList<String> type1 = mysql.GetUsers();
        ArrayList<String> price = mysql.all2();
        String t = String.valueOf(type1).replaceAll("\\=", "");
        return type1;
    }public ArrayList price1() {
        final DBHelper1 mysql = new DBHelper1(DB_to_String.this);
        ArrayList<String> type1 = mysql.GetUsers();
        ArrayList<String> price = mysql.all2();
        String t = String.valueOf(type1).replaceAll("\\=", "");
        return price;
    }
}
