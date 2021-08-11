package com.example.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Priority {
   private String xx;
   private int salary=0;
    private int inv=0;
    private int expense=0;
   private int k=0, o=0;

    public String balance(String a, String b, ArrayList<String> c, String d){

        if((!a.equals(""))&&(!b.equals(""))&&(!d.equals(""))) {
            salary = Integer.parseInt(a);
            inv = Integer.parseInt(b);
            expense = Integer.parseInt(d);

            k = 0;

        }
        if(!c.isEmpty()){
            String ex;
            for (int i = 0; i < c.size(); i++) {
                ex = (c.get(i));
                int tx = Integer.parseInt(ex);
                k = k + tx;

            }

        }else{
            k=0;
        }
        // System.out.println("Total Price is "+ k);

        int x = salary - (inv + expense + k);
        xx = Integer.toString(x);

        return xx;
    }
    public String balance1(String a, String b, ArrayList<String> c, String d, ArrayList<String> r){
        String xx;
        int salary=0;
        int inv=0;
        int expense=0;
        int k=0, o=0;
        if((!a.equals(""))&&(!b.equals(""))&&(!d.equals(""))) {
            salary = Integer.parseInt(a);
            inv = Integer.parseInt(b);
            expense = Integer.parseInt(d);

            k = 0;

        }
        if(!c.isEmpty()){
            String ex;
            for (int i = 0; i < c.size(); i++) {
                ex = (c.get(i));
                int tx = Integer.parseInt(ex);
                k = k + tx;

            }

        }else{
            k=0;
        }
        if(!r.isEmpty()){
            String e;
            for (int i = 0; i < r.size(); i++) {
                e = (r.get(i));
                int t = Integer.parseInt(e);
                o = o + t;

            }

        }else{
            o=0;
        }
        // System.out.println("Total Price is "+ k);

        int x = (salary+o) - (inv + expense + k);
        xx = Integer.toString(x);

        return xx;
    }
    public String rbalance(String a, String b){
        String xx;
        int salary=0;
        int rbalance=0,total=0;


            salary = Integer.parseInt(a);
            rbalance = Integer.parseInt(b);

             total= salary+rbalance;
             xx= String.valueOf(total);
        return xx;
    }
    public ArrayList type(ArrayList a) {
        int stn = 0, food = 0, cloth = 0, fuel = 0, misc = 0, trans = 0, mort = 0, utili = 0, ent = 0, med = 0, main = 0;
        int st = 0, foo = 1, clot = 2, ful = 3, mis = 4, trns = 5, mrt = 6, util = 7, nt = 8, md = 9, ain = 10;
        ArrayList<Integer> x = new ArrayList<>();
       // int[] aa = new int[a.size()];
        ArrayList<String> count = new ArrayList<>();
        ArrayList<String> tp = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).equals("Stationary")) {
                stn = stn + 1;
            } else if (a.get(i).equals("Clothes")) {
                cloth = cloth + 1;
            } else if (a.get(i).equals("Food")) {
                food = food + 1;
            } else if (a.get(i).equals("Transportation")) {
                trans = trans + 1;
            } else if (a.get(i).equals("Mortgages")) {
                mort = mort + 1;
            } else if (a.get(i).equals("Fuel")) {
                fuel = fuel + 1;
            } else if (a.get(i).equals("Utilities")) {
                utili = utili + 1;
            } else if (a.get(i).equals("Entertainment")) {
                ent = ent + 1;
            } else if (a.get(i).equals("Medicine")) {
                med = med + 1;
            } else if (a.get(i).equals("Maintenance")) {
                main = main + 1;
            } else if (a.get(i).equals("Miscellaneous")) {
                misc = misc + 1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(stn,st);
        map.put(cloth,clot);
        map.put(food,foo);
        map.put(trans,trns);
        map.put(mort,mrt);
        map.put(fuel,ful);
        map.put(utili,util);
        map.put(ent,nt);
        map.put(med,md);
        map.put(main,ain);
        map.put(misc,mis);

        x.add(stn);
        x.add(cloth);
        x.add(food);
        x.add(trans);
        x.add(mort);
        x.add(fuel);
        x.add(utili);
        x.add(ent);
        x.add(med);
        x.add(main);
        x.add(misc);


        ArrayList<Integer> employeeByKey = new ArrayList<>(map.values());

        Object o;
        Collections.sort(x);
        for (int i = 0; i < x.size(); i++) {
            if ((x.get(i) == stn)){//&&(map.get(o=x.get(i))==st)) {
                String stn1 = "Stationary";
                if(stn!=0) {
                    count.add(stn1);
                }
            } else if ((x.get(i) == food)){//&&()&&(map.get(o=x.get(i))==foo)) {
                String stn11 = "Food";
                if(food!=0) {
                    count.add(stn11);
                }
            } else if ((x.get(i) == fuel)){//&&(map.get(o=x.get(i))==ful)) {
                String stn2 = "Fuel";
                if(fuel!=0) {
                    count.add(stn2);
                }
            } else if ((x.get(i) == mort)){//)&&(map.get(o=x.get(i))==mrt)) {
                if(mort!=0) {
                    String stn3 = "Mortgage";
                    count.add(stn3);
                }
            } else if ((x.get(i) == misc)){//)&&(map.get(o=x.get(i))==mis)) {
                String stn4 = "Miscellaneous";
                if(misc!=0) {
                    count.add(stn4);
                }
            } else if ((x.get(i) == cloth)){//)&&(map.get(o=x.get(i))==clot)) {
                String stn5 = "Clothes";
                if(cloth!=0) {
                    count.add(stn5);
                }
            } else if ((x.get(i) == med)){//)&&(map.get(o=x.get(i))==md)) {
                String stn6 = "Medicine";
                if(med!=0) {
                    count.add(stn6);
                }
            } else if ((x.get(i) == trans)){//)&&(map.get(o=x.get(i))==trns)) {
                String stn7 = "Transportation";
                if(trans!=0) {
                    count.add(stn7);
                }
            } else if ((x.get(i) == main)){//)&&(map.get(o=x.get(i))==ain)) {
                String stn8 = "Maintenance";
                if(main!=0) {
                    count.add(stn8);
                }
            } else if( (x.get(i) == ent)){//)&&(map.get(o=x.get(i))==nt)) {
                String stn9 = "Entertainment";
                if(ent!=0) {
                    count.add(stn9);
                }
            } else if ((x.get(i) == utili)){//)&&(map.get(o=x.get(i))==util)) {
                String stn10 = "Utilities";
                if(utili!=0) {
                    count.add(stn10);
                }
            }

        }
        if(!count.isEmpty()&&(count.size()==1)) {
            tp.add("Food");
           // tp.add(count.get(count.size() - 1));
            tp.add("No Data");
            tp.add("No Data");
        }else if(!count.isEmpty()&&(count.size()==2)){
            tp.add("Food");
            tp.add("Transportation");
            //tp.add(count.get(count.size() - 1));
            //tp.add(count.get(count.size() - 2));
            tp.add("No Data");
        }else if(!count.isEmpty()&&(count.size()>=3)){
            tp.add("Food");
            tp.add("Transportation");
            tp.add("Utilities");
            /*
            tp.add(count.get(count.size() - 1));
            tp.add(count.get(count.size() - 2));
            tp.add(count.get(count.size() - 3));
*/
        }


        if(count.isEmpty()){
            tp.add("No Data");
            tp.add("No Data");
            tp.add("No Data");
        }
        return tp;
    }
    /*
    public static String tp(ArrayList x){
         Stream.of("test","test","hello","test")
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(System.out::println);

    }
    */
    public  ArrayList mostCommon(ArrayList<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (Object t : list) {
            Integer val = map.get(t);
            map.put(t.toString(), val == null ? 1 : val + 1);
        }

        Map.Entry<String, Integer> max = null;

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        List<Integer> employeeByKey = new ArrayList<>(map.values());
        Collections.sort(employeeByKey);
        ArrayList x= new ArrayList();
        ArrayList s= new ArrayList();

        for(int i=0; i<employeeByKey.size();i++){
            x.add(employeeByKey.get(i));
        }
        Object c=null,c1=null,c2=null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(!x.isEmpty()&&(x.size()==1)) {
                c=x.get(x.size()-1);
            }else if(!x.isEmpty()&&(x.size()==2)){
                if(x.get(x.size()-1)==x.get(x.size()-2)) {
                    c = x.get(x.size() - 1);
                }else{
                    c = x.get(x.size() - 1);
                    c1=x.get(x.size()-2);
                }

            }else if(!x.isEmpty()&&(x.size()>=3)){
               // if(x.get(x.size()-1)==x.get(x.size()-2)) {
                c=x.get(x.size()-1);
                c1=x.get(x.size()-2);
                c2=x.get(x.size()-3);

            }else {
                s.add("No data");
            }





            if (Objects.equals(c, entry.getValue())) {
                s.add(entry.getKey());
            }
            if (Objects.equals(c1, entry.getValue())) {
                s.add(entry.getKey());
            }
            if (Objects.equals(c2, entry.getValue())) {
                s.add(entry.getKey());
            }

        }
/*
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            String key = entry.getKey();
            Integer value = entry.getValue();

            x.add(value);
        }
*/


        return s;
    }
}
