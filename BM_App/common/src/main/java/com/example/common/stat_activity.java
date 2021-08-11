package com.example.common;


import android.app.Activity;
import android.content.Context;

import com.example.common.BudgetDB;

import java.util.ArrayList;
import java.util.HashMap;

public class stat_activity {
    //final BudgetDB bd= new BudgetDB(Context );
    public ArrayList<String> arrayList(ArrayList<HashMap<String,String>> arrayList){
        ArrayList<String> arry = new ArrayList<String>();
        int a= arrayList.size();
        for(int i=0; i<a;i++){
            String p= String.valueOf(arrayList.get(i));
             arry.add(p.replaceAll("\\=","").replaceAll("\\p{P}", ""));
        }




        return arry;
    }
}