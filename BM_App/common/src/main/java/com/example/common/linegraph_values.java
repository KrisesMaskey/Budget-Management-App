package com.example.common;

import android.app.Activity;
import android.content.Intent;

import com.example.common.BudgetDB;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;

public class linegraph_values {
    final ArrayList<String> arr= new ArrayList<>();
    final ArrayList<String> arr1= new ArrayList<>();
    final ArrayList<String> arr1x= new ArrayList<>();
    final ArrayList<String> arr1y= new ArrayList<>();
    final ArrayList<Integer> arr2= new ArrayList<>();
    final ArrayList<Integer> arr3= new ArrayList<>();
    final ArrayList<Integer> arr4= new ArrayList<>();
    public int maximum(ArrayList arrayList){
        int total=0;
        int temp=0,m=0;

        ArrayList<HashMap<String,String>> x= arrayList;

        for(int i=0;i<arrayList.size();i++){
            arr.add(String.valueOf(arrayList.get(i)).replaceAll("\\=","").replaceAll("\\p{P}", ""));
            String k= arr.get(i);
            temp= Integer.parseInt(k);
            if(temp>m){
                m=temp;
            }
        }

        return m+8000;
    }
    public ArrayList yaxis(ArrayList array){
        int x= Integer.parseInt(arr.get(arr.size()-1));
        int temp=0,total=0;
        arr2.add(x/1000);
        if(!array.isEmpty()) {
            for (int i = 0; i < array.size(); i++) {

                arr1.add(String.valueOf(array.get(i)));
                String k = (array.get(i).toString());
                temp = Integer.parseInt(k);
                total = x - temp;
                arr2.add(total / 1000);

            }
        }else {
            arr2.add(0);
        }
        Collections.sort(arr2,Collections.<Integer>reverseOrder());
        return arr2;

    }
    public ArrayList yaxis1(ArrayList array){
        int x= Integer.parseInt(arr.get(arr.size()-2));
        int temp=0,total=0;
        arr3.add(x/1000);
        if(!array.isEmpty()) {
            for (int i = 0; i < array.size(); i++) {

                arr1x.add(String.valueOf(array.get(i)).replaceAll("\\=", "").replaceAll("\\p{P}", ""));
                String k = arr1x.get(i);
                temp = Integer.parseInt(k);
                total = x - temp;
                arr3.add(total / 1000);
            }
        }else {
            arr3.add(0);
        }
        Collections.sort(arr3,Collections.<Integer>reverseOrder());
        return arr3;

    }
    public ArrayList yaxis2(ArrayList array){
        int x= Integer.parseInt(arr.get(arr.size()-3));
        int temp=0,total=0;
        arr4.add(x/1000);
        if(!array.isEmpty()) {
            for (int i = 0; i < array.size(); i++) {
                arr1y.add(String.valueOf(array.get(i)).replaceAll("\\=", "").replaceAll("\\p{P}", ""));
                String k = arr1y.get(i);
                temp = Integer.parseInt(k);
                total = x - temp;
                arr4.add(total / 1000);
            }
        }else {
            arr4.add(0);
        }
        Collections.sort(arr4,Collections.<Integer>reverseOrder());
        return arr4;

    }
    public ArrayList month(ArrayList a){
        ArrayList x= new ArrayList();
        for(int i=0;i<a.size();i++) {
            String k= a.get(i).toString().replaceAll("\\=","").replaceAll("\\p{P}", "");
            x.add(k);
        }
        return x;
    }

}
