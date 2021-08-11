package com.example.common;

import android.content.Intent;

public class balance1 {
    public int balance(String a,String b, String c, String d){
        int salary= Integer.parseInt(a);
        int inv= Integer.parseInt(b);
        int F_expense= Integer.parseInt(c);
        int expense= Integer.parseInt(d);

        int x= salary-(inv+F_expense);
        return x;
    }
}
