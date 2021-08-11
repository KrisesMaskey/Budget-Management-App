package com.example.common;

import java.util.ArrayList;

public class montn_converter {
    public int a(String x) {
        if (x.equals("January")) {
            return 1;
        } else if (x.equals("February")) {
            return 2;
        } else if (x.equals("March")) {
            return 3;
        } else if (x.equals("April")) {
            return 4;
        } else if (x.equals("May")) {
            return 5;
        } else if (x.equals("June")) {
            return 6;
        } else if (x.equals("July")) {
            return 7;
        } else if (x.equals("August")) {
            return 8;
        } else if (x.equals("September")) {
            return 9;
        } else if (x.equals("October")) {
            return 10;
        } else if (x.equals("November")) {
            return 11;
        } else {
            return 12;
        }
    }

    //method overloading
    public String a(int x) {
        if (x == 1) {
            return "January";
        } else if (x == 2) {
            return "February";
        } else if (x == 3) {
            return "March";
        } else if (x == 4) {
            return "April";
        } else if (x == 5) {
            return "May";
        } else if (x == 6) {
            return "June";
        } else if (x == 7) {
            return "July";
        } else if (x == 8) {
            return "August";
        } else if (x == 9) {
            return "September";
        } else if (x == 10) {
            return "October";
        } else if (x == 11) {
            return "November";
        } else {
            return "December";
        }
    }


}
 class child extends montn_converter{
        public ArrayList b(ArrayList arrayList){
        ArrayList month= new ArrayList();
        for(int i=arrayList.size()-1;i>arrayList.size()-4;i--) {
            String x= arrayList.get(i).toString();
            if (x.equals("January")) {
                month.add(1);
            } else if (x.equals("February")) {
                month.add(2);
            } else if (x.equals("March")) {
                month.add(3);
            } else if (x.equals("April")) {
                month.add(4);
            } else if (x.equals("May")) {
                month.add(5);
            } else if (x.equals("June")) {
                month.add(6);
            } else if (x.equals("July")) {
                month.add(7);
            } else if (x.equals("August")) {
                month.add(8);
            } else if (x.equals("September")) {
                month.add(9);
            } else if (x.equals("October")) {
                month.add(10);
            } else if (x.equals("November")) {
                month.add(11);
            } else {
                month.add(12);
            }
        }
        return month;
    }


}