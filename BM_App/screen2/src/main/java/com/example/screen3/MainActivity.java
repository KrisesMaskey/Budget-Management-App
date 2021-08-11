package com.example.screen3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.common.BudgetDB;
import com.example.common.DBHelper1;
import com.example.common.ExpenseDB;
import com.example.common.MySqliteOpenHelper;
import com.example.common.Priority;
import com.example.common.remainingbalance;
import com.example.common.stat_activity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public FragmentPagerAdapter page;
    public ViewPager viewPager;
    public Toolbar toolbar;
    public ImageView img;
    public TabLayout tabLayout;
    public  PopupWindow popUp;
    public boolean flag=true;
    NavigationView navigationView;
    public String  balance, rbalance;
    DialogFragment dialog;
    public String mm;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img= findViewById(R.id.imageButton);
        toolbar = findViewById(R.id.tool_bar);
        popUp = new PopupWindow(this);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = findViewById(R.id.tabLayout);
        final TabItem tabChats = findViewById(R.id.budget);
        TabItem tabStatus = findViewById(R.id.expenses);
        TabItem tabCalls = findViewById(R.id.stats);
        viewPager = findViewById(R.id.viewPager);
        navigationView= findViewById(R.id.design_navigation_view);




        page = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(page);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(10);
        img.setOnClickListener(new View.OnClickListener() {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.nav_header, null);
            View popupView1 = inflater.inflate(R.layout.stat_click, null);
            @Override

            public void onClick(View view) {
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.setAnimationStyle(R.style.DialogAnimation);
                popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);

                final TextView txt= popupView.findViewById(R.id.currentBD);
                final TextView txt1= popupView.findViewById(R.id.previousBD);
                final TextView txt2=popupView.findViewById(R.id.stats);
                final TextView title= popupView.findViewById(R.id.current);

                //txt2.setText("Hello World");
                txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment=null;
                        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
                        popupWindow.dismiss();
                        viewPager.setCurrentItem(0);


                    }
                });
                txt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final stat_activity st = new stat_activity();
                        final BudgetDB bd= new BudgetDB(MainActivity.this);
                        ArrayList<HashMap<String, String>> type1 = bd.all1();
                        ArrayList<HashMap<String, String>> type2 = bd.all2();
                        ArrayList<HashMap<String, String>> type3 = bd.all3();
                        ArrayList<HashMap<String, String>> type4 = bd.all4();
                        /// ArrayList<HashMap<String, String>> type5 = bd.all();
                        ArrayList<HashMap<String, String>> type6 = bd.all5();
                        System.out.println(type1);
                        System.out.println(type2);
                        System.out.println(type3);
                        System.out.println(type4);
                        //System.out.println(type5);
                        System.out.println(type6);


                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                        builderSingle.setIcon(R.drawable.schedule);
                        builderSingle.setTitle("Select Month:-");
                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);

                        ArrayList<String> month= bd.all();
                        // System.out.println("Hello "+month.subList(0,month.size()-1));
                        for(int i=month.size()-1;i>0;i--) {
                            arrayAdapter.addAll(month.get(i));
                        }

                        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String k= arrayAdapter.getItem(which);



                                ArrayList<String> xx= bd.senderMobile(k);
                                String investment= xx.get(0);
                                String Reserve= xx.get(1);
                                String balance= xx.get(2);
                                String fixed_expense= xx.get(3);
                                String capital= xx.get(4);

                                int add= Integer.parseInt(balance)+1;
                                ArrayList<String> bn= bd.bb(String.valueOf(add));
                                String remainbalance= bn.get(0);


                                System.out.println(bd.senderMobile(k));
                                final Dialog dialog1= new Dialog(MainActivity.this);
                                dialog1.setContentView(R.layout.stat_click);
                                dialog1.setTitle("Title");
                                dialog1.show();
                                Window window = dialog1.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                                final TextView txtlul= dialog1.findViewById(R.id.Capital2);
                                final TextView txt2= dialog1.findViewById(R.id.invst);
                                final TextView txt3= dialog1.findViewById(R.id.reserve);
                                final TextView txt4= dialog1.findViewById(R.id.fe);
                                final TextView txt5= dialog1.findViewById(R.id.saving1);
                                txtlul.setText("Capital:                                            "+capital);
                                txt2.setText("Investment:                                         "+investment);
                                txt3.setText("Reserve:                                              "+Reserve);
                                txt4.setText("Fixed-Expense:                                 "+   fixed_expense);
                                txt5.setText("Closing Balance:                            "+remainbalance);



                                final ExpenseDB sql = new ExpenseDB(MainActivity.this);
                                ArrayList<String> arrayList= sql.senderMobile(k);


                                final ListView recyclerView= dialog1.findViewById(R.id.recycle);
                                final ArrayList<String> name =sql.all(k);
                                ArrayList<String> data1 =sql.all1(k);
                                final ArrayList<String> data =sql.all2(k);
                                final ArrayList<String> data2 =sql.GetUsers(k);
                                System.out.println("Hello "+ data + data1+ name);
                                recyclerView.setVisibility(View.VISIBLE);
                                ArrayList<Person> peopleList = new ArrayList<>();
                                for(int i=0;i<data.size();i++) {
                                    Person john = new Person("$"+data.get(i), data1.get(i), name.get(i));
                                    peopleList.add(john);
                                }



                                PersonListAdapter adapter = new PersonListAdapter(MainActivity.this, R.layout.activity_listview, peopleList);

                                // ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2,android.R.id.text1,data1);

                                recyclerView.setAdapter(adapter);
                                recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        int x= recyclerView.getSelectedItemPosition();

                                        Toast.makeText(MainActivity.this, "You spent it on " + data2.get(i)+
                                                "", Toast.LENGTH_LONG).show();


                                    }
                                });


                            }
                        });
                        builderSingle.show();



                    }
                });
                txt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(view.getContext(), linegraph.class);
                        startActivityForResult(myIntent, 0);


                    }
                });
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure?"+"\n"+"PS: The remaining balance of your current month would get added to the new budget month");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog1, int which) {
                                // Do nothing but close the dialog
                                final MySqliteOpenHelper sql=new MySqliteOpenHelper(MainActivity.this);
                                final DBHelper1 mysql= new DBHelper1(MainActivity.this);
                                final BudgetDB bd= new BudgetDB(MainActivity.this);
                                final remainingbalance rm= new remainingbalance(MainActivity.this);
                                final Priority priority= new Priority();

                                ArrayList<String> price= mysql.all2();
                                ArrayList<String> r= rm.all();
                                final ArrayList<HashMap<String, String>> data =sql.all();
                                ArrayList<HashMap<String, String>> data1 =sql.all1();
                                ArrayList<HashMap<String, String>> data2=sql.all2();
                                ArrayList<HashMap<String, String>> data3 =sql.all3();
                                ArrayList<HashMap<String, String>> data4 =sql.all4();
                                final String p= String.valueOf(data).replaceAll("\\=","");
                                final String p1= String.valueOf(data1).replaceAll("\\=","");
                                final String p2= String.valueOf(data2).replaceAll("\\=","");
                                final String p3= String.valueOf(data3).replaceAll("\\=","");
                                final String p4= String.valueOf(data4).replaceAll("\\=","");
                                rbalance= priority.balance1(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""),r);

                                final Dialog dialog = new Dialog(MainActivity.this);
                                dialog.setContentView(R.layout.pop_up);
                                dialog.setTitle("Title");
                                dialog.show();
                                final Spinner spinner=dialog.findViewById(R.id.spinner2);
                                final EditText salary= dialog.findViewById(R.id.editText);
                                final EditText reserve= dialog.findViewById(R.id.editText3);
                                final EditText investment= dialog.findViewById(R.id.editText2);
                                final EditText expense= dialog.findViewById(R.id.editText4);
                                Button button= (Button)dialog.findViewById(R.id.button3);
                                final ImageView cross= dialog.findViewById(R.id.imageView3);


                                cross.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ArrayList<String> month= bd.all();
                                        // System.out.println("Hello "+month.subList(0,month.size()-1));
                                        boolean rep=false;
                                       final String s5=spinner.getSelectedItem().toString();
                                        final String s=salary.getText().toString();
                                        final String s2=reserve.getText().toString();
                                        final String s3=investment.getText().toString();
                                        final String s4=expense.getText().toString();
                                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                       /*
                                        if(s.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                                            Toast.makeText(MainActivity.this, "Insert data in all fields", Toast.LENGTH_LONG).show();
                                        }else {
                                        */
                                            for (int i = 0; i < month.size(); i++) {
                                                if (s5.equals((month.get(i)))) {
                                                    mm = month.get(i);
                                                    rep = true;
                                                }
                                            }
                                            System.out.println(rep);
                                            if (rep == true) {
                                                builder1.setTitle("Error");
                                                builder1.setMessage("The selected month is already present. If you want to continue the old data would be deleted");

                                                builder1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                    //Add Deletion Code

                                                    public void onClick(DialogInterface dialog1, int which) {
                                                        //System.out.println("Clicked" + s5 + s1 + s3 + s2 + s4);
                                                        if (s.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                                                            Toast.makeText(MainActivity.this, "Insert data in all fields", Toast.LENGTH_LONG).show();
                                                        }
                                                        else{
                                                            int x = Integer.parseInt(s) + Integer.valueOf(rbalance);
                                                            String s1 = String.valueOf(x);
                                                            bd.Deletespecific(mm);
                                                            bd.insertData(s5, s1, s3, s2, s4, rbalance);
                                                            sql.Delete();
                                                            boolean lol = sql.insertData(s5, s1, s3, s2, s4);
                                                            rm.Delete();


                                                            mysql.DeleteUser();


                                                            if (lol == true) {
                                                                salary.setText("");
                                                                reserve.setText("");
                                                                investment.setText("");
                                                                expense.setText("");

                                                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                                                System.out.println("inserted");

                                                                dialog.dismiss();
                                                            } else {
                                                                Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                                                            }
                                                        }
                                                    }

                                                });
                                                builder1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog1, int which) {

                                                        dialog.dismiss();

                                                    }

                                                });
                                                builder1.show();
                                            } else {
                                                if(s.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                                                    Toast.makeText(MainActivity.this, "Insert data in all fields", Toast.LENGTH_LONG).show();
                                                }else {
                                                    int x = Integer.parseInt(s) + Integer.valueOf(rbalance);
                                                    String s1 = String.valueOf(x);
                                                    System.out.println("Clicked" + s5 + s1 + s3 + s2 + s4);
                                                    if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("")) {
                                                        flag = false;
                                                    }
                                                    if (flag == true) {
                                                        bd.insertData(s5, s1, s3, s2, s4, rbalance);
                                                        sql.Delete();
                                                        boolean lol = sql.insertData(s5, s1, s3, s2, s4);
                                                        rm.Delete();


                                                        mysql.DeleteUser();


                                                        if (lol == true) {
                                                            salary.setText("");
                                                            reserve.setText("");
                                                            investment.setText("");
                                                            expense.setText("");

                                                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                                            System.out.println("inserted");

                                                            dialog.dismiss();
                                                        } else {
                                                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                                                        }
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Insert data in all fields", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                        }

                                    }

                                });


                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Do nothing
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                    }
                });
            }
        });








        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.white));
                    navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.colorAccent));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.white));
                    navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                android.R.color.darker_gray));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.white));
                    navigationView.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.colorPrimaryDark));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

}








