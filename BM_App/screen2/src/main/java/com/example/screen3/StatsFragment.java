package com.example.screen3;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.DBHelper1;
import com.example.common.MySqliteOpenHelper;
import com.example.common.remainingbalance;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String TAG = "MainActivity";
    private String[] yData = {"25.3", "10.6", "66.76", "44.32", "46.01", "16.89", "23.9"};
    private String[] xData = {"Investment","Fixed Expense", "Total Expenses","Remaining Balance"};
    private String[] yData1= new String[4];
    private Float[] xData1= new Float[11];
    String [] lol= new String[6];

    PieChart pieChart;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        Log.d(TAG, "onCreate: starting to create chart");
        ImageView img= view.findViewById(R.id.imageView);
        final TextView txt= view.findViewById(R.id.sts);
        pieChart = (PieChart) view.findViewById(R.id.idPieChart);
        img.setY(250);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MySqliteOpenHelper sql=new MySqliteOpenHelper(getActivity());
                final DBHelper1 mysql=new DBHelper1(getActivity());
                final remainingbalance rm =new remainingbalance(getActivity());
                // final String v1,v2,v3,v4;
                ArrayList<String> type1 = mysql.GetUsers();


                final ArrayList<String> r = rm.all();

                final ArrayList<HashMap<String, String>> data = sql.all();
                final ArrayList<HashMap<String, String>> data1 = sql.all1();
                final ArrayList<HashMap<String, String>> data2 = sql.all2();
                final ArrayList<HashMap<String, String>> data3 = sql.all3();
                final ArrayList<HashMap<String, String>> data4 = sql.all4();
                final ArrayList<String> price = mysql.all2();
                if ((data.isEmpty()== true) || (data1.isEmpty()== true) || (data2.isEmpty() == true)||(data3.isEmpty()==true)||(data4.isEmpty()==true)) {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_LONG).show();
                } else {
                    txt.setVisibility(View.VISIBLE);
                    String p = String.valueOf(data).replaceAll("\\=", "");
                    String p1 = String.valueOf(data1).replaceAll("\\=", "");
                    String p2 = String.valueOf(data2).replaceAll("\\=", "");
                    String p3 = String.valueOf(data3).replaceAll("\\=", "");
                    String p4 = String.valueOf(data4).replaceAll("\\=", "");
                    String v1 = String.valueOf(balance(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""), r).get(0));
                    String v2 = String.valueOf(balance(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""),r).get(1));
                    String v3 = String.valueOf(balance(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""),r).get(2));
                    String v4 = String.valueOf(balance(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""),r).get(3));
                    System.out.println(v1);
                    yData1[0] = v1;
                    yData1[1] = v2;
                    yData1[2] = v3;
                    yData1[3] = v4;
                    // System.out.println(balance(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", "")));


                    for (int i = 0; i < yData1.length; i++) {
                        System.out.println(yData1[i]);
                    }
                    txt.setText("Pie Chart of Budget Distribution");
                    pieChart.getDescription().setPosition(0, 1);
                    pieChart.setRotationEnabled(true);
                    //pieChart.setUsePercentValues(true);
                    //pieChart.setHoleColor(Color.BLUE);
                    //pieChart.setCenterTextColor(Color.BLACK);
                    pieChart.setHoleRadius(26f);
                    pieChart.setTransparentCircleAlpha(0);
                    pieChart.setCenterText("A Debt Problem at its Core is a Budgeting Problem.");
                    pieChart.setCenterTextSize(10);
                    //pieChart.setDrawEntryLabels(true);
                    //pieChart.setEntryLabelTextSize(20);
                    //More options just check out the documentation!

                    addDataSet();
                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {
                            Log.d(TAG, "onValueSelected: Value select from chart.");
                            Log.d(TAG, "onValueSelected: " + e.toString());
                            Log.d(TAG, "onValueSelected: " + h.toString());
                            int pos1 = 0;    //= e.toString().indexOf("(sum): ");
                            String sales = e.toString().substring(pos1 + 17);
                            System.out.println(sales);

                            if (yData1[0].contains(sales)) {
                                pos1 = 0;
                            } else if (yData1[1].contains(sales)) {
                                pos1 = 1;
                            } else if (yData1[2].contains(sales)) {
                                pos1 = 2;
                            } else if (yData1[3].contains(sales)) {
                                pos1 = 3;
                            }

                            System.out.println("Pos is " + pos1);
                            String employee = xData[pos1];
                            Toast.makeText(getActivity(), "" + employee + " $" + sales, Toast.LENGTH_LONG).show();
                        }


                        @Override
                        public void onNothingSelected() {

                        }
                    });
                }
            }
        });











        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private void addDataSet() {
        Log.d(TAG, "addDataSet ");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        for(int i = 0; i < yData1.length ;i++){
            yEntrys.add(new PieEntry(Float.parseFloat(yData1[i]) , xData[i]));
        }
        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(16);
        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

   /* public String[] myarray(String a[]) {
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

        String z[] = new String[7];
        String y1[] = new String[7];
        double y[] = new double[7];
        int stn = 0, food = 0, cloth = 0, fuel = 0, misc = 0, trans = 0, mort = 0, utili = 0, ent = 0, med = 0, main = 0;
        ArrayList<Integer> x = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            if (a[i].equals("Stationary")) {
                stn = stn + 1;
                if (stn != 0) {
                    x.add(stn);
                }
            } else if (a[i].equals("Clothes")) {
                cloth = cloth + 1;
                if (cloth != 0) {
                    x.add(cloth);
                }
            } else if (a[i].equals("Food")) {
                food = food + 1;
                if (food != 0) {
                    x.add(food);
                }
            } else if (a[i].equals("Transportation")) {
                trans = trans + 1;
                if (trans != 0) {
                    x.add(trans);
                }
            } else if (a[i].equals("Mortgages")) {
                mort = mort + 1;
                if (mort != 0) {
                    x.add(mort);
                }
            } else if (a[i].equals("Fuel")) {
                fuel = fuel + 1;
                if (stn != 0) {
                    x.add(stn);
                }
            } else if (a[i].equals("Utilities")) {
                utili = utili + 1;
                if (utili != 0) {
                    x.add(utili);
                }
            } else if (a[i].equals("Entertainment")) {
                ent = ent + 1;
                if (ent != 0) {
                    x.add(ent);
                }
            } else if (a[i].equals("Medicine")) {
                med = med + 1;
                if (med != 0) {
                    x.add(med);
                }
            } else if (a[i].equals("Maintenance")) {
                main = main + 1;
                if (main != 0) {
                    x.add(main);
                }
            } else if (a[i].equals("Miscellaneous")) {
                misc = misc + 1;
                if (misc != 0) {
                    x.add(misc);
                }
            }
        }

       // System.out.println(x);

        for(int j=0;j<x.size();j++){
            z[j]=x.get(j).toString();
            y[j]=((Integer.parseInt(z[j])*360)/11);
            //System.out.println(z[j]);

        }
        for(int i=0;i<z.length;i++) {
            y1[i]= String.valueOf(y[i]).toString();
            System.out.println(y1[i]);
        }

        return y1;
    }
    */

    public ArrayList balance(String a,String b, ArrayList<String> c, String d, ArrayList<String> r){
        String xx,xxx;
        ArrayList<String> data= new ArrayList<String>();

        int salary=0;
        double inv=0;
        double expense=0;
        double k=0, o=0;
        if((!a.equals(""))&&(!b.equals(""))&&(!d.equals(""))) {
            salary = Integer.parseInt(a);
            inv = Double.parseDouble(b);
            expense = Double.parseDouble(d);
            String inv1= String.valueOf(inv).toString();
            String expense1= String.valueOf(expense).toString();
            data.add(inv1);
            data.add(expense1);
            k = 0.0;

        }
        if(!c.isEmpty()){
            String ex;
            for (int i = 0; i < c.size(); i++) {
                ex = (c.get(i));
                double tx = Double.parseDouble(ex);
                k = k + tx;

            }

        }
        // System.out.println("Total Price is "+ k);
        if(!r.isEmpty()){
            String e;
            for (int i = 0; i < r.size(); i++) {
                e = (r.get(i));
                double t = Double.parseDouble(e);
                o = o + t;

            }

        }
        double x = (salary+o) - (inv + expense + k);
        xxx= String.valueOf(x).toString();
        xx = String.valueOf(k).toString();
        data.add(xx);
        data.add(xxx);

        return data;
    }











    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
