package com.example.screen3;

public class ListModel {
    private String name="" ;
    private String date="" ;
    private String price="";

    public ListModel(String pname, String pdate,String Price) {
        this.name = pname;
        this.date = pdate;
        this.price = Price;
    }
    public String getname()
    {
        return this.name;
    }
    public String getDate()
    {
        return this.date;
    }
    public String getPrice()
    {
        return this.price;
    }
}
